package edu.vt.ridenshare.server.service.impl;

import edu.vt.ridenshare.server.entity.AreaSpotMap;
import edu.vt.ridenshare.server.dao.AreaSpotMapDao;
import edu.vt.ridenshare.server.service.AreaSpotMapService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("areaSpotMapService")
public class AreaSpotMapServiceImpl implements AreaSpotMapService {
    @Resource
    private AreaSpotMapDao areaSpotMapDao;

    /**
     * query maps by areaId
     *
     * @param areaId areaId
     * @return maps
     */
    @Override
    public List<AreaSpotMap> queryByAreaId(Integer areaId) {
        return this.areaSpotMapDao.queryByAreaId(areaId);
    }

    /**
     * query map by spotId
     * @param spotId spot id
     * @return map
     */
    @Override
    public AreaSpotMap queryBySpotId(Integer spotId) {
        return areaSpotMapDao.queryBySpotId(spotId);
    }
}
