package edu.vt.ridenshare.server.vo;

import lombok.Data;

@Data
public class UserVo {
    private Integer id;

    private String username;

    private String email;

    private String phone;

}
