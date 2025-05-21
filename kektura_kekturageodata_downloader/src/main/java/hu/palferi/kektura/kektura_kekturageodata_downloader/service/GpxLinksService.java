package hu.palferi.kektura.kektura_kekturageodata_downloader.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import hu.palferi.kektura.kektura_kekturageodata_downloader.businessObject.GpxLinkBO;
import hu.palferi.kektura.kektura_kekturageodata_downloader.dto.GpxLinkDTO;
import hu.palferi.kektura.kektura_kekturageodata_downloader.dtoMapper.GpxLinkDTOMapper;
import hu.palferi.kektura.kektura_kekturageodata_downloader.entity.GpxLinkEntity;
import hu.palferi.kektura.kektura_kekturageodata_downloader.entityMapper.GpxLinkEntityMapper;
import hu.palferi.kektura.kektura_kekturageodata_downloader.repository.GpxLinkRepository;

@Service
public class GpxLinksService {

    private final GpxLinkRepository gpxLinkRepository;

    private final GpxService gpxService;
    GpxLinksService(GpxLinkRepository gpxLinkRepository, GpxService gpxService) {
        this.gpxLinkRepository = gpxLinkRepository;
        this.gpxService = gpxService;
    }

    public void refreshGpxLinks() {
        Set<String> allGpxFileLinks = gpxService.fetchGpxLinks();

        List<GpxLinkEntity> entities = new ArrayList<>();
        
        for (String gpxLink : allGpxFileLinks) {
            GpxLinkDTO gpxLinkDTO = new GpxLinkDTO(gpxLink);
            GpxLinkBO gpxLinkBO = GpxLinkDTOMapper.fromDTO(gpxLinkDTO);
            GpxLinkEntity gpxLinkEntity = GpxLinkEntityMapper.fromBO(gpxLinkBO);
            entities.add(gpxLinkEntity);    
        }
        gpxLinkRepository.deleteAll();
        gpxLinkRepository.saveAll(entities);

    }
}
