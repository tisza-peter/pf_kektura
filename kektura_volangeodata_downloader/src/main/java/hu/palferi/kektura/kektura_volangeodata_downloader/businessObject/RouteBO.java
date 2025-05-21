package hu.palferi.kektura.kektura_volangeodata_downloader.businessObject;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RouteBO {
    private String agencyId;
    private String routeId;
    private String routeShortName;
    private String routeLongName;
    private Integer routeType;
    private String routeUrl;
    private String routeColor;
    private String routeTextColor;
    private String routeNetwork;
}
