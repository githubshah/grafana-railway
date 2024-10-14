package com.railway.master.enity;

import com.railway.master.constant.SeatType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "master_seat_type")
@NoArgsConstructor
public class SeatTypeEntity extends AuditableEntity<String> {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "seat_type")
    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @Column(name = "seat_code")
    private String seatCode;

    public SeatTypeEntity(SeatType seatType) {
        this.seatType = seatType;
        this.seatCode = seatType.getCode(); // Set the code from the enum
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
        this.seatCode = seatType.getCode();
    }

}