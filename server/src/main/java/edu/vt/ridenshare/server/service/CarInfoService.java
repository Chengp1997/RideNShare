package edu.vt.ridenshare.server.service;

import edu.vt.ridenshare.server.entity.CarInfo;
import edu.vt.ridenshare.server.param.CarParam;
import edu.vt.ridenshare.server.vo.CarVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CarInfoService {

//    CarInfo queryById(Integer id);

    CarInfo queryById(Integer id);

    CarVo findByUserId(Integer userId);

    Page<CarInfo> queryByPage(CarInfo carInfo, PageRequest pageRequest);

    CarVo registerNewCar(CarParam carParam);

    CarVo updateCarInfo(CarInfo carInfo);

    boolean deleteById(Integer id);

}
