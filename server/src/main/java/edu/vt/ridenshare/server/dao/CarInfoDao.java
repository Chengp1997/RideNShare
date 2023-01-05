package edu.vt.ridenshare.server.dao;

import edu.vt.ridenshare.server.entity.CarInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CarInfoDao {

    CarInfo queryById(Integer id);

    List<CarInfo> queryByUserIds(List<Integer> userIdList);

    CarInfo findCarByUserId(Integer userId);

    List<CarInfo> queryAllByLimit(CarInfo carInfo, @Param("pageable") Pageable pageable);

    long count(CarInfo carInfo);

    int insert(CarInfo carInfo);

    int insertBatch(@Param("entities") List<CarInfo> entities);

    int insertOrUpdateBatch(@Param("entities") List<CarInfo> entities);

    int update(CarInfo carInfo);

    int deleteById(Integer id);

}

