package edu.vt.ridenshare.server.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SpotVo implements Serializable {

    /**
     * spot id
     */
    private Integer id;

    /**
     * spot location name
     */
    private String location;

    private Double longitude;

    private Double latitude;

    /**
     * area id
     */
    private Integer areaId;

    /**
     * the area name this spot belongs to
     */
    private String areaName;

}
