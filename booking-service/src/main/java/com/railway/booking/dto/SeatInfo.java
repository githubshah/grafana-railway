package com.railway.booking.dto;

import com.railway.booking.constant.SeatType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatInfo {

    Long seatId;
    SeatType seatType;

}