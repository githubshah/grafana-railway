package com.railway.master.controller;

import com.railway.master.dto.TrainInfoDTO;
import com.railway.master.model.TrainDetailModel;
import com.railway.master.service.TrainMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class TrainController {
    @Autowired
    private TrainMasterService trainMasterService;

    @GetMapping("/from/{from}/to/{to}")
    public List<TrainDetailModel> trains(@PathVariable("from") Long from, @PathVariable("to") Long to) {
        return trainMasterService.getAllTrainBy(from, to);
    }

    @GetMapping("/{train_id}/route/{route_id}")
    public TrainInfoDTO trainByRoute(@PathVariable("train_id") Long trainId, @PathVariable("route_id") Long routeId) {
        return trainMasterService.getTrainById(trainId, routeId);
    }

    @GetMapping("route/{id}")
    public TrainDetailModel train(@PathVariable("id") Long id) {
        return trainMasterService.getTrainRouteId(id);
    }
}
