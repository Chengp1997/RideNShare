package edu.vt.ridenshare.server.service.impl;

import edu.vt.ridenshare.server.entity.AreaSpotMap;
import edu.vt.ridenshare.server.entity.Spot;
import edu.vt.ridenshare.server.dao.SpotDao;
import edu.vt.ridenshare.server.service.AreaSpotMapService;
import edu.vt.ridenshare.server.service.SpotService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service("spotService")
public class SpotServiceImpl implements SpotService {
    @Resource
    private SpotDao spotDao;

    @Resource
    private AreaSpotMapService areaSpotMapService;

    /**
     * query spot by id
     *
     * @param id spot id
     * @return spot
     */
    @Override
    public Spot queryById(Integer id) {
        return this.spotDao.queryById(id);
    }

    /**
     * query spots by area id
     *
     * @param areaId area id
     * @return spots
     */
    @Override
    public List<Spot> queryByAreaId(Integer areaId) {
        List<AreaSpotMap> areaSpotMaps = areaSpotMapService.queryByAreaId(areaId);
        List<Integer> spotIdList = areaSpotMaps.stream().map(AreaSpotMap::getSpotId).collect(Collectors.toList());
        return this.spotDao.queryByIds(spotIdList);
    }

    @Override
    public List<Spot> queryByIds(List<Integer> spotIdList) {
        return spotDao.queryByIds(spotIdList);
    }

}
