package com.railway.master.repository;

import com.railway.master.enity.mapping.TrainCoachMappingEntity;
import com.railway.master.model.CoachDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainCoachMappingRepository extends JpaRepository<TrainCoachMappingEntity, Long> {

    @Query(value = """
            SELECT new com.railway.master.model.CoachDetailModel(C.coachEntity.id, C.coachEntity.name) from TrainCoachMappingEntity C 
            where C.trainEntity.id =:id
                       """)
    List<CoachDetailModel> findAllByTrainId(Long id);
}
