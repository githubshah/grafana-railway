package com.railway.master.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "master_route")
public class RouteEntity extends AuditableEntity<String> {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "source_station_id")
    @OneToOne
    private StationEntity sourceStationId;

    @JoinColumn(name = "destination_station_id")
    @OneToOne
    private StationEntity destinationStationId;
}