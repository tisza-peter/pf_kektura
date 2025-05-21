package hu.palferi.kektura.kektura_volangeodata_downloader.entityMapper;

import java.sql.Date;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.CalendarBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.entity.CalendarEntity;

public class CalendarEntityMapper {

    public static CalendarEntity fromBO(CalendarBO bo) {
        return CalendarEntity.builder()
                .serviceId(bo.getServiceId())
                .monday(bo.getMonday())
                .tuesday(bo.getTuesday())
                .wednesday(bo.getWednesday())
                .thursday(bo.getThursday())
                .friday(bo.getFriday())
                .saturday(bo.getSaturday())
                .sunday(bo.getSunday())
                .startDate(Date.valueOf(bo.getStartDate()))
                .endDate(Date.valueOf(bo.getEndDate()))
                .serviceDesc(bo.getServiceDesc())
                .build();
    }
}
