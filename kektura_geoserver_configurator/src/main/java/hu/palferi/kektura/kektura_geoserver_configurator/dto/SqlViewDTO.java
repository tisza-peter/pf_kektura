package hu.palferi.kektura.kektura_geoserver_configurator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "SQL nézet létrehozásához vagy frissítéséhez szükséges adatok.")
public class SqlViewDTO {

    @Schema(
        description = "Már létező store neve.",
        required = true,
        example = "my_store"
    )
    private String storeName;

    @Schema(
        description = "A létrehozandó vagy frissítendő réteg neve.",
        required = true,
        example = "my_view_layer"
    )
    private String layerName;

    @Schema(
        description = "Az SQL lekérdezés, amely a nézet definícióját adja meg. " +
                      "Kötelező, hogy tartalmazzon `id` és `geom` mezőket.",
        required = true,
        example = "SELECT id, geom FROM my_table"
    )
    private String sqlStatement;


}
