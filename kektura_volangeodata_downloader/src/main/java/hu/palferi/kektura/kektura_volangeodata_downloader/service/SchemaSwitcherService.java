package hu.palferi.kektura.kektura_volangeodata_downloader.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import hu.palferi.kektura.kektura_volangeodata_downloader.enums.TableType;
import jakarta.transaction.Transactional;

@Service
public class SchemaSwitcherService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Több tábla egyszerre történő cseréje (pl. egy új verzió publikálása).
     * @return 
     */
    @Transactional
    public List<String> switchAllTables() {
        List<String> gtfsTableNames = Arrays.stream(TableType.values())
                .map(TableType::getFileName)
                .collect(Collectors.toList());

        List<String> geomTableNames = Arrays.asList(
            "shape_route", "shape_stop");
        
        for (String gtfsTableName : gtfsTableNames) {
            switchStagingToPublic("gtfs", gtfsTableName);
        }

        for (String geomTableName : geomTableNames) {
            switchStagingToPublic("geom", geomTableName);
        }

        return gtfsTableNames;
    }

    @Transactional
    public void switchStagingToPublic(String schemaPrefix, String tableName) {

        String stagingSchema = schemaPrefix + "_staging";
        String publicSchema = schemaPrefix + "_public";
        String backupSchema = schemaPrefix + "_backup";

        // Ellenőrizd, hogy a staging sémában létezik-e a tábla
        Boolean exists = jdbcTemplate.queryForObject("""
                SELECT EXISTS (
                    SELECT 1
                    FROM information_schema.tables 
                    WHERE table_schema = ? 
                    AND table_name = ?
                )
                """, Boolean.class, stagingSchema, tableName);

        if (exists == null || !exists) {
            throw new IllegalStateException("Nem található staging tábla: " + stagingSchema + "." + tableName);
        }

        // Tábla törlése a backup sémából, ha létezik
        jdbcTemplate.execute("DROP TABLE IF EXISTS " + backupSchema + "." + tableName );

        // Támla áthelyezése a public sémából a backup sémába
        jdbcTemplate.execute("ALTER TABLE " + publicSchema + "." + tableName + " SET SCHEMA " + backupSchema);

        // Tábla áthelyezése a staging sémából a public sémába
        jdbcTemplate.execute("ALTER TABLE " + stagingSchema + "." + tableName + " SET SCHEMA " + publicSchema);

        // Tábla létrehozása a staging sémában
        jdbcTemplate.execute("CREATE TABLE " + stagingSchema + "." + tableName + " (LIKE " + publicSchema + "." + tableName + " INCLUDING ALL);");

    }

}
