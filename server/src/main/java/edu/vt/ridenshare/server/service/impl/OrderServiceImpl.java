package edu.vt.ridenshare.server.service.impl;

import edu.vt.ridenshare.server.dao.*;
import edu.vt.ridenshare.server.entity.*;
import edu.vt.ridenshare.server.enums.PostStatusEnum;
import edu.vt.ridenshare.server.service.OrderService;
import edu.vt.ridenshare.server.service.PostService;
import edu.vt.ridenshare.server.vo.OrderVo;
import edu.vt.ridenshare.server.vo.PostDetailVo;
import edu.vt.ridenshare.server.vo.SpotVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (Order)
 *
 * @since 2022-11-14 15:22:55
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private PostDao postDao;

    @Resource
    private UserDao userDao;

    @Resource
    private SpotDao spotDao;

    @Resource
    private PostService postService;


    @Resource
    private CarInfoDao carInfoDao;
    /**
     * query by id
     *
     * @param id key
     * @return object
     */
    @Override
    public OrderVo queryById(Integer id) {
        Order order = orderDao.queryById(id);
        if(order == null) {
            return null;
        }

        return createOrderVo(order);
    }

    @Override
    public OrderVo queryByPostId(Integer postId) {
        Order order = orderDao.queryByPostId(postId);
        if(order == null) {
            return null;
        }
        return createOrderVo(order);
    }

    /**
     * query by page
     *
     * @param pageRequest pageRequest
     * @return result
     */
    @Override
    public Page<OrderVo> queryByPage(Integer userId, PageRequest pageRequest) {

        List<Order> orders = this.orderDao.queryAllByUserId(userId, pageRequest);
        long total = orders.size();

        return new PageImpl<>(toOrderVos(orders), pageRequest, total);
    }

    /**
     * add
     *
     * @param order object
     * @return object
     */
    @Override
    public OrderVo insert(Order order) {
        Post updatePost = new Post();
        updatePost.setId(order.getPostId());
        updatePost.setStatus(1);
        postService.updateByPost(updatePost);

        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        this.orderDao.insert(order);
        return queryById(order.getId());
    }

    /**
     * update
     *
     * @param order object
     * @return object
     */
    @Override
    public OrderVo update(Order order) {
        this.orderDao.update(order);
        return this.queryById(order.getId());
    }

    /**
     * delete by id
     *
     * @param id key
     * @return boolean
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.orderDao.deleteById(id) > 0;
    }

    private OrderVo createOrderVo(Order order) {
        PostDetailVo postDetailVo = postService.queryById(order.getPostId(), PostStatusEnum.COMPLETED.getCode());
        if (postDetailVo == null) {
            return null;
        }


        User driver = userDao.queryById(order.getDriverId());
        if (driver == null) {
            return null;
        }
        User passenger = userDao.queryById(order.getPassengerId());
        if (passenger == null) {
            return null;
        }

        SpotVo startSpot = postDetailVo.getStartSpot();
        SpotVo endSpot = postDetailVo.getEndSpot();

        CarInfo carInfo = carInfoDao.queryById(driver.getCarId());

        driver.setPassword(null);
        passenger.setPassword(null);
        OrderVo orderVo = new OrderVo();
        BeanUtils.copyProperties(order, orderVo);
        orderVo.setDriverId(driver.getId());
        orderVo.setDriverName(driver.getUsername());
        orderVo.setPassengerId(passenger.getId());
        orderVo.setPassengerName(passenger.getUsername());
        orderVo.setCarId(carInfo.getId());
        orderVo.setPlateNo(carInfo.getPlateNo());
        orderVo.setCarType(carInfo.getCarType());
        orderVo.setStartSpotId(startSpot.getId());
        orderVo.setStartLocation(startSpot.getLocation());
        orderVo.setEndSpotId(endSpot.getId());
        orderVo.setEndLocation(endSpot.getLocation());
        orderVo.setStartTime(postDetailVo.getStartTime());
        orderVo.setEndTime(postDetailVo.getEndTime());
        return orderVo;
    }

    private List<OrderVo> toOrderVos(List<Order> orders) {
        List<OrderVo> orderVos = new ArrayList<>();

        for(Order order : orders) {
            orderVos.add(createOrderVo(order));
        }
        return orderVos;
    }
}
