package com.railway.master.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CoachInfoDTO {

    long coachId;
    String coachName;
    List<SeatInfo> seats;

}
