package com.railway.booking.webclient;

import com.railway.booking.dto.FarePolicyModel;
import com.railway.booking.dto.TrainCoachBasePriceDetailModel;
import com.railway.booking.dto.TrainDetailModel;
import com.railway.booking.dto.TrainInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "MASTER-SERVICE") // spring.application.name of callee service
public interface MasterServiceClient {
    @GetMapping("/master/train/ticket/base-price/{train_id}/{coach_id}")
    TrainCoachBasePriceDetailModel getBasePriceByTrainIdAndCoachId(@PathVariable("train_id") Long trainId, @PathVariable("coach_id") Long coachId);

    @GetMapping("/master/train/ticket/policy")
    Object getBasePriceByTrainIdAndCoachId();

    @GetMapping("/master/train/from/{src}/to/{dest}")
    Object getTrainBetweenSourceAndDestination(@PathVariable("src") Long src, @PathVariable("dest") Long dest);

    @GetMapping("/master/train/route/{route_id}")
    TrainDetailModel getTrainRouteByRoutId(@PathVariable("route_id") Long routeId);

    @GetMapping("/master/train/{train_id}/route/{route_id}")
    TrainInfoDTO getTrainInfoByTrainIdAndRouteId(@PathVariable("train_id") Long trainId, @PathVariable("route_id") Long routeId);

    @GetMapping("/master/train/ticket/policy")
    List<FarePolicyModel> getFarePolicy();
}