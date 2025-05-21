package hu.palferi.kektura.kektura_geoserver_configurator.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class SqlViewRequest {

    @Schema(
        description = "Az SQL lekérdezés, amely a nézet definícióját adja meg. " +
                      "Kötelező, hogy tartalmazzon `id` és `geom` mezőket.",
        required = true,
        example = "SELECT id, geom FROM my_table"
    )
    private String sqlStatement;

    @Schema(
        description = "A létrehozandó vagy frissítendő réteg neve.",
        required = true,
        example = "my_view_layer"
    )
    private String layerName;

    // Getterek és setterek
    public String getSqlStatement() {
        return sqlStatement;
    }

    public void setSqlStatement(String sqlStatement) {
        this.sqlStatement = sqlStatement;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }
}
