package com.railway.booking.controller;

import com.railway.booking.dto.CoachSeatAvailability;
import com.railway.booking.service.TrainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@Slf4j
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping("/train/from/{src}/to/{dest}")
    public Object getBasePrice1(@PathVariable("src") Long trainId, @PathVariable("dest") Long coachId) {
        return trainService.getTrainBetweenSourceAndDestination(trainId, coachId);
    }

    @GetMapping("/train/{train_id}/route/{route_id}")
    public CoachSeatAvailability getTrainAvailability(@PathVariable("train_id") Long trainId,
                                                      @PathVariable("route_id") Long routeId,
                                                      @RequestParam("journeyDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate journeyDate) {
        return trainService.getTrainAvailableSeats(trainId, routeId, journeyDate);
    }
}
