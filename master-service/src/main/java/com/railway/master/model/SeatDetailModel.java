package com.railway.master.model;

import com.railway.master.constant.SeatType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatDetailModel {
    private Long seatId;
    private SeatType seatType;

    public SeatDetailModel(Long seatId, SeatType seatType) {
        this.seatId = seatId;
        this.seatType = seatType;
    }
}
