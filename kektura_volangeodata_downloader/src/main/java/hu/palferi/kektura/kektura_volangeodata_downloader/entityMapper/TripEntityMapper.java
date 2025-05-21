package hu.palferi.kektura.kektura_volangeodata_downloader.entityMapper;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.TripBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.entity.TripEntity;

public class TripEntityMapper {

    public static TripEntity fromBO(TripBO bo) {
        return TripEntity.builder()
                .routeId(bo.getRouteId())
                .tripId(bo.getTripId())
                .serviceId(bo.getServiceId())
                .tripShortName(bo.getTripShortName())
                .tripHeadsign(bo.getTripHeadsign())
                .directionId(bo.getDirectionId())
                .shapeId(bo.getShapeId())
                .wheelchairAccessible(bo.getWheelchairAccessible())
                .build();
    }
}
