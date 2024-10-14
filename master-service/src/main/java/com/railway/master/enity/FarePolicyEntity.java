package com.railway.master.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "master_fare_policy")
public class FarePolicyEntity extends AuditableEntity<String> {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "coach_percentage_filled")
    private Long coachPercentageFilled;

    @Column(name = "increase_price_by")
    private Long increasePriceBy;

}
