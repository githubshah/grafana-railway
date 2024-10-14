package com.railway.booking.service;


import com.railway.booking.dto.BookingRequestDTO;
import com.railway.booking.dto.CoachSeatAvailability;
import com.railway.booking.dto.TrainTicket;

import java.time.LocalDate;

public interface TrainService {
    Object getTrainBetweenSourceAndDestination(Long src, Long dest);

    CoachSeatAvailability getTrainAvailableSeats(Long trainId, Long routeId, LocalDate journeyDate);

    Object processBooking(BookingRequestDTO bookingRequestDTO);

    TrainTicket getTrainTicket(Long ticketId);
}
