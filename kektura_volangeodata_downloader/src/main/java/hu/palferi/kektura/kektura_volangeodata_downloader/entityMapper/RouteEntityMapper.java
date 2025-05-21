package hu.palferi.kektura.kektura_volangeodata_downloader.entityMapper;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.RouteBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.entity.RouteEntity;

public class RouteEntityMapper {

    public static RouteEntity fromBO(RouteBO bo) {
        return RouteEntity.builder()
                .agencyId(bo.getAgencyId())
                .routeId(bo.getRouteId())
                .routeShortName(bo.getRouteShortName())
                .routeLongName(bo.getRouteLongName())
                .routeType(bo.getRouteType())
                .routeUrl(bo.getRouteUrl())
                .routeColor(bo.getRouteColor())
                .routeTextColor(bo.getRouteTextColor())
                .routeNetwork(bo.getRouteNetwork())
                .build();
    }
}
