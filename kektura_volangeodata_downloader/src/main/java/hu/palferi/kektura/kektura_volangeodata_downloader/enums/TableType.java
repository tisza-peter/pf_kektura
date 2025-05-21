package hu.palferi.kektura.kektura_volangeodata_downloader.enums;

import java.util.Arrays;

public enum TableType {
    AGENCY("agency"),
    CALENDAR("calendar"),
    CALENDAR_DATES("calendar_dates"),
    FEED_INFO("feed_info"),
    ROUTES("routes"),
    SHAPES("shapes"),
    STOP_TIMES("stop_times"),
    STOPS("stops"),
    TRIPS("trips");

    private final String filename;

    TableType(String filename) {
        this.filename = filename;
    }

    public String getFileName() {
        return filename;
    }

    public static TableType fromFilename(String filename) {
        return Arrays.stream(values())
                .sorted((a, b) -> Integer.compare(b.filename.length(), a.filename.length())) // hossz szerint csökkenően
                .filter(t -> filename.startsWith(t.filename))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown table type in filename: " + filename));
    }
}


