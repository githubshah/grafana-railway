package com.railway.admin.webclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "BOOKING-SERVICE") // spring.application.name of callee service
public interface BookingServiceClient {
    @GetMapping("/booking/matrix")
    Object getBookingMatrix();
}