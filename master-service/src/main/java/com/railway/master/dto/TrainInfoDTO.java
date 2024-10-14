package com.railway.master.dto;

import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class TrainInfoDTO {

    private Long trainId;
    private String trainName;

    private Long routeId;
    private String routeName;

    private LocalTime departTime;
    private LocalTime arrivalTime;

    List<CoachInfoDTO> coaches;
}
