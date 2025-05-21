package hu.palferi.kektura.kektura_volangeodata_downloader.businessObject;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CalendarDateBO{
    private String serviceId;
    private LocalDate date;
    private Integer exceptionType;
}
