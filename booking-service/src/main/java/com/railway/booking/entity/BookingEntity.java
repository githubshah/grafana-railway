package com.railway.booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "ticket_booking")
public class BookingEntity extends AuditableEntity<String> {

    @Id
    @GeneratedValue
    @Column(name = "booking_id")
    private Long id;

    @Column(name = "train_id")
    private Long trainId;

    @Column(name = "coach_id")
    private Long coachId;

    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "route_id")
    private Long routeId;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @Column(name = "berth_price")
    private Double berthPrice;

}