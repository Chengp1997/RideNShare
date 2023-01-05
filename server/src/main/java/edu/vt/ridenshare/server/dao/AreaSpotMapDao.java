package edu.vt.ridenshare.server.dao;

import edu.vt.ridenshare.server.entity.AreaSpotMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AreaSpotMapDao {

    /**
     * query maps by areaId
     *
     * @param areaId areaId
     * @return maps
     */
    List<AreaSpotMap> queryByAreaId(Integer areaId);

    List<AreaSpotMap> queryAllByLimit(AreaSpotMap areaSpotMap, @Param("pageable") Pageable pageable);

    long count(AreaSpotMap areaSpotMap);

    int insert(AreaSpotMap areaSpotMap);

    int insertBatch(@Param("entities") List<AreaSpotMap> entities);

    int insertOrUpdateBatch(@Param("entities") List<AreaSpotMap> entities);

    int update(AreaSpotMap areaSpotMap);

    int deleteById(Integer areaId);

    /**
     * query map by spotId
     * @param spotId spot id
     * @return map
     */
    AreaSpotMap queryBySpotId(@Param("spotId") Integer spotId);
}

