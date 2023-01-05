package edu.vt.ridenshare.server.controller;

import edu.vt.ridenshare.server.entity.Spot;
import edu.vt.ridenshare.server.common.log.LogAnnotation;
import edu.vt.ridenshare.server.service.SpotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("spot")
public class SpotController {
    @Resource
    private SpotService spotService;

    /**
     * query spots by area id
     *
     * @param areaId area id
     * @return spots
     */
    @GetMapping("{areaId}")
    @LogAnnotation(module="/spot", operation="find spot by area id")
    public ResponseEntity<List<Spot>> queryById(@PathVariable("areaId") Integer areaId) {
        return ResponseEntity.ok(this.spotService.queryByAreaId(areaId));
    }

}

