package hu.palferi.kektura.kektura_volangeodata_downloader.service;

import java.io.IOException;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.palferi.kektura.kektura_volangeodata_downloader.dto.AgencyDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.CalendarDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.CalendarDateDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.FeedInfoDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.RouteDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.ShapeDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.StopDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.StopTimeDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.TripDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.enums.AgencyCsvHeader;
import hu.palferi.kektura.kektura_volangeodata_downloader.enums.CalendarCsvHeader;
import hu.palferi.kektura.kektura_volangeodata_downloader.enums.CalendarDatesCsvHeader;
import hu.palferi.kektura.kektura_volangeodata_downloader.enums.FeedInfoCsvHeader;
import hu.palferi.kektura.kektura_volangeodata_downloader.enums.RouteCsvHeader;
import hu.palferi.kektura.kektura_volangeodata_downloader.enums.ShapesCsvHeader;
import hu.palferi.kektura.kektura_volangeodata_downloader.enums.StopCsvHeader;
import hu.palferi.kektura.kektura_volangeodata_downloader.enums.StopTimeCsvHeader;
import hu.palferi.kektura.kektura_volangeodata_downloader.enums.TableType;
import hu.palferi.kektura.kektura_volangeodata_downloader.enums.TripCsvHeader;
import hu.palferi.kektura.kektura_volangeodata_downloader.utils.FileUtil;
import hu.palferi.kektura.kektura_volangeodata_downloader.validator.CsvHeaderValidator;
import hu.palferi.kektura.kektura_volangeodata_downloader.validator.DtoPropertyValidator;

@Service
public class ZipService {

    private static final Logger logger = LoggerFactory.getLogger(ZipService.class);

    @Value("${data.refresh.zip.url}")
    private String zipFileUrl;

    @Value("${data.refresh.work.extract.batch.size}")
    private Integer batchSize;

    @Value("${data.refresh.work.extract.dir}")
    private String extractDirPath;

    @Value("${data.refresh.work.download.dir}")
    private String downloadDirPath;

    @Value("${data.refresh.work.loading.dir}")
    private String loadingDirPath;

    @Value("${data.refresh.work.zip.file.name}")
    private String zipFileName;

    public void createEmptyDirectory(String dirPath) {
        try {
            FileUtil.cleanupDirectory(Paths.get(dirPath));
            FileUtil.createWorkDirectory(Paths.get(dirPath));
        } catch (IOException e) {
            logger.error("Hiba a könyvtár létrehozása közben", e);
            e.printStackTrace();
        }
    }

    public void downloadZip() {
        createEmptyDirectory(downloadDirPath);
        try {
            FileUtil.downloadZipFile(Paths.get(downloadDirPath).resolve(zipFileName), zipFileUrl);
        } catch (IOException e) {
            logger.error("Hiba a ZIP fájl letöltése közben", e);
        }
    }

    public void unzip() {
        createEmptyDirectory(extractDirPath);
        try {
            FileUtil.extractZipFile(Paths.get(downloadDirPath).resolve(zipFileName),  Paths.get(extractDirPath));
        } catch (IOException e) {
            logger.error("Hiba a ZIP fájl kicsomagolása közben", e);
            e.printStackTrace();
        }
    }

    public void split() {
        createEmptyDirectory(loadingDirPath);
        try {
            FileUtil.splitFiles( Paths.get(extractDirPath),"txt", Paths.get(loadingDirPath), batchSize);
        } catch (IOException e) {
            logger.error("Hiba a fájlok darabolása közben", e);
            e.printStackTrace();
        }
    }

    public void checkCsvHeaders() {
        logger.info("CSV fejléc ellenőrzése indítása");
        CsvHeaderValidator csvValidator = new CsvHeaderValidator(extractDirPath);
        csvValidator.loadAndValidateCsvHeader(TableType.AGENCY, AgencyCsvHeader.class);
        csvValidator.loadAndValidateCsvHeader(TableType.CALENDAR, CalendarCsvHeader.class);
        csvValidator.loadAndValidateCsvHeader(TableType.CALENDAR_DATES, CalendarDatesCsvHeader.class);   
        csvValidator.loadAndValidateCsvHeader(TableType.FEED_INFO, FeedInfoCsvHeader.class);
        csvValidator.loadAndValidateCsvHeader(TableType.ROUTES, RouteCsvHeader.class);    
        csvValidator.loadAndValidateCsvHeader(TableType.SHAPES, ShapesCsvHeader.class);
        csvValidator.loadAndValidateCsvHeader(TableType.STOPS, StopCsvHeader.class);
        csvValidator.loadAndValidateCsvHeader(TableType.STOP_TIMES, StopTimeCsvHeader.class);
        csvValidator.loadAndValidateCsvHeader(TableType.TRIPS, TripCsvHeader.class);

                DtoPropertyValidator dtoValidator = new DtoPropertyValidator();
        dtoValidator.validateDtoFieldsAgainstEnum(AgencyDTO.class, AgencyCsvHeader.class);
        dtoValidator.validateDtoFieldsAgainstEnum(CalendarDTO.class, CalendarCsvHeader.class);
        dtoValidator.validateDtoFieldsAgainstEnum(CalendarDateDTO.class, CalendarDatesCsvHeader.class);
        dtoValidator.validateDtoFieldsAgainstEnum(FeedInfoDTO.class, FeedInfoCsvHeader.class);
        dtoValidator.validateDtoFieldsAgainstEnum(RouteDTO.class, RouteCsvHeader.class);
        dtoValidator.validateDtoFieldsAgainstEnum(ShapeDTO.class, ShapesCsvHeader.class);
        dtoValidator.validateDtoFieldsAgainstEnum(StopDTO.class, StopCsvHeader.class);
        dtoValidator.validateDtoFieldsAgainstEnum(StopTimeDTO.class, StopTimeCsvHeader.class);
        dtoValidator.validateDtoFieldsAgainstEnum(TripDTO.class, TripCsvHeader.class);
        logger.info("CSV fejléc ellenőrzése befejeződött");


    }

}
