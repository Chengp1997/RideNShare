package edu.vt.ridenshare.server.entity;

import java.util.Date;
import java.io.Serializable;

public class Post implements Serializable {
    private static final long serialVersionUID = -98385522401294839L;
    /**
     * post id
     */
    private Integer id;
    
    private Date createTime;
    
    private Date updateTime;
    /**
     * preferred start time
     */
    private Date startTime;
    /**
     * preferred end time
     */
    private Date endTime;
    /**
     * start spot id
     */
    private Integer spotIdStart;
    /**
     * end spot id
     */
    private Integer spotIdEnd;
    
    private Double price;
    
    private Integer userId;
    /**
     * 0: in progress, 1: completed, 2: cancelled
     */
    private Integer status;
    /**
     * 0: driver, 1: passenger
     */
    private Integer type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getSpotIdStart() {
        return spotIdStart;
    }

    public void setSpotIdStart(Integer spotIdStart) {
        this.spotIdStart = spotIdStart;
    }

    public Integer getSpotIdEnd() {
        return spotIdEnd;
    }

    public void setSpotIdEnd(Integer spotIdEnd) {
        this.spotIdEnd = spotIdEnd;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}

