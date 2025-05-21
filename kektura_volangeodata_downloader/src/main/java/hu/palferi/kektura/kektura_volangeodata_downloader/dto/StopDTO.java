package hu.palferi.kektura.kektura_volangeodata_downloader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@AllArgsConstructor
@Builder
@Getter
public class StopDTO {
    private String stopId;
    private String stopName;
    private String stopLat;
    private String stopLon;
    private String locationType;
    private String parentStation;
    private String platformCode;
}
