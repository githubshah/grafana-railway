package com.railway.master.enity.mapping;

import com.railway.master.enity.AuditableEntity;
import com.railway.master.enity.CoachEntity;
import com.railway.master.enity.TrainEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "master_train_coach_mapping")
public class TrainCoachMappingEntity extends AuditableEntity<String> {

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
}
