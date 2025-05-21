package hu.palferi.kektura.kektura_volangeodata_downloader.service.datatypes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.CalendarDateBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.CalendarDateDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dtoMapper.CalendarDateDTOMapper;
import hu.palferi.kektura.kektura_volangeodata_downloader.entity.CalendarDateEntity;
import hu.palferi.kektura.kektura_volangeodata_downloader.entityMapper.CalendarDateEntityMapper;
import hu.palferi.kektura.kektura_volangeodata_downloader.utils.MapperUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CalendarDateService {

    private static final Logger logger = LoggerFactory.getLogger(CalendarDateService.class);

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
            CalendarDateDTO calendarDateDTO = new CalendarDateDTO(
                    MapperUtil.emptyToNull(dataRow[0]), // service_id
                    MapperUtil.emptyToNull(dataRow[1]), // date
                    MapperUtil.emptyToNull(dataRow[2])  // exception_type
            );

            // DTO → BO → Entity
            CalendarDateBO calendarDateBO = CalendarDateDTOMapper.fromDTO(calendarDateDTO);
            CalendarDateEntity calendarDateEntity = CalendarDateEntityMapper.fromBO(calendarDateBO);

            entityManager.persist(calendarDateEntity);
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
