package hu.palferi.kektura.kektura_volangeodata_downloader.enums;

public enum CalendarCsvHeader {
    SERVICE_ID("service_id", "serviceId"),
    MONDAY("monday", "monday"),
    TUESDAY("tuesday", "tuesday"),
    WEDNESDAY("wednesday", "wednesday"),
    THURSDAY("thursday", "thursday"),
    FRIDAY("friday", "friday"),
    SATURDAY("saturday", "saturday"),
    SUNDAY("sunday", "sunday"),
    START_DATE("start_date", "startDate"),
    END_DATE("end_date", "endDate"),
    SERVICE_DESC("service_desc", "serviceDesc");

    private String headerName;
    private String propertyName;



    CalendarCsvHeader(String headerName, String propertyName) {
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