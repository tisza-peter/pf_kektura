package hu.palferi.kektura.kektura_volangeodata_downloader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@AllArgsConstructor
@Getter
public class AgencyDTO {
    private String agencyId;
    private String agencyName;
    private String agencyUrl;
    private String agencyTimezone;
    private String agencyLang;
    private String agencyPhone;
}
