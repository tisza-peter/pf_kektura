package hu.palferi.kektura.kektura_volangeodata_downloader.dtoMapper;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.StopBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.StopDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.utils.MapperUtil;

public class StopDTOMapper {

    public static StopBO fromDTO(StopDTO dto) {
        return StopBO.builder()
                .stopId(dto.getStopId())
                .stopName(dto.getStopName())
                .stopLat(MapperUtil.parseFloatOrDefault(dto.getStopLat()))
                .stopLon(MapperUtil.parseFloatOrDefault(dto.getStopLon()))
                .locationType(MapperUtil.parseIntegerOrDefault(dto.getLocationType(),0))
                .parentStation(dto.getParentStation())
                .platformCode(MapperUtil.parseIntegerOrDefault(dto.getPlatformCode(),0))
                .build();
    }
}
