package com.railway.master.dto;

import com.railway.master.constant.SeatType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatInfo {

    Long seatId;
    SeatType seatType;

}
