package hu.palferi.kektura.kektura_volangeodata_downloader.service.datatypes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.FeedInfoBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.FeedInfoDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dtoMapper.FeedInfoDTOMapper;
import hu.palferi.kektura.kektura_volangeodata_downloader.entity.FeedInfoEntity;
import hu.palferi.kektura.kektura_volangeodata_downloader.entityMapper.FeedInfoEntityMapper;
import hu.palferi.kektura.kektura_volangeodata_downloader.utils.MapperUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class FeedInfoService {

    private static final Logger logger = LoggerFactory.getLogger(FeedInfoService.class);

    @PersistenceContext
    private EntityManager entityManager;

    private static final int BATCH_SIZE = 500;

    @Transactional
    public void processCsvData(String[] columnNames, List<String[]> dataRows) {
        logger.info("Adatfeldolgozás elkezdődött. Sorok száma: {}", dataRows.size());
        
        int count = 0;

        for (int i = 0; i < dataRows.size(); i++) {
            String[] dataRow = dataRows.get(i);

            FeedInfoDTO feedInfoDTO = new FeedInfoDTO(
                    MapperUtil.emptyToNull(dataRow[0]), // feed_id
                    MapperUtil.emptyToNull(dataRow[1]), // feed_publisher_name
                    MapperUtil.emptyToNull(dataRow[2]), // feed_publisher_url
                    MapperUtil.emptyToNull(dataRow[3]), // feed_lang
                    MapperUtil.emptyToNull(dataRow[4]), // feed_start_date
                    MapperUtil.emptyToNull(dataRow[5]), // feed_end_date
                    MapperUtil.emptyToNull(dataRow[6]), // feed_version
                    MapperUtil.emptyToNull(dataRow[7])  // feed_includes_prefixed_ids
            );

            FeedInfoBO feedInfoBO = FeedInfoDTOMapper.fromDTO(feedInfoDTO);
            FeedInfoEntity feedInfoEntity = FeedInfoEntityMapper.fromBO(feedInfoBO);

            entityManager.persist(feedInfoEntity);
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
