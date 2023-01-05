package edu.vt.ridenshare.server.service.impl;

import edu.vt.ridenshare.server.entity.Message;
import edu.vt.ridenshare.server.dao.MessageDao;
import edu.vt.ridenshare.server.entity.Room;
import edu.vt.ridenshare.server.service.MessageService;
import edu.vt.ridenshare.server.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Message)表服务实现类
 *
 * @author makejava
 * @since 2022-12-03 23:53:40
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageDao messageDao;

    @Resource
    private RoomService roomService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Message queryById(Integer id) {
        return this.messageDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param message 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Message> queryByPage(Message message, PageRequest pageRequest) {
        long total = this.messageDao.count(message);
        return new PageImpl<>(this.messageDao.queryAllByLimit(message, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    @Override
    public Message insert(Message message) {
        this.messageDao.insert(message);
        return message;
    }

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    @Override
    public Message update(Message message) {
        this.messageDao.update(message);
        return this.queryById(message.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.messageDao.deleteById(id) > 0;
    }

    @Override
    public List<Message> queryByUsers(Integer userId1, Integer userId2) {
        // get room id
        Room room = roomService.queryByUsers(userId1, userId2);
        // get messages by room id
        return messageDao.queryByRoomId(room.getId());
    }

    @Override
    public List<Message> queryByRoomId(Integer roomid) {
        return messageDao.queryByRoomId(roomid);
    }
}
