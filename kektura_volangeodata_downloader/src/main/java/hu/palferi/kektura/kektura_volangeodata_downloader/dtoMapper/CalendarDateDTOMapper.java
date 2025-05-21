package hu.palferi.kektura.kektura_volangeodata_downloader.dtoMapper;


import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.CalendarDateBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.CalendarDateDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.utils.MapperUtil;

public class CalendarDateDTOMapper {

    public static CalendarDateBO fromDTO(CalendarDateDTO dto) {
        return CalendarDateBO.builder()
                .serviceId(dto.getServiceId())
                .date(MapperUtil.parseLocalDateOrDefault(dto.getDate()))
                .exceptionType(MapperUtil.parseIntegerOrDefault(dto.getExceptionType(),0))
                .build();
    }
}
