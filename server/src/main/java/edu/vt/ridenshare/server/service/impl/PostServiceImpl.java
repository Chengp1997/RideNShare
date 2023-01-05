package edu.vt.ridenshare.server.service.impl;

import edu.vt.ridenshare.server.entity.*;
import edu.vt.ridenshare.server.dao.PostDao;
import edu.vt.ridenshare.server.enums.PostStatusEnum;
import edu.vt.ridenshare.server.enums.PostTypeEnum;
import edu.vt.ridenshare.server.param.PostDetailParam;
import edu.vt.ridenshare.server.service.*;
import edu.vt.ridenshare.server.param.PostParam;
import edu.vt.ridenshare.server.util.UserThreadLocal;
import edu.vt.ridenshare.server.vo.CarVo;
import edu.vt.ridenshare.server.vo.PostDetailVo;
import edu.vt.ridenshare.server.vo.PostVo;
import edu.vt.ridenshare.server.vo.SpotVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service("postService")
public class PostServiceImpl implements PostService {
    @Resource
    private PostDao postDao;

    @Resource
    private UserService userService;

    @Resource
    private CarInfoService carInfoService;

    @Resource
    private SpotService spotService;

    @Resource
    private AreaService areaService;

    @Resource
    private AreaSpotMapService areaSpotMapService;

    /**
     * query post detail by id
     *
     * @param id post id
     * @param statusCode status code
     * @return post detail
     */
    @Override
    public PostDetailVo queryById(Integer id, int statusCode) {
        Post post = postDao.queryById(id, statusCode);
        if (post == null) {
            return null;
        }

        User user = userService.queryById(post.getUserId());
        if (user == null) {
            return null;
        }

        boolean isDriverPost = post.getType() == PostTypeEnum.DRIVER.getCode();
        CarVo carVo = carInfoService.findByUserId(user.getId());
        if (isDriverPost && carVo.getId() == null) {
            return null;
        }

        Spot spotStart = spotService.queryById(post.getSpotIdStart());
        Spot spotEnd = spotService.queryById(post.getSpotIdEnd());
        AreaSpotMap startAreaSpotMap = areaSpotMapService.queryBySpotId(spotStart.getId());
        AreaSpotMap endAreaSpotMap = areaSpotMapService.queryBySpotId(spotEnd.getId());
        Area startArea = areaService.queryById(startAreaSpotMap.getAreaId());
        Area endArea = areaService.queryById(endAreaSpotMap.getAreaId());

        PostVo postVo = createPostVo(post, user, spotStart, spotEnd);

        PostDetailVo postDetailVo = new PostDetailVo();
        BeanUtils.copyProperties(postVo, postDetailVo);
        postDetailVo.setPhone(user.getPhone());
        if (isDriverPost) {
            postDetailVo.setCarVo(carVo);
        }

        SpotVo spotVoStart = new SpotVo();
        SpotVo spotVoEnd = new SpotVo();
        BeanUtils.copyProperties(spotStart, spotVoStart);
        BeanUtils.copyProperties(spotEnd, spotVoEnd);
        spotVoStart.setAreaId(startArea.getId());
        spotVoStart.setAreaName(startArea.getName());
        spotVoEnd.setAreaId(endArea.getId());
        spotVoEnd.setAreaName(endArea.getName());
        postDetailVo.setStartSpot(spotVoStart);
        postDetailVo.setEndSpot(spotVoEnd);

        return postDetailVo;
    }

    /**
     * query all by page
     * @param pageRequest page object
     * @return all posts by date desc
     */
    @Override
    public Page<PostVo> queryByPage(PageRequest pageRequest) {
        Post temp = new Post();
        temp.setStatus(PostStatusEnum.IN_PROGRESS.getCode());
        long count = this.postDao.count(temp);
        List<Post> posts = this.postDao.queryAllByLimit(temp, pageRequest);
        if (posts == null || posts.size() == 0) {
            return null;
        }
        return new PageImpl<>(toPostVos(posts), pageRequest, count);
    }

    /**
     * Query all by page with filter
     *
     * @param postParam filter
     * @return all filtered posts by date desc
     */
    @Override
    public Page<PostVo> queryByPage(PostParam postParam) {
        Post post = new Post();
        BeanUtils.copyProperties(postParam, post);
        post.setStatus(PostStatusEnum.IN_PROGRESS.getCode());
        PageRequest pageRequest = PageRequest.of(postParam.getPage() - 1, postParam.getSize());
        long count = this.postDao.count(post);
        List<Post> posts = this.postDao.queryAllByLimit(post, pageRequest);
        if (posts == null || posts.size() == 0) {
            return null;
        }
        return new PageImpl<>(toPostVos(posts), pageRequest, count);
    }

    /**
     * Query all by user id and status
     * @param userId user id
     * @param statusCode post status
     * @param pageRequest page info
     * @return all posts by date desc
     */
    @Override
    public Page<PostVo> queryByUserId(Integer userId, int statusCode, PageRequest pageRequest) {
        Post post = new Post();
        post.setUserId(userId);
        long count = this.postDao.count(post);
        if (count == 0) {
            return null;
        }

        List<Post> posts = postDao.queryByUserId(userId, statusCode);
        if (posts == null || posts.size() == 0) {
            return null;
        }

        return new PageImpl<>(toPostVos(posts), pageRequest, count);
    }

    private List<PostVo> toPostVos(List<Post> posts) {
        List<Integer> userIdList = new ArrayList<>();
        List<Integer> spotIdStartList = new ArrayList<>();
        List<Integer> spotIdEndList = new ArrayList<>();

        for (Post post : posts) {
            userIdList.add(post.getUserId());
            spotIdStartList.add(post.getSpotIdStart());
            spotIdEndList.add(post.getSpotIdEnd());
        }

        List<User> users = userService.queryByIds(userIdList);
        Map<Integer, User> userIdMap = new HashMap<>();
        for (User user : users) {
            userIdMap.put(user.getId(), user);
        }

        List<Spot> startSpots = spotService.queryByIds(spotIdStartList);
        List<Spot> endSpots = spotService.queryByIds(spotIdEndList);

        Map<Integer, Spot> startSpotIdMap = new HashMap<>();
        for (Spot startSpot : startSpots) {
            startSpotIdMap.put(startSpot.getId(), startSpot);
        }

        Map<Integer, Spot> endSpotIdMap = new HashMap<>();
        for (Spot endSpot : endSpots) {
            endSpotIdMap.put(endSpot.getId(), endSpot);
        }

        return posts.stream().map(post -> {
            Integer userId = post.getUserId();
            User user = userIdMap.get(userId);
            Integer spotIdStart = post.getSpotIdStart();
            Integer spotIdEnd = post.getSpotIdEnd();
            Spot startSpot = startSpotIdMap.get(spotIdStart);
            Spot endSpot = endSpotIdMap.get(spotIdEnd);

            return createPostVo(post, user, startSpot, endSpot);
        }).collect(Collectors.toList());
    }

    private PostVo createPostVo(Post post, User user, Spot startSpot, Spot endSpot) {
        PostVo postVo = new PostVo();
        BeanUtils.copyProperties(post, postVo);

        postVo.setUserId(user.getId());
        postVo.setUsername(user.getUsername());

        postVo.setSpotNameStart(startSpot.getLocation());
        postVo.setSpotNameEnd(endSpot.getLocation());

        return postVo;
    }

    /**
     * insert a post
     *
     * @param postDetailParam post
     * @return postDetailVo
     */
    @Override
    public PostDetailVo insert(PostDetailParam postDetailParam) {
        Date date = new Date();
        Post insertPost = new Post();
        BeanUtils.copyProperties(postDetailParam, insertPost);
        insertPost.setId(null);
        insertPost.setCreateTime(date);
        insertPost.setUpdateTime(date);

        User user = UserThreadLocal.get();
        insertPost.setUserId(user.getId());
        insertPost.setStatus(0);
        this.postDao.insert(insertPost);
        return queryById(insertPost.getId(), PostStatusEnum.IN_PROGRESS.getCode());
    }

    /**
     * update post
     *
     * @param postDetailParam post
     * @return postDetailVo
     */
    @Override
    public PostDetailVo update(PostDetailParam postDetailParam) {
        Post updatePost = new Post();
        BeanUtils.copyProperties(postDetailParam, updatePost);
        this.postDao.update(updatePost);
        return queryById(updatePost.getId(), PostStatusEnum.IN_PROGRESS.getCode());
    }

    @Override
    public int updateByPost(Post post) {
        return this.postDao.update(post);
    }

    /**
     * delete post by id
     *
     * @param id post id
     * @return delete successful
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.postDao.deleteById(id) > 0;
    }
}
