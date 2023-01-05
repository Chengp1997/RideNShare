package edu.vt.ridenshare.server.dao;

import edu.vt.ridenshare.server.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface OrderDao {

    /**
     * query by id
     *
     * @param id key
     * @return entity
     */
    Order queryById(Integer id);

    Order queryByPostId(Integer postId);

    /**
     * query all
     *
     * @param order condition
     * @param pageable
     * @return Order
     */
    List<Order> queryAllByLimit(@Param("order") Order order, @Param("pageable") Pageable pageable);

    /**
     * count size
     *
     * @param order condition
     * @return size
     */

    /**
     * query all by user id
     * @param userId
     * @return
     */
    List<Order> queryAllByUserId(Integer userId, @Param("pageable") Pageable pageable);

    long count(Order order);

    /**
     * add new order
     *
     * @param order object
     * @return row
     */
    int insert(Order order);

    /**
     * insert with batch
     *
     * @param entities List<Order> order list
     * @return influenced rows
     */
    int insertBatch(@Param("entities") List<Order> entities);

    /**
     * insert batch
     *
     * @param entities List<Order> orders
     * @return rows
     * @throws org.springframework.jdbc.BadSqlGrammarException throw exception
     */
    int insertOrUpdateBatch(@Param("entities") List<Order> entities);

    /**
     * udpate
     *
     * @param order object
     * @return row
     */
    int update(Order order);

    /**
     * delete by id
     *
     * @param id key
     * @return row
     */
    int deleteById(Integer id);

}

