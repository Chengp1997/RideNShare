package edu.vt.ridenshare.server.controller;

import edu.vt.ridenshare.server.entity.Order;
import edu.vt.ridenshare.server.common.log.LogAnnotation;
import edu.vt.ridenshare.server.service.OrderService;
import edu.vt.ridenshare.server.vo.OrderVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Order)
 *
 * @author makejava
 * @since 2022-11-14 15:22:55
 */
@RestController
@RequestMapping("order")
public class OrderController {
    /**
     *
     */
    @Resource
    private OrderService orderService;

    /**
     * query by page
     * @param pageSize
     * @param  pageSize
     * @return
     */
    @GetMapping("orders/{userId}")
    @LogAnnotation(module="/order/orders/{userId}", operation="get all orders")
    public ResponseEntity<Page<OrderVo>> queryByPage(@PathVariable("userId") Integer userId, @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                     @RequestParam(required = false, defaultValue = "6") Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return ResponseEntity.ok(this.orderService.queryByPage(userId, pageRequest));
    }

    /**
     *
     * query by order id
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @LogAnnotation(module="/order/{id}", operation="get order by id")
    public ResponseEntity<OrderVo> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.orderService.queryById(id));
    }

    /**
     * query by post id
     */

    @GetMapping("orderDetail/{postId}")
    @LogAnnotation(module="/order/orderDetail/{postId}", operation="get order by postId")
    public ResponseEntity<OrderVo> queryByPostId(@PathVariable("postId") Integer postId) {
        return ResponseEntity.ok(this.orderService.queryByPostId(postId));
    }
    /**
     * add new order
     *
     * @param order entity
     * @return result
     */
    @PostMapping
    @LogAnnotation(module="/order", operation="make an order")
    public ResponseEntity<OrderVo> add(@RequestBody Order order) {
        return ResponseEntity.ok(this.orderService.insert(order));
    }

    /**
     * udpate order
     *
     * @param order entity
     * @return updated orderVo
     */
    @PutMapping
    @LogAnnotation(module="/order", operation="edit order")
    public ResponseEntity<OrderVo> edit(@RequestBody Order order) {
        return ResponseEntity.ok(this.orderService.update(order));
    }

    /**
     * delete order
     *
     * @param id Integer
     * @return boolean
     */
    @DeleteMapping
    @LogAnnotation(module="/order", operation="delete order")
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.orderService.deleteById(id));
    }

}

