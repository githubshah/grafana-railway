package com.railway.master.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoachDetailModel {
    private Long coachId;
    private String coachName;

    public CoachDetailModel(Long coachId, String coachName) {
        this.coachId = coachId;
        this.coachName = coachName;
    }
}
