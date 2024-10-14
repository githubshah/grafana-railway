package com.railway.master.repository;

import com.railway.master.enity.FarePolicyEntity;
import com.railway.master.model.FarePolicyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarePolicyRepository extends JpaRepository<FarePolicyEntity, Long> {

    @Query(value = "SELECT new com.railway.master.model.FarePolicyModel(P.coachPercentageFilled, P.increasePriceBy) from FarePolicyEntity P")
    List<FarePolicyModel> getFarePolicy();

}
