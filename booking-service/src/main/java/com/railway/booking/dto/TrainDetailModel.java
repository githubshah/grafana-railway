package com.railway.booking.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class TrainDetailModel {
    private Long trainId;
    private String trainName;
    private Long routeId;
    private String routeName;
    private LocalTime departTime;
    private LocalTime arrivalTime;

    public TrainDetailModel(Long trainId, String trainName, Long routeId, String routeName, LocalTime departTime, LocalTime arrivalTime) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.routeId = routeId;
        this.routeName = routeName;
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
    }
}