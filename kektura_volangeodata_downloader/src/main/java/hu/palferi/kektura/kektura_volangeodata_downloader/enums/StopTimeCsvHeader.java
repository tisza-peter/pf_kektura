package hu.palferi.kektura.kektura_volangeodata_downloader.enums;

public enum StopTimeCsvHeader {
    TRIP_ID("trip_id", "tripId"),
    STOP_ID("stop_id", "stopId"),
    ARRIVAL_TIME("arrival_time", "arrivalTime"),
    DEPARTURE_TIME("departure_time", "departureTime"),
    STOP_SEQUENCE("stop_sequence", "stopSequence"),
    PICKUP_TYPE("pickup_type", "pickupType"),
    DROP_OFF_TYPE("drop_off_type", "dropOffType"),
    SHAPE_DIST_TRAVELED("shape_dist_traveled", "shapeDistTraveled"),;

    private String headerName;
    private String propertyName;



    StopTimeCsvHeader(String headerName, String propertyName) {
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