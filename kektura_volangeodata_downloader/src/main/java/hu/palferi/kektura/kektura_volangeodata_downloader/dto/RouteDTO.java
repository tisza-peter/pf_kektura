package hu.palferi.kektura.kektura_volangeodata_downloader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class RouteDTO {
    private String agencyId;
    private String routeId;
    private String routeShortName;
    private String routeLongName;
    private String routeType;
    private String routeUrl;
    private String routeColor;
    private String routeTextColor;
    private String routeNetwork;
}
