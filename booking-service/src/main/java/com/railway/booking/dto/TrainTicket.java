package com.railway.booking.dto;

import com.railway.booking.constant.SeatType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class TrainTicket {
    private Long ticketId;
    private Long trainId;
    private String train;
    private String coach;
    private Long seatNo;
    private SeatType berth;
    private Long routeId;
    private String route;
    private LocalDate bookingDate;
    private Double berthPrice;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String from;
    private String to;
}