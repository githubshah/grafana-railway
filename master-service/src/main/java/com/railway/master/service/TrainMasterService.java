package com.railway.master.service;

import com.railway.master.dto.TrainInfoDTO;
import com.railway.master.model.FarePolicyModel;
import com.railway.master.model.TrainCoachBasePriceDetailModel;
import com.railway.master.model.TrainDetailModel;

import java.util.List;

public interface TrainMasterService {
    List<TrainDetailModel> getAllTrainBy(Long from, Long to);

    TrainInfoDTO getTrainById(Long id, Long routeId);

    TrainCoachBasePriceDetailModel getBasePrice(Long trainId, Long coachId);

    List<FarePolicyModel> getPolicy();

    TrainDetailModel getTrainRouteId(Long id);
}
