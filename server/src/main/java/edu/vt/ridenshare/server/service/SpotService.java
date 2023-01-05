package edu.vt.ridenshare.server.service;

import edu.vt.ridenshare.server.entity.Spot;

import java.util.List;

public interface SpotService {

    /**
     * query spot by id
     *
     * @param id spot id
     * @return spot
     */
    Spot queryById(Integer id);

    /**
     * query spots by area id
     *
     * @param areaId area id
     * @return spots
     */
    List<Spot> queryByAreaId(Integer areaId);

    /**
     * query spots by ids
     * @param spotIdList spot id list
     * @return spots
     */
    List<Spot> queryByIds(List<Integer> spotIdList);

}
