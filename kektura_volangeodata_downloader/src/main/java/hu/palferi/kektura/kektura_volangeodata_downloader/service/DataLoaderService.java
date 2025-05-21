package hu.palferi.kektura.kektura_volangeodata_downloader.service;


import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.palferi.kektura.kektura_volangeodata_downloader.enums.TableType;
import hu.palferi.kektura.kektura_volangeodata_downloader.service.datatypes.AgencyService;
import hu.palferi.kektura.kektura_volangeodata_downloader.service.datatypes.CalendarDateService;
import hu.palferi.kektura.kektura_volangeodata_downloader.service.datatypes.CalendarService;
import hu.palferi.kektura.kektura_volangeodata_downloader.service.datatypes.FeedInfoService;
import hu.palferi.kektura.kektura_volangeodata_downloader.service.datatypes.RouteService;
import hu.palferi.kektura.kektura_volangeodata_downloader.service.datatypes.ShapeService;
import hu.palferi.kektura.kektura_volangeodata_downloader.service.datatypes.StopService;
import hu.palferi.kektura.kektura_volangeodata_downloader.service.datatypes.StopTimeService;
import hu.palferi.kektura.kektura_volangeodata_downloader.service.datatypes.TripService;
import jakarta.transaction.Transactional;

@Service
public class DataLoaderService {

    private static final Logger logger = LoggerFactory.getLogger(DataLoaderService.class);

    @Value("${data.refresh.work.loading.dir}")
    private String loadingDirPath;

    @Autowired private AgencyService agencyService;
    @Autowired private CalendarService calendarService;
    @Autowired private CalendarDateService calendarDateService;
    @Autowired private FeedInfoService feedInfoService;
    @Autowired private RouteService routeService;
    @Autowired private ShapeService shapeService;
    @Autowired private StopTimeService stopTimeService;
    @Autowired private StopService stopService;
    @Autowired private TripService tripService;

    @Transactional
    public void loadDataPart(TableType tableType, int from, int to) {
        String prefix = tableType.getFileName();
        String tableName = prefix;
    
        List<Path> filesToDelete = new ArrayList<>();
        Path baseDir = Paths.get(loadingDirPath);
    
        Runtime runtime = Runtime.getRuntime(); // Runtime példány a memóriahasználat logolásához


        for (int i = from; i <= to; i++) {
            String fileName = prefix + "_part" + i + ".txt";
            Path filePath = baseDir.resolve(fileName);
    
            if (!Files.exists(filePath)) {
                logger.warn("Fájl nem található: {}", filePath.toAbsolutePath());
                throw new RuntimeException("Hiányzó fájl: " + filePath.toAbsolutePath());
            }

            List<String[]> allRows = null;
            List<String[]> dataRows = null;
    
            try (
                Reader fileReader = new InputStreamReader(Files.newInputStream(filePath), StandardCharsets.UTF_8);
                CSVReader csvReader = new CSVReader(fileReader)
            ) {
                allRows = csvReader.readAll();

                if (allRows.isEmpty()) {
                    logger.warn("Üres fájl: {}", filePath);
                    continue;
                }

                String[] columnNames = allRows.get(0);
                dataRows = allRows.subList(1, allRows.size());

                if (dataRows.isEmpty()) {
                    logger.warn("Üres adat a fájlban: {}", filePath);
                    continue;
                }

                dispatchToService(tableName, columnNames, dataRows);
                filesToDelete.add(filePath);

            } catch (IOException e) {
                logger.error("IO hiba fájl olvasásnál: {}", filePath, e);
                e.printStackTrace();
            } catch (CsvException e) {
                logger.error("CSV hiba fájl olvasásnál: {}", filePath, e);
                e.printStackTrace();
            }finally {
                allRows=null;
                dataRows=null;

            // Memóriahasználat logolása
            long usedMemory = runtime.totalMemory() - runtime.freeMemory();
            logger.info("Memóriahasználat fájl feldolgozása után: {} MB", usedMemory / (1024 * 1024));

            }

        }

        // Csak akkor töröljük a fájlokat, ha minden sikerült
        for (Path path : filesToDelete) {
            try {
                Files.delete(path);
                logger.info("Feldolgozott fájl törölve: {}", path);
            } catch (IOException e) {
                logger.warn("Nem sikerült törölni a fájlt: {}", path, e);
            }
        }
    }

    private void dispatchToService(String tableName, String[] columnNames, List<String[]> dataRows) {
        // A táblanevek és a szolgáltatások közötti összekapcsolás
        logger.info("Feldolgozás: {} tábla, oszlopok: {}", tableName, String.join(", ", columnNames));
        logger.info("Adatsorok száma: {}", dataRows.size());
        logger.info("Adatsorok: {}", dataRows.size() > 0 ? dataRows.get(0).length : 0); // Első sor oszlopainak száma
        switch (tableName) {
            case "agency":
                agencyService.processCsvData(columnNames, dataRows);
                break;
            case "calendar":
                calendarService.processCsvData(columnNames, dataRows);
                break;
            case "calendar_dates":
                calendarDateService.processCsvData(columnNames, dataRows);
                break;
            case "feed_info":
                feedInfoService.processCsvData(columnNames, dataRows);
                break;
            case "routes":
                routeService.processCsvData(columnNames, dataRows);
                break;
            case "shapes":
                shapeService.processCsvData(columnNames, dataRows);
                break;
            case "stop_times":
                stopTimeService.processCsvData(columnNames, dataRows);
                break;
            case "stops":
                stopService.processCsvData(columnNames, dataRows);
                break;
            case "trips":
                tripService.processCsvData(columnNames, dataRows);
                break;
            default:
                throw new IllegalArgumentException("Ismeretlen tábla: " + tableName);
        }
    }

    public void loadDataAll() {
        File dir = new File(loadingDirPath);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new RuntimeException("Nem létező könyvtár: " + loadingDirPath);
        }
        if (!dir.canRead()) {
            throw new RuntimeException("Nincs olvasási jog a könyvtárhoz: " + loadingDirPath);
        }
        if (!dir.canWrite()) {
            throw new RuntimeException("Nincs írási jog a könyvtárhoz: " + loadingDirPath);
        }
        Arrays.stream(dir.listFiles())
                .filter(File::isFile)
                .filter(file -> file.getName().endsWith(".txt"))
                .sorted()
                .forEach(file -> {
                    String filename = file.getName();
                    try {
                        TableType tableType = TableType.fromFilename(filename);
                        int partNumber = extractPartNumber(filename);
                        loadDataPart(tableType, partNumber, partNumber);
                    } catch (Exception e) {
                        System.err.println("Hiba a fájl feldolgozásakor: " + filename + " - " + e.getMessage());
                    }
                });
    }

    private int extractPartNumber(String filename) {
        // Pl.: calendar_dates_part2.txt → 2
        String partPattern = "_part(\\d+)\\.txt$";
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(partPattern).matcher(filename);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        } else {
            throw new IllegalArgumentException("Nem található part szám a fájlnévben: " + filename);
        }
    }

}
