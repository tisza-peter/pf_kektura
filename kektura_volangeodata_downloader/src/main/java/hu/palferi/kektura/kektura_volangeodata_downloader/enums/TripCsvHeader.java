package hu.palferi.kektura.kektura_volangeodata_downloader.enums;

public enum TripCsvHeader {
    ROUTE_ID("route_id", "routeId"),
    TRIP_ID("trip_id", "tripId"),
    SERVICE_ID("service_id", "serviceId"),
    TRIP_SHORT_NAME("trip_short_name", "tripShortName"),
    TRIP_HEADSIGN("trip_headsign", "tripHeadsign"),
    DIRECTION_ID("direction_id", "directionId"),
    SHAPE_ID("shape_id", "shapeId"),
    WHEELCHAIR_ACCESSIBLE("wheelchair_accessible", "wheelchairAccessible"),;

    private String headerName;
    private String propertyName;



    TripCsvHeader(String headerName, String propertyName) {
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