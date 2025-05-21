package hu.palferi.kektura.kektura_volangeodata_downloader.entityMapper;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.StopTimeBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.entity.StopTimeEntity;

public class StopTimeEntityMapper {

    public static StopTimeEntity fromBO(StopTimeBO bo) {
        return StopTimeEntity.builder()
                .tripId(bo.getTripId())
                .stopId(bo.getStopId())
                .arrivalTime(bo.getArrivalTime().toSeconds())
                .departureTime(bo.getDepartureTime().toSeconds())
                .stopSequence(bo.getStopSequence())
                .pickupType(bo.getPickupType())
                .dropOffType(bo.getDropOffType())
                .shapeDistTraveled(bo.getShapeDistTraveled())
                .build();
    }
}
