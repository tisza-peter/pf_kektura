package hu.palferi.kektura.kektura_control_center.enums;

public enum MapServiceType {
    WMS,      // Web Map Service
    WFS,      // Web Feature Service
    WMTS,     // Web Map Tile Service
    TMS,      // Tile Map Service
    XYZ,      // XYZ Tile Layer
    GEOJSON,  // GeoJSON over HTTP
    IMAGE,    // Static image layer
    VECTOR,   // Vector layer (pl. OpenLayers saját)
    KML,      // Keyhole Markup Language
    GPX,      // GPS Exchange Format
    TOPOJSON, // TopoJSON
    MVT       // Mapbox Vector Tiles
}