package com.railway.master.enity.mapping;

import com.railway.master.enity.AuditableEntity;
import com.railway.master.enity.CoachEntity;
import com.railway.master.enity.SeatEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "master_coach_seat_mapping")
public class CoachSeatMappingEntity extends AuditableEntity<String> {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private CoachEntity coachEntity;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private SeatEntity seatEntity;

}
