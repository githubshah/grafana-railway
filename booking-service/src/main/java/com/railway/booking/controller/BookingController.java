package com.railway.booking.controller;

import com.railway.booking.dto.BookingRequestDTO;
import com.railway.booking.dto.TrainTicket;
import com.railway.booking.service.TrainService;
import com.railway.booking.webclient.MasterServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class BookingController {

    @Autowired
    private MasterServiceClient masterServiceClient;

    @Autowired
    private TrainService trainService;

    @GetMapping("/{train_id}/{coach_id}")
    public Object getBasePrice1(@PathVariable("train_id") Long trainId, @PathVariable("coach_id") Long coachId) {
        return masterServiceClient.getBasePriceByTrainIdAndCoachId(trainId, coachId);
    }

    @PostMapping("/train")
    public Object bookTicket(@RequestBody BookingRequestDTO bookingRequestDTO) {
        return trainService.processBooking(bookingRequestDTO);
    }

    @GetMapping("/train/ticket/{id}")
    public TrainTicket bookTicket(@PathVariable("id") Long ticketId) {
        return trainService.getTrainTicket(ticketId);
    }
}
