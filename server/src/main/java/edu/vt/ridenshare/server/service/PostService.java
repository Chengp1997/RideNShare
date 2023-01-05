package edu.vt.ridenshare.server.service;

import edu.vt.ridenshare.server.entity.Post;
import edu.vt.ridenshare.server.param.PostDetailParam;
import edu.vt.ridenshare.server.param.PostParam;
import edu.vt.ridenshare.server.vo.PostDetailVo;
import edu.vt.ridenshare.server.vo.PostVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PostService {

    /**
     * query post detail by id
     *
     * @param id post id
     * @param statusCode status code
     * @return post detail
     */
    PostDetailVo queryById(Integer id, int statusCode);

    /**
     * query all by page
     * @param pageRequest page object
     * @return all posts by date desc
     */
    Page<PostVo> queryByPage(PageRequest pageRequest);

    /**
     * Query all by page with filter
     *
     * @param postParam filter
     * @return all filtered posts by date desc
     */
    Page<PostVo> queryByPage(PostParam postParam);

    /**
     * Query all by user id and status
     * @param userId user id
     * @param statusCode post status
     * @param pageRequest page info
     * @return all posts by date desc
     */
    Page<PostVo> queryByUserId(Integer userId, int statusCode, PageRequest pageRequest);

    /**
     * insert a post
     *
     * @param postDetailParam post
     * @return postDetailVo
     */
    PostDetailVo insert(PostDetailParam postDetailParam);

    /**
     * update post
     *
     * @param postDetailParam postDetailParam from front end
     * @return postDetailVo
     */
    PostDetailVo update(PostDetailParam postDetailParam);

    /**
     * update by post
     * @param post post passed from other service
     * @return row affected
     */
    int updateByPost(Post post);

    /**
     * delete post by id
     *
     * @param id post id
     * @return delete successful
     */
    boolean deleteById(Integer id);

}
