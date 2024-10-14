package com.railway.booking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainCoachBasePriceDetailModel {
    private Long id;
    private double basePrice;

    public TrainCoachBasePriceDetailModel(Long id, double basePrice) {
        this.id = id;
        this.basePrice = basePrice;
    }
}
