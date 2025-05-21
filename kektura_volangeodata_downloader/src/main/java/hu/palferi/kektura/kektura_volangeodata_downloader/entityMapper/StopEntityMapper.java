package hu.palferi.kektura.kektura_volangeodata_downloader.entityMapper;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.StopBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.entity.StopEntity;

public class StopEntityMapper {

    public static StopEntity fromBO(StopBO bo) {
        return StopEntity.builder()
                .stopId(bo.getStopId())
                .stopName(bo.getStopName())
                .stopLat(bo.getStopLat())
                .stopLon(bo.getStopLon())
                .locationType(bo.getLocationType())
                .parentStation(bo.getParentStation())
                .platformCode(bo.getPlatformCode())
                .build();
    }
}
