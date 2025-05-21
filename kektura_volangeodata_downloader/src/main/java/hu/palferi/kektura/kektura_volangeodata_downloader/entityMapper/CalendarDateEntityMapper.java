package hu.palferi.kektura.kektura_volangeodata_downloader.entityMapper;

import java.sql.Date;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.CalendarDateBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.entity.CalendarDateEntity;

public class CalendarDateEntityMapper {

    public static CalendarDateEntity fromBO(CalendarDateBO bo) {
        return CalendarDateEntity.builder()
                .serviceId(bo.getServiceId())
                .date(Date.valueOf(bo.getDate()))
                .exceptionType(bo.getExceptionType())
                .build();
    }
}
