package hu.palferi.kektura.kektura_volangeodata_downloader.service.datatypes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.StopBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.StopDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dtoMapper.StopDTOMapper;
import hu.palferi.kektura.kektura_volangeodata_downloader.entity.StopEntity;
import hu.palferi.kektura.kektura_volangeodata_downloader.entityMapper.StopEntityMapper;
import hu.palferi.kektura.kektura_volangeodata_downloader.utils.MapperUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class StopService {

    private static final Logger logger = LoggerFactory.getLogger(StopService.class);

    @PersistenceContext
    private EntityManager entityManager;

    private static final int BATCH_SIZE = 500;

    @Transactional
    public void processCsvData(String[] columnNames, List<String[]> dataRows) {
        logger.info("Adatfeldolgozás elkezdődött. Sorok száma: {}", dataRows.size());

        int count = 0;

        for (int i = 0; i < dataRows.size(); i++) {
            String[] dataRow = dataRows.get(i);

            // DTO létrehozása
            StopDTO stopDTO = new StopDTO(
                    MapperUtil.emptyToNull(dataRow[0]), // stop_id
                    MapperUtil.emptyToNull(dataRow[1]), // stop_name
                    MapperUtil.emptyToNull(dataRow[2]), // stop_lat
                    MapperUtil.emptyToNull(dataRow[3]), // stop_lon
                    MapperUtil.emptyToNull(dataRow[4]), // location_type
                    MapperUtil.emptyToNull(dataRow[5]), // parent_station
                    MapperUtil.emptyToNull(dataRow[6])  // platform_code
            );

            // DTO → BO → Entity
            StopBO stopBO = StopDTOMapper.fromDTO(stopDTO);
            StopEntity stopEntity = StopEntityMapper.fromBO(stopBO);

            entityManager.persist(stopEntity);
            count++;

            if (count % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
                logger.debug("Batch mentve: {} rekord", count);
            }
        }

        entityManager.flush();
        entityManager.clear();

        logger.info("Adatmentés sikeresen befejeződött. Összesen {} rekord került mentésre.", count);
    }
}
