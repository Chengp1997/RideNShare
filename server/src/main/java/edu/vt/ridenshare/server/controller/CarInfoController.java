package edu.vt.ridenshare.server.controller;

import edu.vt.ridenshare.server.entity.CarInfo;
import edu.vt.ridenshare.server.common.log.LogAnnotation;
import edu.vt.ridenshare.server.param.CarParam;
import edu.vt.ridenshare.server.service.CarInfoService;
import edu.vt.ridenshare.server.vo.CarVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("carInfo")
public class CarInfoController {

    @Resource
    private CarInfoService carInfoService;

    @GetMapping
    @LogAnnotation(module="/carInfo", operation="get all carInfo")
    public ResponseEntity<Page<CarInfo>> queryByPage(CarInfo carInfo, PageRequest pageRequest) {
        return ResponseEntity.ok(this.carInfoService.queryByPage(carInfo, pageRequest));
    }

    @GetMapping("{id}")
    @LogAnnotation(module="/carInfo", operation="get carInfo by its id")
    public ResponseEntity<CarInfo> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.carInfoService.queryById(id));
    }

    @GetMapping("/user/{id}")
    @LogAnnotation(module="/carInfo", operation="get carInfo by its owner")
    public ResponseEntity<CarVo> findCarByUserId(@PathVariable("id") Integer userId){
        return ResponseEntity.ok(carInfoService.findByUserId(userId));
    }

    @PostMapping
    @LogAnnotation(module="/carInfo", operation="Register new car")
    public ResponseEntity<CarVo> registerCar(@RequestBody CarParam carParam) {
        return ResponseEntity.ok(carInfoService.registerNewCar(carParam));
    }

    @PutMapping("{id}")
    @LogAnnotation(module="/carInfo/{id}", operation="edit car info")
    public ResponseEntity<CarVo> editCar(@RequestBody CarInfo carInfo) {
        return ResponseEntity.ok(carInfoService.updateCarInfo(carInfo));
    }

}

