package edu.vt.ridenshare.server.controller;

import edu.vt.ridenshare.server.enums.PostStatusEnum;
import edu.vt.ridenshare.server.common.log.LogAnnotation;
import edu.vt.ridenshare.server.param.PostDetailParam;
import edu.vt.ridenshare.server.service.PostService;
import edu.vt.ridenshare.server.param.PostParam;
import edu.vt.ridenshare.server.vo.PostDetailVo;
import edu.vt.ridenshare.server.vo.PostVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("post")
public class PostController {
    @Resource
    private PostService postService;

    /**
     * Get posts by update time desc
     * @param pageNum page num
     * @param pageSize page size
     * @return all posts by update time desc
     */
    @GetMapping
    @LogAnnotation(module="/post", operation="get all post by page")
    public ResponseEntity<Page<PostVo>> queryByPage(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                    @RequestParam(required = false, defaultValue = "6") Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return ResponseEntity.ok(this.postService.queryByPage(pageRequest));
    }

    /**
     * Get posts by filter
     *
     * @param postParam query condition
     * @return filtered posts
     */
    @PostMapping
    @LogAnnotation(module="/post", operation="search post")
    public ResponseEntity<Page<PostVo>> queryByPageFilter(@RequestBody PostParam postParam) {
        return ResponseEntity.ok(this.postService.queryByPage(postParam));
    }

    @GetMapping("/me/{userId}")
    @LogAnnotation(module="/post/me/{userId}", operation="find post of the a user by id")
    public ResponseEntity<Page<PostVo>> queryMyPostByPage(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                          @RequestParam(required = false, defaultValue = "6") Integer pageSize,
                                                          @PathVariable("userId") Integer userId) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return ResponseEntity.ok(this.postService.queryByUserId(userId, PostStatusEnum.ALL.getCode(), pageRequest));
    }

    /**
     * query post detail by id
     *
     * @param id post id
     * @return post detail
     */
    @GetMapping("{id}")
    @LogAnnotation(module="/post/{id}", operation="find post by id")
    public ResponseEntity<PostDetailVo> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.postService.queryById(id, PostStatusEnum.ALL.getCode()));
    }

    /**
     * insert a post
     *
     * @param postDetailParam post
     * @return postDetailVo
     */
    @PostMapping("/add")
    @LogAnnotation(module="/post/add", operation="add new post")
    public ResponseEntity<PostDetailVo> add(@RequestBody PostDetailParam postDetailParam) {
        return ResponseEntity.ok(this.postService.insert(postDetailParam));
    }

    /**
     * update post
     *
     * @param postDetailParam post
     * @return postDetailVo
     */
    @PutMapping
    @LogAnnotation(module="/post/edit", operation="edit post")
    public ResponseEntity<PostDetailVo> edit(@RequestBody PostDetailParam postDetailParam) {
        return ResponseEntity.ok(this.postService.update(postDetailParam));
    }

    /**
     * delete post by id
     *
     * @param id post id
     * @return delete successful
     */
    @DeleteMapping("{id}")
    @LogAnnotation(module="/post/{id}", operation="delete post by id")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.postService.deleteById(id));
    }

}

