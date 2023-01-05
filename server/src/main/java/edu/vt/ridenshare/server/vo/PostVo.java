package edu.vt.ridenshare.server.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class PostVo implements Serializable {
    /**
     * post id
     */
    private Integer id;

    private Integer userId;

    private String username;

    /**
     * last updated time
     */
    private Date updateTime;

    /**
     * preferred ride time start
     */
    private Date startTime;

    /**
     * preferred ride time end
     */
    private Date endTime;

    /**
     * start spot name
     */
    private String spotNameStart;

    /**
     * end spot name
     */
    private String spotNameEnd;

    /**
     * price willing to pay
     */
    private Double price;

    /**
     * 0: in progress, 1: completed, 2: cancelled
     */
    private Integer status;

    /**
     * 0: driver, 1: passenger
     */
    private Integer type;

}
