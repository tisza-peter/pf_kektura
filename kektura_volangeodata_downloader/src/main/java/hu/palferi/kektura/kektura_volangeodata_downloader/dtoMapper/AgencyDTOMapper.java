package hu.palferi.kektura.kektura_volangeodata_downloader.dtoMapper;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.AgencyBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.AgencyDTO;

public class AgencyDTOMapper {

    public static AgencyBO fromDTO(AgencyDTO dto) {
        return AgencyBO.builder()
                .agencyId(dto.getAgencyId())
                .agencyName(dto.getAgencyName())
                .agencyUrl(dto.getAgencyUrl())
                .agencyTimezone(dto.getAgencyTimezone())
                .agencyLang(dto.getAgencyLang())
                .agencyPhone(dto.getAgencyPhone())
                .build();
    }
}
