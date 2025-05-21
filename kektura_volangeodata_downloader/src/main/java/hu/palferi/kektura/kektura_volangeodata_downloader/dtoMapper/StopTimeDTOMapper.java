package hu.palferi.kektura.kektura_volangeodata_downloader.dtoMapper;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.StopTimeBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.StopTimeDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.utils.MapperUtil;

public class StopTimeDTOMapper {

    public static StopTimeBO fromDTO(StopTimeDTO dto) {
        return StopTimeBO.builder()
                .tripId(dto.getTripId())
                .stopId(dto.getStopId())
                .arrivalTime(MapperUtil.parseDurationOrDefault(dto.getArrivalTime()))
                .departureTime(MapperUtil.parseDurationOrDefault(dto.getDepartureTime()))
                .stopSequence(MapperUtil.parseIntegerOrDefault(dto.getStopSequence(),null))
                .pickupType(MapperUtil.parseIntegerOrDefault(dto.getPickupType(),0))
                .dropOffType(MapperUtil.parseIntegerOrDefault(dto.getDropOffType(),0))
                .shapeDistTraveled(MapperUtil.parseFloatOrDefault(dto.getShapeDistTraveled()))
                .build();
    }
}
