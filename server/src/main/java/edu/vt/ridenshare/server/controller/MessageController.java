package edu.vt.ridenshare.server.controller;

import edu.vt.ridenshare.server.entity.Message;
import edu.vt.ridenshare.server.common.log.LogAnnotation;
import edu.vt.ridenshare.server.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Message)表控制层
 *
 * @author makejava
 * @since 2022-12-03 23:53:40
 */
@RestController
@RequestMapping("message")
public class MessageController {
    /**
     * 服务对象
     */
    @Resource
    private MessageService messageService;

//    /**
//     * 分页查询
//     *
//     * @param message 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    @GetMapping
//    public ResponseEntity<Page<Message>> queryByPage(Message message, PageRequest pageRequest) {
//        return ResponseEntity.ok(this.messageService.queryByPage(message, pageRequest));
//    }

//    /**
//     * 通过主键查询单条数据
//     *
//     * @param id 主键
//     * @return 单条数据
//     */
//    @GetMapping("{id}")
//    public ResponseEntity<Message> queryById(@PathVariable("id") Integer id) {
//        return ResponseEntity.ok(this.messageService.queryById(id));
//    }

    @GetMapping("{roomid}")
    @LogAnnotation(module="/message", operation="get all messages by room id")
    public ResponseEntity<List<Message>> queryByRoomId(@PathVariable("roomid") Integer roomid) {
        List<Message> messages = messageService.queryByRoomId(roomid);
        return ResponseEntity.ok(messages);
    }

    //http:localhost:8080/message?userId1=111&userId2=222
    @GetMapping
    @LogAnnotation(module="/message", operation="get all messages")
    public ResponseEntity<List<Message>> queryByUsers(@RequestParam("userId1") Integer userId1,
                                                      @RequestParam("userId2") Integer userId2) {
        return ResponseEntity.ok(this.messageService.queryByUsers(userId1, userId2));
    }


    /**
     * 新增数据
     *
     * @param message 实体
     * @return 新增结果
     */
    @PostMapping
    @LogAnnotation(module="/message", operation="publish new message")
    public ResponseEntity<Message> add(@RequestBody Message message) {
        return ResponseEntity.ok(this.messageService.insert(message));
    }

    /**
     * 编辑数据
     *
     * @param message 实体
     * @return 编辑结果
     */
    @PutMapping
    @LogAnnotation(module="/message", operation="edit messages")
    public ResponseEntity<Message> edit(Message message) {
        return ResponseEntity.ok(this.messageService.update(message));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    @LogAnnotation(module="/message", operation="delete messages")
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.messageService.deleteById(id));
    }

}

