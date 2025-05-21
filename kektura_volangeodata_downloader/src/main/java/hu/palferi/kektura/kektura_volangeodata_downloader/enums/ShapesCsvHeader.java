package hu.palferi.kektura.kektura_volangeodata_downloader.enums;

public enum ShapesCsvHeader {
    SHAPE_ID("shape_id", "shapeId"),
    SHAPE_PT_SEQUENCE("shape_pt_sequence", "shapePtSequence"),
    SHAPE_PT_LAT("shape_pt_lat", "shapePtLat"),
    SHAPE_PT_LON("shape_pt_lon", "shapePtLon"),
    SHAPE_DIST_TRAVELED("shape_dist_traveled", "shapeDistTraveled"),;

    private String headerName;
    private String propertyName;



    ShapesCsvHeader(String headerName, String propertyName) {
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