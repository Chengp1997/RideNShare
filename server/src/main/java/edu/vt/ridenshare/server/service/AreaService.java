package edu.vt.ridenshare.server.service;

import edu.vt.ridenshare.server.entity.Area;

import java.util.List;

public interface AreaService {

    /**
     * query by id
     *
     * @param id area id
     * @return area
     */
    Area queryById(Integer id);

    /**
     * query area by page
     *
     * @return area list
     */
    List<Area> queryAll();

}
