package hu.palferi.kektura.kektura_volangeodata_downloader.service.datatypes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.ShapeBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.ShapeDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dtoMapper.ShapeDTOMapper;
import hu.palferi.kektura.kektura_volangeodata_downloader.entity.ShapeEntity;
import hu.palferi.kektura.kektura_volangeodata_downloader.entityMapper.ShapeEntityMapper;
import hu.palferi.kektura.kektura_volangeodata_downloader.utils.MapperUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ShapeService {

    private static final Logger logger = LoggerFactory.getLogger(ShapeService.class);

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
            ShapeDTO shapeDTO = new ShapeDTO(
                    MapperUtil.emptyToNull(dataRow[0]), // shape_id
                    MapperUtil.emptyToNull(dataRow[1]), // shape_pt_sequence
                    MapperUtil.emptyToNull(dataRow[2]), // shape_pt_lat
                    MapperUtil.emptyToNull(dataRow[3]), // shape_pt_lon
                    MapperUtil.emptyToNull(dataRow[4])  // shape_dist_traveled
            );

            // DTO → BO → Entity
            ShapeBO shapeBO = ShapeDTOMapper.fromDTO(shapeDTO);
            ShapeEntity shapeEntity = ShapeEntityMapper.fromBO(shapeBO);

            entityManager.persist(shapeEntity);
            count++;

            if (count % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
                logger.debug("Batch mentve: {} rekord", count);
            }
        }

        // Utolsó batch flush, ha maradt még menteni való
        entityManager.flush();
        entityManager.clear();

        logger.info("Adatmentés sikeresen befejeződött. Összesen {} rekord került mentésre.", count);
    }
}

