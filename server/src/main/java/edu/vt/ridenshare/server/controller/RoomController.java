package edu.vt.ridenshare.server.controller;

import edu.vt.ridenshare.server.entity.Room;
import edu.vt.ridenshare.server.service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Room)表控制层
 *
 * @author makejava
 * @since 2022-12-04 00:55:23
 */
@RestController
@RequestMapping("room")
public class RoomController {
    /**
     * 服务对象
     */
    @Resource
    private RoomService roomService;

    /**
     * 分页查询
     *
     * @param room 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Room>> queryByPage(Room room, PageRequest pageRequest) {
        return ResponseEntity.ok(this.roomService.queryByPage(room, pageRequest));
    }

//    /**
//     * 通过主键查询单条数据
//     *
//     * @param id 主键
//     * @return 单条数据
//     */
//    @GetMapping("{id}")
//    public ResponseEntity<Room> queryById(@PathVariable("id") Integer id) {
//        return ResponseEntity.ok(this.roomService.queryById(id));
//    }

    //localhost:8080/api/room/1
    @GetMapping("{userId}")
    public ResponseEntity<List<Room>> queryByUserId(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(this.roomService.queryByUserId(userId));
    }

    /**
     * 新增数据
     *
     * @param room 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Room> add(@RequestBody Room room) {
        return ResponseEntity.ok(this.roomService.insert(room));
    }

    /**
     * 编辑数据
     *
     * @param room 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Room> edit(Room room) {
        return ResponseEntity.ok(this.roomService.update(room));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.roomService.deleteById(id));
    }

}

