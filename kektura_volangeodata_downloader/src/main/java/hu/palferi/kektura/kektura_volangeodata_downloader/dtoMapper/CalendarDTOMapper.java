package hu.palferi.kektura.kektura_volangeodata_downloader.dtoMapper;



import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.CalendarBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.CalendarDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.utils.MapperUtil;

public class CalendarDTOMapper {

    public static CalendarBO fromDTO(CalendarDTO dto) {
        return CalendarBO.builder()
                .serviceId(dto.getServiceId())
                .monday(MapperUtil.parseIntegerOrDefault(dto.getMonday(),0))
                .tuesday(MapperUtil.parseIntegerOrDefault(dto.getTuesday(),0))
                .wednesday(MapperUtil.parseIntegerOrDefault(dto.getWednesday(),0))
                .thursday(MapperUtil.parseIntegerOrDefault(dto.getThursday(),0))
                .friday(MapperUtil.parseIntegerOrDefault(dto.getFriday(),0))
                .saturday(MapperUtil.parseIntegerOrDefault(dto.getSaturday(),0))
                .sunday(MapperUtil.parseIntegerOrDefault(dto.getSunday(),0))
                .startDate(MapperUtil.parseLocalDateOrDefault(dto.getStartDate()))
                .endDate(MapperUtil.parseLocalDateOrDefault(dto.getEndDate()))
                .serviceDesc(dto.getServiceDesc())
                .build();
    }
}
