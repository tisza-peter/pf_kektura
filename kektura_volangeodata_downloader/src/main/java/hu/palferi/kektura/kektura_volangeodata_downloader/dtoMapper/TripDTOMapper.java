package hu.palferi.kektura.kektura_volangeodata_downloader.dtoMapper;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.TripBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.TripDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.utils.MapperUtil;

public class TripDTOMapper {

    public static TripBO fromDTO(TripDTO dto) {
        return TripBO.builder()
                .routeId(dto.getRouteId())
                .tripId(dto.getTripId())
                .serviceId(dto.getServiceId())
                .tripShortName(dto.getTripShortName())
                .tripHeadsign(dto.getTripHeadsign())
                .directionId(MapperUtil.parseIntegerOrDefault(dto.getDirectionId(),null))
                .shapeId(dto.getShapeId())
                .wheelchairAccessible(MapperUtil.parseIntegerOrDefault(dto.getWheelchairAccessible(),0))
                .build();
    }
}
