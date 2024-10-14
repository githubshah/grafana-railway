package com.railway.booking.service;

import com.railway.booking.entity.BookingEntity;
import com.railway.booking.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookingMatrixServiceImpl implements BookingMatrixService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<BookingEntity> getMatrix() {
        return bookingRepository.findAll();
    }
}
