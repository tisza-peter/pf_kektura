package hu.palferi.kektura.kektura_volangeodata_downloader.enums;

public enum CalendarDatesCsvHeader {
    SERVICE_ID("service_id", "serviceId"),
    DATE("date", "date"),
    EXCEPTION_TYPE("exception_type", "exceptionType"),;

    private String headerName;
    private String propertyName;



    CalendarDatesCsvHeader(String headerName, String propertyName) {
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