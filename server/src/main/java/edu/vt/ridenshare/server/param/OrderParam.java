package edu.vt.ridenshare.server.param;

import lombok.Data;

@Data
public class OrderParam {
    private Integer driverId;
    private Integer passengerId;
    private Integer postId;
}
