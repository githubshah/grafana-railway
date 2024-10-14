package com.railway.master.enity.mapping;

import com.railway.master.enity.AuditableEntity;
import com.railway.master.enity.RouteEntity;
import com.railway.master.enity.TrainEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "master_train_route_mapping")
public class TrainRouteMappingEntity extends AuditableEntity<String> {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private TrainEntity trainEntity;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private RouteEntity routeEntity;

    @JoinColumn(name = "depart_time")
    private LocalTime departTime;

    @JoinColumn(name = "arrival_time")
    private LocalTime arrivalTime;
}
