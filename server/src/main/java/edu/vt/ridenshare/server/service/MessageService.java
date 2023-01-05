package edu.vt.ridenshare.server.service;

import edu.vt.ridenshare.server.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Message)表服务接口
 *
 * @author makejava
 * @since 2022-12-03 23:53:40
 */
public interface MessageService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Message queryById(Integer id);

    /**
     * 分页查询
     *
     * @param message 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Message> queryByPage(Message message, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    Message insert(Message message);

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    Message update(Message message);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<Message> queryByUsers(Integer userId1, Integer userId2);

    List<Message> queryByRoomId(Integer roomid);
}
