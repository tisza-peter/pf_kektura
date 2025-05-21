package hu.palferi.kektura.kektura_volangeodata_downloader.businessObject;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CalendarBO{
    private String serviceId;
    private Integer monday;
    private Integer tuesday;
    private Integer wednesday;
    private Integer thursday;
    private Integer friday;
    private Integer saturday;
    private Integer sunday;
    private LocalDate startDate;
    private LocalDate endDate;
    private String serviceDesc;
}
