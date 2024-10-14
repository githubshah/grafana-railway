package com.railway.booking.service;

import com.railway.booking.constant.SeatType;
import com.railway.booking.dto.*;
import com.railway.booking.entity.BookingEntity;
import com.railway.booking.repository.BookingRepository;
import com.railway.booking.webclient.MasterServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TrainServiceImpl implements TrainService {

    @Autowired
    private MasterServiceClient masterServiceClient;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Object getTrainBetweenSourceAndDestination(Long src, Long dest) {
        return masterServiceClient.getTrainBetweenSourceAndDestination(src, dest);
    }

    @Override
    public CoachSeatAvailability getTrainAvailableSeats(Long trainId, Long routeId, LocalDate journeyDate) {
        CoachSeatAvailability coachSeatAvailability = new CoachSeatAvailability();
        TrainInfoDTO trainInfoDTO = masterServiceClient.getTrainInfoByTrainIdAndRouteId(trainId, routeId);
        List<CoachInfoDTO> coaches = trainInfoDTO.getCoaches();
        List<CoachRecord> coachDetails = coaches.stream()
                .map(coachInfoDTO -> {
                    List<Long> availableSeatList = bookingRepository.getAvailableSeat(trainId, coachInfoDTO.getCoachId(),
                            coachInfoDTO.getSeats().stream().map(SeatInfo::getSeatId).distinct().toArray(Long[]::new),
                            journeyDate);

                    long availableSeat = (long) availableSeatList.size();
                    int totalSeat = coachInfoDTO.getSeats().size();
                    long filledPercentage = Math.round((100.0 * (totalSeat - availableSeat)) / 100);

                    Double berthPrice = getBerthPrice(trainId, coachInfoDTO.getCoachId(), filledPercentage);

                    log.info("coachId: {}, coachName: {}, totalSeat: {}, availableSeat: {}, filledPercentage: {}, berthPrice: {}"
                            , coachInfoDTO.getCoachId(), coachInfoDTO.getCoachName(), totalSeat, availableSeat, filledPercentage, berthPrice);

                    return new CoachRecord(coachInfoDTO.getCoachId(), coachInfoDTO.getCoachName(), availableSeat, berthPrice, availableSeatList);
                }).collect(Collectors.toList());
        coachSeatAvailability.setCoachRecord(coachDetails);
        return coachSeatAvailability;
    }

    @Override
    public TrainTicket processBooking(BookingRequestDTO bookingRequestDTO) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setTrainId(bookingRequestDTO.getTrainId());
        bookingEntity.setBookingDate(bookingRequestDTO.getBookingDate());
        bookingEntity.setCoachId(bookingRequestDTO.getCoachId());
        bookingEntity.setCreatedOn(LocalDateTime.now());
        bookingEntity.setCreatedBy("USER");
        bookingEntity.setRouteId(bookingRequestDTO.getRouteId());

        CoachSeatAvailability trainAvailableSeats =
                getTrainAvailableSeats(bookingRequestDTO.getTrainId(), bookingRequestDTO.getRouteId(), bookingRequestDTO.getBookingDate());
        Optional<CoachRecord> first = trainAvailableSeats.getCoachRecord().stream().filter(x -> Objects.equals(x.id(), bookingRequestDTO.getCoachId())).findFirst();

        CoachRecord coach = first.orElseThrow(() -> new RuntimeException("no coach found"));
        bookingEntity.setBerthPrice(coach.berthPrice());

        if (coach.availableSeatList().isEmpty()) {
            throw new RuntimeException("no seat found");
        }
        bookingEntity.setSeatId(coach.availableSeatList().get(0));

        TrainDetailModel route = masterServiceClient.getTrainRouteByRoutId(bookingRequestDTO.getRouteId());

        bookingEntity.setArrivalTime(route.getArrivalTime());
        bookingEntity.setDepartureTime(route.getArrivalTime());
        BookingEntity ticket = bookingRepository.save(bookingEntity);

        return getTrainTicket(ticket.getId());
    }

    @Override
    public TrainTicket getTrainTicket(Long ticketId) {
        Optional<BookingEntity> byId = bookingRepository.findById(ticketId);
        BookingEntity bookingEntity = byId.orElseThrow(() -> new RuntimeException("no ticket found"));

        TrainInfoDTO trainById = masterServiceClient.getTrainInfoByTrainIdAndRouteId(bookingEntity.getTrainId(), bookingEntity.getRouteId());
        String trainName = trainById.getTrainName();

        Optional<CoachInfoDTO> coachInfoOptional = trainById.getCoaches().stream().filter(x -> x.getCoachId() == bookingEntity.getCoachId()).findFirst();
        CoachInfoDTO coachInfo = coachInfoOptional.orElseThrow(() -> new RuntimeException("no coach found"));
        String coachName = coachInfo.getCoachName();

        Optional<SeatInfo> seatInfoOptional = coachInfo.getSeats().stream().filter(x -> Objects.equals(x.getSeatId(), bookingEntity.getSeatId())).findFirst();
        SeatInfo seatinfo = seatInfoOptional.orElseThrow(() -> new RuntimeException("no seat found"));
        SeatType seatType = seatinfo.getSeatType();

        TrainTicket trainTicket = new TrainTicket();
        trainTicket.setTicketId(bookingEntity.getId());
        trainTicket.setTrainId(bookingEntity.getTrainId());
        trainTicket.setTrain(trainName);
        trainTicket.setCoach(coachName);
        trainTicket.setSeatNo(bookingEntity.getSeatId());
        trainTicket.setBerth(seatType);

        TrainDetailModel route = masterServiceClient.getTrainRouteByRoutId(bookingEntity.getRouteId());
        trainTicket.setRouteId(route.getRouteId());
        trainTicket.setRoute(route.getRouteName());

        trainTicket.setBookingDate(bookingEntity.getBookingDate());
        trainTicket.setDepartureTime(bookingEntity.getDepartureTime());
        trainTicket.setArrivalTime(bookingEntity.getArrivalTime());
        trainTicket.setBerthPrice(bookingEntity.getBerthPrice());

        return trainTicket;
    }

    private double getBerthPrice(Long trainId, Long coachId, long filledPercentage) {
        List<FarePolicyModel> farePolicy = masterServiceClient.getFarePolicy();

        TreeMap<Long, Long> map = new TreeMap<>();
        farePolicy.forEach(x -> {
            map.put(x.getCoachPercentageFilled(), x.getIncreasePriceBy());
        });

        double basePrice = masterServiceClient.getBasePriceByTrainIdAndCoachId(trainId, coachId).getBasePrice();

        Long filledPercentageWindow = map.floorKey(filledPercentage);
        if (Objects.nonNull(filledPercentageWindow)) {
            Long increasePriceByPercentage = map.get(filledPercentageWindow);
            basePrice = basePrice + ((basePrice * increasePriceByPercentage) / 100);
        }

        return basePrice;
    }
}
