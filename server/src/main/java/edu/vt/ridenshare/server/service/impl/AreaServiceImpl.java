package edu.vt.ridenshare.server.service.impl;

import edu.vt.ridenshare.server.entity.Area;
import edu.vt.ridenshare.server.dao.AreaDao;
import edu.vt.ridenshare.server.service.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("areaService")
public class AreaServiceImpl implements AreaService {
    @Resource
    private AreaDao areaDao;

    /**
     * query by id
     *
     * @param id area id
     * @return area
     */
    @Override
    public Area queryById(Integer id) {
        return this.areaDao.queryById(id);
    }

    /**
     * query area by page
     *
     * @return area list
     */
    @Override
    public List<Area> queryAll() {
        return areaDao.queryAll();
    }

}
