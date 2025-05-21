package hu.palferi.kektura.kektura_volangeodata_downloader.businessObject;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StopBO {
    private String stopId;
    private String stopName;
    private Float stopLat;
    private Float stopLon;
    private Integer locationType;
    private String parentStation;
    private Integer platformCode;
}
