package edu.vt.ridenshare.server.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class OrderVo {
    /**
     * order id
     */
    private Integer id;
    /**
     * order createTime
     */
    private Date createTime;
    /**
     * driver id
     */
    private Integer driverId;
    private String driverName;
    /**
     * passenger id
     */
    private Integer passengerId;
    private String passengerName;

    /**
     * car id
     */
    private Integer carId;
    private String plateNo;
    private String carType;
    /**
     * post id
     */
    private Integer postId;

    /**
     * start spot id
     */
    private Integer startSpotId;
    private String startLocation;
    /**
     * end spot id
     */
    private Integer endSpotId;
    private String endLocation;
    /**
     * ride start time
     */
    private Date startTime;
    /**
     * ride end time
     */
    private Date endTime;

    private Double price;
    /**
     * 0: in progress, 1: completed, 2: cancelled
     */
    private Integer status;

}
