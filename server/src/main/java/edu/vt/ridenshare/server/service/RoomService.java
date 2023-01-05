package edu.vt.ridenshare.server.service;

import edu.vt.ridenshare.server.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Room)表服务接口
 *
 * @author makejava
 * @since 2022-12-04 00:55:30
 */
public interface RoomService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Room queryById(Integer id);

    /**
     * 分页查询
     *
     * @param room 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Room> queryByPage(Room room, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param room 实例对象
     * @return 实例对象
     */
    Room insert(Room room);

    /**
     * 修改数据
     *
     * @param room 实例对象
     * @return 实例对象
     */
    Room update(Room room);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<Room> queryByUserId(Integer userId);

    Room queryByUsers(Integer userId1, Integer userId2);

}
