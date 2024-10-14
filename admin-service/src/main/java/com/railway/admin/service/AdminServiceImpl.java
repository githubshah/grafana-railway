package com.railway.admin.service;

import com.railway.admin.webclient.BookingServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private BookingServiceClient bookingServiceClient;


    @Override
    public Object getMatrix() {
        return bookingServiceClient.getBookingMatrix();
    }
}
