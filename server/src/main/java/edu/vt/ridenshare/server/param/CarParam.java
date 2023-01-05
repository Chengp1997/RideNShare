package edu.vt.ridenshare.server.param;

import lombok.Data;

@Data
public class CarParam {

    private String plateNo;

    private Integer userId;

    private String carType;

    private Integer capacity;

    private Integer years;

    private String image;
}
