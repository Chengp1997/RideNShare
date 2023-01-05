package edu.vt.ridenshare.server.service.impl;

import edu.vt.ridenshare.server.entity.CarInfo;
import edu.vt.ridenshare.server.dao.CarInfoDao;
import edu.vt.ridenshare.server.entity.User;
import edu.vt.ridenshare.server.param.CarParam;
import edu.vt.ridenshare.server.service.CarInfoService;
import edu.vt.ridenshare.server.service.UserService;
import edu.vt.ridenshare.server.util.UserThreadLocal;
import edu.vt.ridenshare.server.vo.CarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;

@Service("carInfoService")
public class CarInfoServiceImpl implements CarInfoService {
    @Resource
    private CarInfoDao carInfoDao;
    @Autowired
    private UserService userService;

    @Override
    public CarInfo queryById(Integer id) {
        return carInfoDao.queryById(id);
    }

    @Override
    public CarVo findByUserId(Integer userId) {
        CarInfo car = carInfoDao.findCarByUserId(userId);
        if (car == null){
            return new CarVo();
        }
        return copy(car);

    }

    @Override
    public Page<CarInfo> queryByPage(CarInfo carInfo, PageRequest pageRequest) {
        long total = this.carInfoDao.count(carInfo);
        return new PageImpl<>(this.carInfoDao.queryAllByLimit(carInfo, pageRequest), pageRequest, total);
    }

    @Override
    public CarVo registerNewCar(CarParam carParam) {
        CarInfo carInfo = new CarInfo();
        carInfo.setPlateNo(carParam.getPlateNo());
        carInfo.setUserId(carParam.getUserId());
        carInfo.setCarType(carParam.getCarType());
        carInfo.setCapacity(carParam.getCapacity());
        carInfo.setYears(carParam.getYears());
        carInfo.setImage(carParam.getImage());
        carInfo.setCreateTime(new Date());
        carInfo.setUpdateTime(new Date());
        carInfoDao.insert(carInfo);
        //update user
        User user = UserThreadLocal.get();
        user.setCarId(carInfo.getId());
        user.setUpdateTime(new Date());
        userService.updateCurrentUser(user);
        return findByUserId(carInfo.getUserId());
    }

    @Override
    public CarVo updateCarInfo(CarInfo carInfo) {
        CarInfo currentCar = queryById(carInfo.getId());
        currentCar.setPlateNo(carInfo.getPlateNo());
        currentCar.setCarType(carInfo.getCarType());
        currentCar.setCapacity(carInfo.getCapacity());
        currentCar.setYears(carInfo.getYears());
        currentCar.setUpdateTime(new Date());
        currentCar.setImage(carInfo.getImage());
        carInfoDao.update(currentCar);
        return findByUserId(currentCar.getUserId());
    }

    @Override
    public boolean deleteById(Integer id) {
        return this.carInfoDao.deleteById(id) > 0;
    }

    private CarVo copy(CarInfo car){
        CarVo carVo = new CarVo();
        carVo.setId(car.getId());
        carVo.setPlateNumber(car.getPlateNo());
        carVo.setCarType(car.getCarType());
        carVo.setCapacity(car.getCapacity());
        carVo.setYears(car.getYears());
        carVo.setImage(car.getImage());
        return carVo;
    }
}
