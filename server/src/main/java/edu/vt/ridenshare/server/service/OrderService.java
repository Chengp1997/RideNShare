package edu.vt.ridenshare.server.service;

import edu.vt.ridenshare.server.entity.Order;
import edu.vt.ridenshare.server.vo.OrderVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Order)
 *
 * @since 2022-11-14 15:22:55
 */
public interface OrderService {

    /**
     * query by id
     *
     * @param id key
     * @return object
     */
    OrderVo queryById(Integer id);

    /**
     * query order by post id
     */

    OrderVo queryByPostId(Integer postId);
    /**
     * query by page
     *
     * @param pageRequest
     * @return result
     */


    Page<OrderVo> queryByPage(Integer userId, PageRequest pageRequest);


    /**
     * add new
     *
     * @param order object
     * @return object
     */
    OrderVo insert(Order order);

    /**
     * update
     *
     * @param order object
     * @return object
     */
    OrderVo update(Order order);

    /**
     * delete by id
     *
     * @param id key
     * @return boolean
     */
    boolean deleteById(Integer id);

}
