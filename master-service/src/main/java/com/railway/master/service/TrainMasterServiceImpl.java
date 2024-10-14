package com.railway.master.service;

import com.railway.master.dto.CoachInfoDTO;
import com.railway.master.dto.SeatInfo;
import com.railway.master.dto.TrainInfoDTO;
import com.railway.master.enity.TrainEntity;
import com.railway.master.model.CoachDetailModel;
import com.railway.master.model.FarePolicyModel;
import com.railway.master.model.TrainCoachBasePriceDetailModel;
import com.railway.master.model.TrainDetailModel;
import com.railway.master.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainMasterServiceImpl implements TrainMasterService {

    @Autowired
    private TrainRouteMappingRepository trainRouteMappingRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private TrainCoachMappingRepository trainCoachMappingRepository;

    @Autowired
    private SeatTypeMappingRepository seatTypeMappingRepository;

    @Autowired
    private TrainCoachBasePriceMappingRepository trainCoachBasePriceMappingRepository;

    @Autowired
    private FarePolicyRepository farePolicyRepository;


    @Override
    public List<TrainDetailModel> getAllTrainBy(Long from, Long to) {
        return trainRouteMappingRepository.findAllTrainBy(from, to);
    }

    @Override
    public TrainInfoDTO getTrainById(Long trainId, Long routeId) {

        TrainInfoDTO trainInfo = new TrainInfoDTO();
        Optional<TrainEntity> byId = trainRepository.findById(trainId);
        TrainEntity trainEntity = byId.orElseThrow(() -> new RuntimeException("Train not found"));

        // set train info
        trainInfo.setTrainId(trainEntity.getId());
        trainInfo.setTrainName(trainEntity.getName());

        // set route info
        TrainDetailModel trainRoute = getTrainRouteId(routeId);
        trainInfo.setRouteId(trainRoute.getRouteId());
        trainInfo.setRouteName(trainRoute.getRouteName());
        trainInfo.setDepartTime(trainRoute.getDepartTime());
        trainInfo.setArrivalTime(trainRoute.getArrivalTime());

        // set coach and seat info
        List<CoachDetailModel> coachDetailModelList = trainCoachMappingRepository.findAllByTrainId(trainId);

        List<CoachInfoDTO> coachInfoDTOList = coachDetailModelList.stream()
                .map(coachDetailModel -> {
                    CoachInfoDTO coachInfoDTO = new CoachInfoDTO();
                    coachInfoDTO.setCoachId(coachDetailModel.getCoachId());
                    coachInfoDTO.setCoachName(coachDetailModel.getCoachName());

                    List<SeatInfo> seatInfoDTOList = seatTypeMappingRepository.findAllSeatBy(coachDetailModel.getCoachId())
                            .stream()
                            .map(x -> {
                                SeatInfo seatInfo = new SeatInfo();
                                seatInfo.setSeatId(x.getSeatId());
                                seatInfo.setSeatType(x.getSeatType());
                                return seatInfo;
                            })
                            .collect(Collectors.toList());

                    coachInfoDTO.setSeats(seatInfoDTOList);
                    return coachInfoDTO;
                })
                .collect(Collectors.toList());

        trainInfo.setCoaches(coachInfoDTOList);
        return trainInfo;

    }

    @Override
    public TrainCoachBasePriceDetailModel getBasePrice(Long trainId, Long coachId) {
        return trainCoachBasePriceMappingRepository.getBasePrice(trainId, coachId);
    }

    @Override
    public List<FarePolicyModel> getPolicy() {
        return farePolicyRepository.getFarePolicy();
    }

    @Override
    public TrainDetailModel getTrainRouteId(Long id) {
        return trainRouteMappingRepository.findByRouteId(id).get(0);
    }
}
