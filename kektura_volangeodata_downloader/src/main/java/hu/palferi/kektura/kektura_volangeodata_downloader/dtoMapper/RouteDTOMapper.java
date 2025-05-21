package hu.palferi.kektura.kektura_volangeodata_downloader.dtoMapper;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.RouteBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.RouteDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.utils.MapperUtil;

public class RouteDTOMapper {
    
    public static RouteBO fromDTO(RouteDTO dto) {
        return RouteBO.builder()
                .agencyId(dto.getAgencyId())
                .routeId(dto.getRouteId())
                .routeShortName(dto.getRouteShortName())
                .routeLongName(dto.getRouteLongName())
                .routeType(MapperUtil.parseIntegerOrDefault(dto.getRouteType(),0))
                .routeUrl(dto.getRouteUrl())
                .routeColor(dto.getRouteColor())
                .routeTextColor(dto.getRouteTextColor())
                .routeNetwork(dto.getRouteNetwork())
                .build();
    }
}
