package edu.vt.ridenshare.server.dao;

import edu.vt.ridenshare.server.entity.Room;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Room)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-04 00:55:27
 */
public interface RoomDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Room queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param room 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Room> queryAllByLimit(Room room, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param room 查询条件
     * @return 总行数
     */
    long count(Room room);

    /**
     * 新增数据
     *
     * @param room 实例对象
     * @return 影响行数
     */
    int insert(Room room);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Room> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Room> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Room> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Room> entities);

    /**
     * 修改数据
     *
     * @param room 实例对象
     * @return 影响行数
     */
    int update(Room room);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<Room> queryByUserId(Integer userId);

    Room queryByUsers(@Param("user1") Integer userId1, @Param("user2") Integer userId2);

}

