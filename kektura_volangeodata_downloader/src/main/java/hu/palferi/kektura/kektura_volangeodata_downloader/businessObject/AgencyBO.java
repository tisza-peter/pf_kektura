package hu.palferi.kektura.kektura_volangeodata_downloader.businessObject;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AgencyBO {
    private String agencyId;
    private String agencyName;
    private String agencyUrl;
    private String agencyTimezone;
    private String agencyLang;
    private String agencyPhone;
}
