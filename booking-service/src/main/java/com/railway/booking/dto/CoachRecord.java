package com.railway.booking.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public record CoachRecord(Long id, String name, Long availableSeats, Double berthPrice,
                          @JsonIgnore List<Long> availableSeatList) {
}
