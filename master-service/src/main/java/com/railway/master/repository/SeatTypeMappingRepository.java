package com.railway.master.repository;

import com.railway.master.enity.mapping.SeatTypeMappingEntity;
import com.railway.master.model.SeatDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatTypeMappingRepository extends JpaRepository<SeatTypeMappingEntity, Long> {

    @Query("""
            SELECT new com.railway.master.model.SeatDetailModel(S.seatEntity.id, S.seatTypeEntity.seatType) 
            FROM SeatTypeMappingEntity S
            JOIN CoachSeatMappingEntity C ON S.seatEntity.id = C.seatEntity.id
            WHERE C.coachEntity.id = :coachId
            """)
    List<SeatDetailModel> findAllSeatBy(Long coachId);
}
