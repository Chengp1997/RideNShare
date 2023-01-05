package edu.vt.ridenshare.server.dao;

import edu.vt.ridenshare.server.entity.Spot;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface SpotDao {

    Spot queryById(Integer id);

    List<Spot> queryAllByLimit(Spot spot, @Param("pageable") Pageable pageable);

    long count(Spot spot);

    int insert(Spot spot);

    int insertBatch(@Param("entities") List<Spot> entities);

    int insertOrUpdateBatch(@Param("entities") List<Spot> entities);

    int update(Spot spot);

    int deleteById(Integer id);

    List<Spot> queryByIds(@Param("spotIdList") List<Integer> spotIdList);
}

