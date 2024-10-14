package com.railway.booking.controller;

import com.railway.booking.service.BookingMatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingMatrixController {

    @Autowired
    private BookingMatrixService bookingMatrixService;

    @GetMapping("/matrix")
    public Object getBookingMatrix() {
        return bookingMatrixService.getMatrix();
    }
}
