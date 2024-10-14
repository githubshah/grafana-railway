package com.railway.master.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarePolicyModel {
    private Long coachPercentageFilled;
    private Long increasePriceBy;

    public FarePolicyModel(Long coachPercentageFilled, Long increasePriceBy) {
        this.coachPercentageFilled = coachPercentageFilled;
        this.increasePriceBy = increasePriceBy;
    }
}
