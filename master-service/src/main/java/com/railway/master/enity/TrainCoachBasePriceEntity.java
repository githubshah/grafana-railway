package com.railway.master.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "master_train_coach_base_price_policy")
public class TrainCoachBasePriceEntity extends AuditableEntity<String> {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private TrainEntity trainEntity;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private CoachEntity coachEntity;

    @Column(name = "base_price")
    private Double basePrice;

}