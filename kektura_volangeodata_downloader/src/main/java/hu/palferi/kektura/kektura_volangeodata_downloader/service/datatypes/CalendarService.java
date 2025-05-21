package hu.palferi.kektura.kektura_volangeodata_downloader.service.datatypes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.CalendarBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.CalendarDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dtoMapper.CalendarDTOMapper;
import hu.palferi.kektura.kektura_volangeodata_downloader.entity.CalendarEntity;
import hu.palferi.kektura.kektura_volangeodata_downloader.entityMapper.CalendarEntityMapper;
import hu.palferi.kektura.kektura_volangeodata_downloader.utils.MapperUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CalendarService {

    private static final Logger logger = LoggerFactory.getLogger(CalendarService.class);

    @PersistenceContext
    private EntityManager entityManager;

    private static final int BATCH_SIZE = 500;

    @Transactional
    public void processCsvData(String[] columnNames, List<String[]> dataRows) {
        logger.info("Adatfeldolgozás elkezdődött. Sorok száma: {}", dataRows.size());

        int count = 0;

        for (int i = 0; i < dataRows.size(); i++) {
            String[] dataRow = dataRows.get(i);

            CalendarDTO calendarDTO = new CalendarDTO(
                    MapperUtil.emptyToNull(dataRow[0]), // service_id
                    MapperUtil.emptyToNull(dataRow[1]), // monday
                    MapperUtil.emptyToNull(dataRow[2]), // tuesday
                    MapperUtil.emptyToNull(dataRow[3]), // wednesday
                    MapperUtil.emptyToNull(dataRow[4]), // thursday
                    MapperUtil.emptyToNull(dataRow[5]), // friday
                    MapperUtil.emptyToNull(dataRow[6]), // saturday
                    MapperUtil.emptyToNull(dataRow[7]), // sunday
                    MapperUtil.emptyToNull(dataRow[8]), // start_date
                    MapperUtil.emptyToNull(dataRow[9]), // end_date
                    MapperUtil.emptyToNull(dataRow[10]) // service_desc
            );

            CalendarBO calendarBO = CalendarDTOMapper.fromDTO(calendarDTO);
            CalendarEntity calendarEntity = CalendarEntityMapper.fromBO(calendarBO);

            entityManager.persist(calendarEntity);
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
