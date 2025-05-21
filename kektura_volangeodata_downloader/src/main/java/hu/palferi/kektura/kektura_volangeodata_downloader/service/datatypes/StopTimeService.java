package hu.palferi.kektura.kektura_volangeodata_downloader.service.datatypes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.StopTimeBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.StopTimeDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dtoMapper.StopTimeDTOMapper;
import hu.palferi.kektura.kektura_volangeodata_downloader.entity.StopTimeEntity;
import hu.palferi.kektura.kektura_volangeodata_downloader.entityMapper.StopTimeEntityMapper;
import hu.palferi.kektura.kektura_volangeodata_downloader.utils.MapperUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class StopTimeService {

    private static final Logger logger = LoggerFactory.getLogger(StopTimeService.class);

    @PersistenceContext
    private EntityManager entityManager;

    private static final int BATCH_SIZE = 500;

    @Transactional
    public void processCsvData(String[] columnNames, List<String[]> dataRows) {
        logger.info("Adatfeldolgozás elkezdődött. Sorok száma: {}", dataRows.size());

        int count = 0;

        for (int i = 0; i < dataRows.size(); i++) {
            String[] dataRow = dataRows.get(i);

            // DTO feltöltése
            StopTimeDTO stopTimeDTO = new StopTimeDTO(
                    MapperUtil.emptyToNull(dataRow[0]), // trip_id
                    MapperUtil.emptyToNull(dataRow[1]), // stop_id
                    MapperUtil.emptyToNull(dataRow[2]), // arrival_time
                    MapperUtil.emptyToNull(dataRow[3]), // departure_time
                    MapperUtil.emptyToNull(dataRow[4]), // stop_sequence
                    MapperUtil.emptyToNull(dataRow[5]), // pickup_type
                    MapperUtil.emptyToNull(dataRow[6]), // drop_off_type
                    MapperUtil.emptyToNull(dataRow[7])  // shape_dist_traveled
            );

            // DTO → BO → Entity
            StopTimeBO stopTimeBO = StopTimeDTOMapper.fromDTO(stopTimeDTO);
            StopTimeEntity stopTimeEntity = StopTimeEntityMapper.fromBO(stopTimeBO);

            entityManager.persist(stopTimeEntity);
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
