package com.railway.booking.repository;

import com.railway.booking.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    List<BookingEntity> findAllByTrainIdAndBookingDate(Long trainId, LocalDate journeyDate);

    @Query(value = """
                SELECT L.id FROM unnest(:ids) AS L(id) 
            LEFT JOIN 
            booking.ticket_booking B 
            ON B.train_id=:trainId and B.coach_id =:coachId and B.booking_date =:bookingDate and L.id = B.seat_id   
            where B.seat_id is null ORDER BY 
                                        CASE
                                            WHEN L.id % 2 = 1 THEN 0  -- Odd numbers first
                                            ELSE 1                 -- Even numbers second
                                        END,
                                        L.id;
                """, nativeQuery = true)
    List<Long> getAvailableSeat(Long trainId, Long coachId, @Param("ids") Long[] ids, LocalDate bookingDate);
}
