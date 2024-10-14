package com.railway.master.repository;

import com.railway.master.dto.TrainInfoDTO;
import com.railway.master.enity.mapping.TrainRouteMappingEntity;
import com.railway.master.model.TrainDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRouteMappingRepository extends JpaRepository<TrainRouteMappingEntity, Long> {

    @Query(value = """
            SELECT new com.railway.master.model.TrainDetailModel(T.trainEntity.id, T.trainEntity.name, T.routeEntity.id, T.routeEntity.name, T.departTime, T.arrivalTime) from TrainRouteMappingEntity T 
            where T.routeEntity.sourceStationId.id =:from and T.routeEntity.destinationStationId.id =:to
                       """)
    List<TrainDetailModel> findAllTrainBy(Long from, Long to);

    @Query(value = """
            SELECT new com.railway.master.model.TrainDetailModel(T.trainEntity.id, T.trainEntity.name, T.routeEntity.id, T.routeEntity.name, T.departTime, T.arrivalTime) from TrainRouteMappingEntity T 
            where T.routeEntity.id =:id
                       """)
    List<TrainDetailModel> findByRouteId(Long id);
}
