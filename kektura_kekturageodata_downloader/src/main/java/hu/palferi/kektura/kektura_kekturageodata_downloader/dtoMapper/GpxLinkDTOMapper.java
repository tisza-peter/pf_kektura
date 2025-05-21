package hu.palferi.kektura.kektura_kekturageodata_downloader.dtoMapper;

import hu.palferi.kektura.kektura_kekturageodata_downloader.businessObject.GpxLinkBO;
import hu.palferi.kektura.kektura_kekturageodata_downloader.dto.GpxLinkDTO;

public class GpxLinkDTOMapper {

    public static GpxLinkBO fromDTO(GpxLinkDTO dto) {
        return GpxLinkBO.builder()
                .linkPath(dto.getLinkPath())
                .build();
    }
}
