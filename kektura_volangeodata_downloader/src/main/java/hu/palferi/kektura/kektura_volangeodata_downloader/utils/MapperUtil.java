package hu.palferi.kektura.kektura_volangeodata_downloader.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MapperUtil {
    public static String emptyToNull(String value) {
        return (value == null || value.isEmpty()) ? null : value;
    }

    public static Integer parseIntegerOrDefault(String value, Integer defaultInteger) {
        return (value == null || value.isEmpty()) ? defaultInteger : Integer.parseInt(value);
    }

    public static Float parseFloatOrDefault(String value) {
        return (value == null || value.isEmpty()) ? null : Float.parseFloat(value);
    }

    public static Boolean parseBooleanOrDefault(String value) {
        return (value == null || value.isEmpty()) ? null : Boolean.parseBoolean(value);
    }

    public static LocalDate parseLocalDateOrDefault(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return (value == null || value.isEmpty()) ? null : LocalDate.parse(value, formatter);
    }

    public static Duration parseDurationOrDefault(String value) {
        return (value == null || value.isEmpty()) ? null : parseDuration(value);
    }

    public static Duration parseDuration(String value) {
        String[] parts = value.split(":");
        return Duration.ofHours(Long.parseLong(parts[0]))
                                    .plusMinutes(Long.parseLong(parts[1]))
                                    .plusSeconds(Long.parseLong(parts[2]));
    }

}
