package hu.palferi.kektura.kektura_kekturageodata_downloader.entityMapper;

import org.springframework.stereotype.Component;

import hu.palferi.kektura.kektura_kekturageodata_downloader.businessObject.GpxLinkBO;
import hu.palferi.kektura.kektura_kekturageodata_downloader.entity.GpxLinkEntity;

@Component
public class GpxLinkEntityMapper {

    public static GpxLinkEntity fromBO(GpxLinkBO bo) {
        return GpxLinkEntity.builder()
                .linkPath(bo.getLinkPath())
                .build();
    }
}


