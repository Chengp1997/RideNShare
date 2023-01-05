package edu.vt.ridenshare.server.service;

import edu.vt.ridenshare.server.entity.AreaSpotMap;

import java.util.List;

public interface AreaSpotMapService {

    /**
     * query maps by areaId
     *
     * @param areaId area id
     * @return maps
     */
    List<AreaSpotMap> queryByAreaId(Integer areaId);

    /**
     * query map by spotId
     * @param spotId spot id
     * @return map
     */
    AreaSpotMap queryBySpotId(Integer spotId);

}
