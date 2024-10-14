package com.railway.master.enity.mapping;

import com.railway.master.enity.AuditableEntity;
import com.railway.master.enity.SeatEntity;
import com.railway.master.enity.SeatTypeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "master_seat_type_mapping")
public class SeatTypeMappingEntity extends AuditableEntity<String> {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private SeatEntity seatEntity;

    @ManyToOne
    @JoinColumn(name = "seat_type_id", referencedColumnName = "id")
    private SeatTypeEntity seatTypeEntity;

}
