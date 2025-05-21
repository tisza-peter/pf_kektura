package hu.palferi.kektura.kektura_volangeodata_downloader.service.datatypes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.AgencyBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.AgencyDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dtoMapper.AgencyDTOMapper;
import hu.palferi.kektura.kektura_volangeodata_downloader.entity.AgencyEntity;
import hu.palferi.kektura.kektura_volangeodata_downloader.entityMapper.AgencyEntityMapper;
import hu.palferi.kektura.kektura_volangeodata_downloader.utils.MapperUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class AgencyService {

    private static final Logger logger = LoggerFactory.getLogger(AgencyService.class);

    @PersistenceContext
    private EntityManager entityManager;

    private static final int BATCH_SIZE = 500;

    @Transactional
    public void processCsvData(String[] columnNames, List<String[]> dataRows) {
        logger.info("Adatfeldolgozás elkezdődött. Sorok száma: {}", dataRows.size());


        int count = 0;

        for (int i = 0; i < dataRows.size(); i++) {
            String[] dataRow = dataRows.get(i);

            
            // DTO feltöltése a dataRow-ból
            AgencyDTO agencyDTO = new AgencyDTO(
                MapperUtil.emptyToNull(dataRow[0]), // agency_id
                MapperUtil.emptyToNull(dataRow[1]), // agency_name
                MapperUtil.emptyToNull(dataRow[2]), // agency_url
                MapperUtil.emptyToNull(dataRow[3]), // agency_timezone
                MapperUtil.emptyToNull(dataRow[4]), // agency_lang
                MapperUtil.emptyToNull(dataRow[5])  // agency_phone
            );

            // DTO → BO → Entity
            AgencyBO agencyBO = AgencyDTOMapper.fromDTO(agencyDTO);
            AgencyEntity agencyEntity = AgencyEntityMapper.fromBO(agencyBO);

            entityManager.persist(agencyEntity);
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
