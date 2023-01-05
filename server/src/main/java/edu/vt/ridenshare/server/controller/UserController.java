package edu.vt.ridenshare.server.controller;

import edu.vt.ridenshare.server.entity.User;
import edu.vt.ridenshare.server.common.log.LogAnnotation;
import edu.vt.ridenshare.server.service.UserService;
import edu.vt.ridenshare.server.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping
    @LogAnnotation(module="/user", operation="All users")
    public ResponseEntity<Page<UserVo>> queryByPage(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return ResponseEntity.ok(userService.queryByPage(pageRequest));
    }

    @GetMapping("{id}")
    @LogAnnotation(module="/user", operation="find user by Id")
    public ResponseEntity<UserVo> findUserById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("{id}")
    @LogAnnotation(module="/user", operation="edit user")
    public ResponseEntity<UserVo> editUser(@RequestBody User user) {
        return ResponseEntity.ok(this.userService.updateCurrentUser(user));
    }

}

