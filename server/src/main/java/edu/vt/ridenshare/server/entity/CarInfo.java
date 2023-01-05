package edu.vt.ridenshare.server.entity;

import java.util.Date;
import java.io.Serializable;

public class CarInfo implements Serializable {
    private static final long serialVersionUID = 740913484083171299L;
    /**
     * car id
     */
    private Integer id;
    /**
     * plate no
     */
    private String plateNo;
    /**
     * user id
     */
    private Integer userId;
    /**
     * Toyota Highlander SUV
     */
    private String carType;
    
    private Integer capacity;
    
    private Integer years;
    /**
     * image url
     */
    private String image;
    
    private Date createTime;
    
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

}

