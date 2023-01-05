package edu.vt.ridenshare.server.entity;

import java.util.Date;
import java.io.Serializable;

public class AreaSpotMap implements Serializable {
    private static final long serialVersionUID = -36298302962106365L;
    /**
     * area id
     */
    private Integer areaId;
    /**
     * spot id
     */
    private Integer spotId;
    
    private Date createTime;
    
    private Date updateTime;


    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getSpotId() {
        return spotId;
    }

    public void setSpotId(Integer spotId) {
        this.spotId = spotId;
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

