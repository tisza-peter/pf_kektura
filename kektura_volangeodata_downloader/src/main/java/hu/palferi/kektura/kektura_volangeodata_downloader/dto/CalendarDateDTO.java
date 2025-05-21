
package hu.palferi.kektura.kektura_volangeodata_downloader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class CalendarDateDTO {
    private String serviceId;
    private String date;
    private String exceptionType;
}
