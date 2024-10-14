package com.railway.master.repository;

import com.railway.master.enity.TrainCoachBasePriceEntity;
import com.railway.master.model.TrainCoachBasePriceDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainCoachBasePriceMappingRepository extends JpaRepository<TrainCoachBasePriceEntity, Long> {

    @Query(value = """
            SELECT new com.railway.master.model.TrainCoachBasePriceDetailModel(P.id, P.basePrice) from TrainCoachBasePriceEntity P 
            where P.trainEntity.id =:trainId and P.coachEntity.id =:coachId
                       """)
    TrainCoachBasePriceDetailModel getBasePrice(Long trainId, Long coachId);
}
