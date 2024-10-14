package com.railway.master.controller;

import com.railway.master.model.FarePolicyModel;
import com.railway.master.model.TrainCoachBasePriceDetailModel;
import com.railway.master.service.TrainMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@Slf4j
public class TicketPricePolicyController {

    @Autowired
    private TrainMasterService trainMasterService;

    @GetMapping("/base-price/{train_id}/{coach_id}")
    public TrainCoachBasePriceDetailModel getBasePrice(@PathVariable("train_id") Long trainId, @PathVariable("coach_id") Long coachId) {
        return trainMasterService.getBasePrice(trainId, coachId);
    }

    @GetMapping("/policy")
    public List<FarePolicyModel> getPolicy() {
        return trainMasterService.getPolicy();
    }

}
