package edu.vt.ridenshare.server.entity;

import java.util.Date;
import java.io.Serializable;

public class Area implements Serializable {
    private static final long serialVersionUID = -87769869538639104L;
    /**
     * area id
     */
    private Integer id;
    /**
     * area name
     */
    private String name;
    
    private Date createTime;
    
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

