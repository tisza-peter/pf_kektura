package hu.palferi.kektura.kektura_volangeodata_downloader.entityMapper;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.AgencyBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.entity.AgencyEntity;

public class AgencyEntityMapper {

    public static AgencyEntity fromBO(AgencyBO bo) {
        return AgencyEntity.builder()
                .agencyId(bo.getAgencyId())
                .agencyName(bo.getAgencyName())
                .agencyUrl(bo.getAgencyUrl())
                .agencyTimezone(bo.getAgencyTimezone())
                .agencyLang(bo.getAgencyLang())
                .agencyPhone(bo.getAgencyPhone())
                .build();
    }
}
