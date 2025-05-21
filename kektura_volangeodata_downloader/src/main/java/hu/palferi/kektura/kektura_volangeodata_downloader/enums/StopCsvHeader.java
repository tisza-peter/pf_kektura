package hu.palferi.kektura.kektura_volangeodata_downloader.enums;

public enum StopCsvHeader {
    STOP_ID("stop_id", "stopId"),
    STOP_NAME("stop_name", "stopName"),
    STOP_LAT("stop_lat", "stopLat"),
    STOP_LON("stop_lon", "stopLon"),
    LOCATION_TYPE("location_type", "locationType"),
    PARENT_STATION("parent_station", "parentStation"),
    PLATFORM_CODE("platform_code", "platformCode"),;

    private String headerName;
    private String propertyName;



    StopCsvHeader(String headerName, String propertyName) {
        this.headerName = headerName;
        this.propertyName = propertyName;
    };
    
    public String getHeaderName() {
        return headerName;
    }

    public String getPropertyName() {
        return propertyName;
    }

}