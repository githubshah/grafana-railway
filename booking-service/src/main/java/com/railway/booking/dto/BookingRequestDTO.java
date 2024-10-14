package com.railway.booking.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingRequestDTO {

    private Long trainId;
    private Long coachId;
    private Long routeId;
    private LocalDate bookingDate;

}
