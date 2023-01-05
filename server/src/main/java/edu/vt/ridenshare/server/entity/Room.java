package edu.vt.ridenshare.server.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * (Room)实体类
 *
 * @author makejava
 * @since 2022-12-04 00:55:28
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Room implements Serializable {
    private static final long serialVersionUID = 837698912803289500L;
    /**
     * room id
     */
    private Integer id;
    /**
     * room name
     */
    private String roomname;
    /**
     * user1 id
     */
    private Integer user1Id;
    /**
     * user1 name
     */
    private String user1Name;
    /**
     * another user id
     */
    private Integer user2Id;
    /**
     * another user name
     */
    private String user2Name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public Integer getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(Integer user1Id) {
        this.user1Id = user1Id;
    }

    public String getUser1Name() {
        return user1Name;
    }

    public void setUser1Name(String user1Name) {
        this.user1Name = user1Name;
    }

    public Integer getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(Integer user2Id) {
        this.user2Id = user2Id;
    }

    public String getUser2Name() {
        return user2Name;
    }

    public void setUser2Name(String user2Name) {
        this.user2Name = user2Name;
    }

}

