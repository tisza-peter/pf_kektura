package hu.palferi.kektura.kektura_volangeodata_downloader.validator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import hu.palferi.kektura.kektura_volangeodata_downloader.enums.TableType;
import hu.palferi.kektura.kektura_volangeodata_downloader.utils.FileUtil;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CsvHeaderValidator {
    
    private String loadingDirPath;

    public String[] loadAndValidateCsvHeader(TableType tableType, Class<? extends Enum<?>> expectedHeaderClass) {
        Path path = Paths.get(loadingDirPath).resolve(tableType.getFileName() + ".txt");
        String[] columnNames = FileUtil.getCsvHeader(path);
        validateHeader(expectedHeaderClass, columnNames);
        return columnNames;
    }




    public static void validateHeader(Class<? extends Enum<?>> enumClass, String[] actualColumnNames) {
        List<String> expectedColumnNames = Arrays.stream(enumClass.getEnumConstants())
                .map(e -> {
                    try {
                        return (String) e.getClass().getMethod("getHeaderName").invoke(e);
                    } catch (Exception ex) {
                        throw new RuntimeException("Enum nem rendelkezik getHeaderName() methódussal", ex);
                    }
                })
                .collect(Collectors.toList());

        if (!expectedColumnNames.equals(Arrays.asList(actualColumnNames))) {
            throw new IllegalArgumentException("A CSV fejléc nem egyezik a várt oszlopnevekkel! "
                + "Várt: " + expectedColumnNames + ", Kapott: " + Arrays.toString(actualColumnNames));
        }
    }

}
