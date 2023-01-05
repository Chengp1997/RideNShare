package edu.vt.ridenshare.server.controller;

import edu.vt.ridenshare.server.entity.Area;
import edu.vt.ridenshare.server.common.log.LogAnnotation;
import edu.vt.ridenshare.server.service.AreaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("area")
public class AreaController {

    @Resource
    private AreaService areaService;

    /**
     * query area by page
     *
     * @return area list
     */
    @GetMapping
    @LogAnnotation(module="/area", operation="find all areas")
    public ResponseEntity<List<Area>> queryAll() {
        return ResponseEntity.ok(this.areaService.queryAll());
    }

    @GetMapping("{id}")
    @LogAnnotation(module="/order/{id}", operation="get area by its id")
    public ResponseEntity<Area> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.areaService.queryById(id));
    }
}

