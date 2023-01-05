package edu.vt.ridenshare.server.service.impl;

import edu.vt.ridenshare.server.entity.Room;
import edu.vt.ridenshare.server.dao.RoomDao;
import edu.vt.ridenshare.server.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Room)表服务实现类
 *
 * @author makejava
 * @since 2022-12-04 00:55:31
 */
@Service("roomService")
public class RoomServiceImpl implements RoomService {
    @Resource
    private RoomDao roomDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Room queryById(Integer id) {
        return this.roomDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param room 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Room> queryByPage(Room room, PageRequest pageRequest) {
        long total = this.roomDao.count(room);
        return new PageImpl<>(this.roomDao.queryAllByLimit(room, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param room 实例对象
     * @return 实例对象
     */
    @Override
    public Room insert(Room room) {
        Integer driverId = room.getUser1Id();
        Integer passengerId = room.getUser2Id();
        Room roomResult = this.roomDao.queryByUsers(driverId, passengerId);
        if (roomResult != null) {
            return roomResult;
        }
        this.roomDao.insert(room);
        return room;
    }

    /**
     * 修改数据
     *
     * @param room 实例对象
     * @return 实例对象
     */
    @Override
    public Room update(Room room) {
        this.roomDao.update(room);
        return this.queryById(room.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.roomDao.deleteById(id) > 0;
    }

    @Override
    public List<Room> queryByUserId(Integer userId) {
        return this.roomDao.queryByUserId(userId);
    }

    @Override
    public Room queryByUsers(Integer userId1, Integer userId2) {
        return this.roomDao.queryByUsers(userId1, userId2);
    }
}
