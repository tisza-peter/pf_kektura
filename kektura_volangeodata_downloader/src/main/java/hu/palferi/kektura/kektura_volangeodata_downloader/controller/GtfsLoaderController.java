package hu.palferi.kektura.kektura_volangeodata_downloader.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.palferi.kektura.kektura_volangeodata_downloader.enums.TableType;
import hu.palferi.kektura.kektura_volangeodata_downloader.service.DataLoaderService;
import hu.palferi.kektura.kektura_volangeodata_downloader.service.ZipService;

/**
 * Controller az adatok frissítéséhez
 */
@RestController
@RequestMapping("/api/gtfs-loader")
public class GtfsLoaderController {

    private static final Logger logger = LoggerFactory.getLogger(GtfsLoaderController.class);
    
    private final ZipService zipService;
    private final DataLoaderService dataLoaderService;

    public GtfsLoaderController(ZipService zipService, DataLoaderService dataLoaderService) {
        this.zipService = zipService;
        this.dataLoaderService = dataLoaderService;
    }


    @PostMapping("/download")
    public ResponseEntity<String> downloadZip() {
        logger.info("ZIP fájl letöltése indítása");
        try {
            zipService.downloadZip();
            return ResponseEntity.ok("ZIP fájl letöltve.");
        } catch (Exception e) {
            logger.error("Hiba a ZIP fájl letöltése során", e);
            return ResponseEntity.internalServerError().body("Hiba a letöltés közben: " + e.getMessage());
        }
    }

    @PostMapping("/unzip")
    public ResponseEntity<String> unzip() {
        logger.info("Kitömörítés indítása");
        try {
            zipService.unzip();
            return ResponseEntity.ok("Kitömörítés kész.");
        } catch (Exception e) {
            logger.error("Hiba a kitömörítés során", e);
            return ResponseEntity.internalServerError().body("Hiba a feldolgozás során: " + e.getMessage());
        }
    }

    @PostMapping("/split")
    public ResponseEntity<String> split() {
        logger.info("Darabolás indítása");
        try {
            zipService.split();
            return ResponseEntity.ok("Darabolás kész.");
        } catch (Exception e) {
            logger.error("Hiba a darabolás során", e);
            return ResponseEntity.internalServerError().body("Hiba a feldolgozás során: " + e.getMessage());
        }
    }

    @GetMapping("/check-csv-headers")
    public ResponseEntity<String> checkCsvHeaders() {
        logger.info("CSV fejléc ellenőrzése indítása");
        try {
            zipService.checkCsvHeaders();
            return ResponseEntity.ok("CSV fejléc ellenőrzés kész.");
        } catch (Exception e) {
            logger.error("Hiba a CSV fejléc ellenőrzése során", e);
            return ResponseEntity.internalServerError().body("Hiba a feldolgozás során: " + e.getMessage());
        }
    }

    @PostMapping("/load")
    public ResponseEntity<String> loadData(
            @RequestParam(name = "type", defaultValue = "FULL") TableType tableType,
            @RequestParam(name = "from", defaultValue = "0") int from,
            @RequestParam(name = "to", defaultValue = "100") int to
    ) {
        logger.info("Adatok betöltése indítása. Típus: {}, From: {}, To: {}", tableType, from, to);
        try {
            dataLoaderService.loadDataPart(tableType, from, to);
            return ResponseEntity.ok("Betöltés sikeres.");
        } catch (Exception e) {
            logger.error("Hiba a betöltés során", e);
            return ResponseEntity.internalServerError().body("Hiba a betöltés során: " + e.getMessage());
        }
    }

    @PostMapping("/load-all")
    public String loadAllData() {
        dataLoaderService.loadDataAll();
        return "Adatbetöltés elindítva";
    }

}