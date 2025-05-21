package hu.palferi.kektura.kektura_volangeodata_downloader.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


/**
 * A fájlok darabolására szolgáló osztály.
 * A fájlokat a megadott sorok számának megfelelően darabolja fel.
 */
public class FileUtil {

    private static final byte[] BUFFER = new byte[8192]; // Újrahasználható buffer I/O műveletekhez
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(FileUtil.class);
    
    /**
     * Ideiglenes könyvtár létrehozása
     */
    public static void createWorkDirectory(Path dir) throws IOException {
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }
    }

    

    public static void cleanupDirectory(Path dir) {
        if (!Files.exists(dir)) {
            logger.info("A könyvtár nem létezik, nincs mit törölni: {}", dir);
            return;
        }
        try {
            logger.info("Ideiglenes könyvtár törlése: {}", dir);
            Files.walk(dir)
                .sorted((p1, p2) -> -p1.compareTo(p2))
                .forEach(path -> {
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException e) {
                        logger.warn("Nem sikerült törölni: {}", path, e);
                    }
                });
        } catch (IOException e) {
            logger.warn("Hiba az ideiglenes könyvtár törlése közben", e);
        }
    }
    

    /**1
     * ZIP fájl letöltése buffered stream használatával
     */
    public static Path downloadZipFile(Path zipFilePath,String url) throws IOException {
        logger.info("ZIP fájl letöltése: {}", url);
        try (
            InputStream in = new URL(url).openStream();
            BufferedInputStream bis = new BufferedInputStream(in);
            FileOutputStream fos = new FileOutputStream(zipFilePath.toFile())
        ) {
            int bytesRead;
            while ((bytesRead = bis.read(BUFFER, 0, BUFFER.length)) != -1) {
                fos.write(BUFFER, 0, bytesRead);
            }
        }

        logger.info("ZIP fájl sikeresen letöltve: {}", zipFilePath);
        return zipFilePath;
    }




    public static void splitFiles(Path sourceDir, String allowedExtension, Path targetDir, int rowsPerPart) throws IOException {

        try (Stream<Path> files = Files.list(sourceDir)) {
            List<Path> filteredFiles = files
                    .filter(p -> Files.isRegularFile(p))
                    .filter(p -> p.toString().endsWith("." + allowedExtension))
                    .collect(Collectors.toList());

            for (Path filePath : filteredFiles) {
                splitFile(filePath, allowedExtension, targetDir, rowsPerPart);
            }
        }
    }

    private static void splitFile(Path filePath, String extension, Path targetDir, int rowsPerPart) throws IOException {
        String baseFileName = filePath.getFileName().toString().replace("." + extension, "");

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String header = reader.readLine();
            if (header == null) return;

            int partNumber = 1;
            int rowCount = 0;
            BufferedWriter writer = null;

            String line;
            while ((line = reader.readLine()) != null) {
                if (rowCount == 0) {
                    if (writer != null) writer.close();
                    String newFileName = baseFileName + "_part" + partNumber + "." + extension;
                    Path partFile = targetDir.resolve(newFileName);
                    writer = Files.newBufferedWriter(partFile);
                    writer.write(header);
                    writer.newLine();
                    partNumber++;
                }

                if (writer != null) {
                    writer.write(line);
                    writer.newLine();
                    rowCount++;
                }

                if (rowCount >= rowsPerPart) {
                    rowCount = 0;
                }
            }

            if (writer != null) {
                writer.close();
            }
        }
    }




    /**
     * ZIP fájl kicsomagolása optimalizált bufferrel
     */
    public static void extractZipFile(Path zipFilePath, Path destDir) throws IOException {
        logger.info("ZIP fájl kicsomagolása");
        int extractedFiles = 0;
        
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath.toFile()))) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                Path newFilePath = destDir.resolve(zipEntry.getName());
                
                // Könyvtárak kezelése
                if (zipEntry.isDirectory()) {
                    Files.createDirectories(newFilePath);
                } else {
                    // Fájlok kicsomagolása
                    Files.createDirectories(newFilePath.getParent());
                    
                    try (FileOutputStream fos = new FileOutputStream(newFilePath.toFile())) {
                        int bytesRead;
                        while ((bytesRead = zis.read(BUFFER)) > 0) {
                            fos.write(BUFFER, 0, bytesRead);
                        }
                    }
                    
                    extractedFiles++;
                    logger.debug("Fájl kicsomagolva: {}", newFilePath);
                }
                
                zis.closeEntry();
            }
        }
        
        logger.info("ZIP fájl kicsomagolása befejeződött, {} fájl kicsomagolva", extractedFiles);
    }
    
    /**
     * CSV fejléc beolvasása
     */
    public static String[] getCsvHeader(Path filePath) {
        String[] result = new String[0];

        try (
            Reader fileReader = new InputStreamReader(Files.newInputStream(filePath), StandardCharsets.UTF_8);
            CSVReader csvReader = new CSVReader(fileReader)
        ) {
            String[] header = csvReader.readNext();
            if (header != null) {
                result = header;
            } else {
                throw new IllegalArgumentException("A CSV fájl üres vagy nincs fejléc sor.");
            }
        } catch (IOException e) {
            logger.error("Hiba a CSV fejléc olvasása közben", e);
            throw new RuntimeException("Nem sikerült beolvasni a CSV fejlécet.", e);
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return result;
    }
}
