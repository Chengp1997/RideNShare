package edu.vt.ridenshare.server.vo;

import lombok.Data;

@Data
public class CarVo {
    private Integer id;

    private String plateNumber;

    private String carType;

    private Integer capacity;

    private Integer years;

    private String image;
}
