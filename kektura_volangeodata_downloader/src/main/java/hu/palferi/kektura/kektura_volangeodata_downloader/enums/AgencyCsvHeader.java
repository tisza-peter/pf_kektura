package hu.palferi.kektura.kektura_volangeodata_downloader.enums;

public enum AgencyCsvHeader {
    AGENCY_ID("agency_id", "agencyId"),
    AGENCY_NAME("agency_name", "agencyName"),
    AGENCY_URL("agency_url", "agencyUrl"),
    AGENCY_TIMEZONE("agency_timezone", "agencyTimezone"), 
    AGENCY_LANG("agency_lang",  "agencyLang"),
    AGENCY_PHONE("agency_phone", "agencyPhone");
    
    private String headerName;
    private String propertyName;



    AgencyCsvHeader(String headerName, String propertyName) {
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
