package edu.vt.ridenshare.server.dao;

import edu.vt.ridenshare.server.entity.Area;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AreaDao {

    Area queryById(Integer id);

    List<Area> queryAllByLimit(Area area, @Param("pageable") Pageable pageable);

    List<Area> queryAll();

    long count(Area area);

    int insert(Area area);

    int insertBatch(@Param("entities") List<Area> entities);

    int insertOrUpdateBatch(@Param("entities") List<Area> entities);

    int update(Area area);

    int deleteById(Integer id);

}

