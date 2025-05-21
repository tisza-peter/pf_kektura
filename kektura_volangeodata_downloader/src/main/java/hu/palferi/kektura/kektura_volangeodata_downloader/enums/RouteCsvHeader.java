package hu.palferi.kektura.kektura_volangeodata_downloader.enums;

public enum RouteCsvHeader {   
    AGENCY_ID("agency_id", "agencyId"),
    ROUTE_ID("route_id", "routeId"),
    ROUTE_SHORT_NAME("route_short_name", "routeShortName"),
    ROUTE_LONG_NAME("route_long_name", "routeLongName"),
    ROUTE_TYPE("route_type", "routeType"),
    ROUTE_URL("route_url", "routeUrl"),
    ROUTE_COLOR("route_color", "routeColor"),
    ROUTE_TEXT_COLOR("route_text_color", "routeTextColor"),
    ROUTE_NETWORK("route_network", "routeNetwork"),; 

    private String headerName;
    private String propertyName;



    RouteCsvHeader(String headerName, String propertyName) {
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
