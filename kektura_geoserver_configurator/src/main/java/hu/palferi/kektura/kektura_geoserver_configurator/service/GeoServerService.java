package hu.palferi.kektura.kektura_geoserver_configurator.service;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import it.geosolutions.geoserver.rest.GeoServerRESTManager;
import it.geosolutions.geoserver.rest.decoder.RESTLayerList;
import it.geosolutions.geoserver.rest.encoder.GSLayerEncoder;
import it.geosolutions.geoserver.rest.encoder.datastore.GSPostGISDatastoreEncoder;
import it.geosolutions.geoserver.rest.encoder.feature.GSFeatureTypeEncoder;
import it.geosolutions.geoserver.rest.encoder.metadata.virtualtable.GSVirtualTableEncoder;
import jakarta.annotation.PostConstruct;


@Service
public class GeoServerService {

    private GeoServerRESTManager geoServerManager;

    @Value("${geoserver.url}")
    private String geoserverUrl;

    @Value("${geoserver.username}")
    private String username;

    @Value("${geoserver.password}")
    private String password;

    @Value("${geoserver.workspace.name}")
    private String workspaceName;

    @Value("${volan.db.host}")
    private String volanDbHost;

    @Value("${volan.db.port}")
    private int volanDbPort;

    @Value("${volan.db.database.name}")
    private String volanDbDatabaseName;

    @Value("${volan.db.username}")
    private String volanDbUser;

    @Value("${volan.db.password}")
    private String volanDbPassword;

    @Value("${volan.store.name}")
    private String storeName;

    @PostConstruct
    public void init() throws Exception {
        this.geoServerManager = new GeoServerRESTManager(new URL(geoserverUrl), username, password);
    }


    public List<String> getLayerNames() {
        RESTLayerList layers = geoServerManager.getReader().getLayers();
        return layers.getNames().stream().collect(Collectors.toList());
    }

    public boolean initGeoServerToVolanDb() {
        if (!geoServerManager.getReader().existsWorkspace(workspaceName)) {
            geoServerManager.getPublisher().createWorkspace(workspaceName);
        }
        setDefaultWorkspace(workspaceName);
        createOrUpdatePgStore(workspaceName, storeName, volanDbHost, volanDbPort, volanDbDatabaseName, volanDbUser, volanDbPassword);
        
        return true;
    }

    public boolean setDefaultWorkspace(String workspaceName) {
        String body = """
            <workspace>
                <name>%s</name>
            </workspace>
            """.formatted(workspaceName);

        WebClient client = WebClient.builder()
                .baseUrl(geoserverUrl)
                .defaultHeaders(headers -> {
                    headers.setBasicAuth(username, password);
                    headers.setContentType(MediaType.APPLICATION_XML);
                })
                .build();

        HttpStatusCode status = client.put()
                .uri("/rest/workspaces/default.xml")
                .bodyValue(body)
                .retrieve()
                .toBodilessEntity()
                .map(ResponseEntity::getStatusCode)
                .block();

        return status != null && status.is2xxSuccessful();
    }


    public boolean createOrUpdatePgStore(String workspace, String storeName,
                                         String dbHost, int dbPort, String dbName,
                                         String dbUser, String dbPassword) {

        GSPostGISDatastoreEncoder storeEncoder = new GSPostGISDatastoreEncoder(storeName);
        storeEncoder.setHost(dbHost);
        storeEncoder.setPort(dbPort);
        storeEncoder.setDatabase(dbName);
        storeEncoder.setUser(dbUser);
        storeEncoder.setPassword(dbPassword);
        storeEncoder.setConnectionTimeout(30);
        storeEncoder.setMaxConnections(10);
        storeEncoder.setMinConnections(1);
        storeEncoder.setName(storeName);

        if (geoServerManager.getReader().getDatastore(workspace, storeName) == null) {
            return geoServerManager.getStoreManager().create(workspace, storeEncoder);
        } else {
            return geoServerManager.getStoreManager().update(workspace, storeEncoder);
        }
    }

    public void CreateOrRefreshPublicSqlViewLayer(String layerName, String sqlStatement)
    {
        DropIfExistsAndCreateAndPublishPostgresSqlViewLayer(workspaceName, storeName, layerName, sqlStatement);
    }

    public Boolean dropIfExistsSqlViewLayer(String layerName)
    {
        return DropIfExistsPostgresSqlViewLayer(workspaceName, layerName);
    }


    public Boolean DropIfExistsPostgresSqlViewLayer(
            String workspace,
            String layerName
    ) {
        try {
            // Réteg törlése, ha létezik
            if (geoServerManager.getReader().existsLayer(workspace, layerName)) {
                boolean removed = geoServerManager.getPublisher().removeLayer(workspace, layerName);
                if (!removed) {
                    System.err.println("Nem sikerült törölni a meglévő réteget: " + layerName);
                    return false;
                }
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }




    public boolean DropIfExistsAndCreateAndPublishPostgresSqlViewLayer(
            String workspace,
            String storeName,
            String layerName,
            String sqlStatement
    ) {
        try {
            // Réteg törlése, ha létezik
            DropIfExistsPostgresSqlViewLayer(workspace, layerName);

            // FeatureType encoder beállítása
            GSFeatureTypeEncoder fte = new GSFeatureTypeEncoder();
            System.out.println("Layer name: " + layerName);
            System.out.println("SQL statement: " + sqlStatement);
            fte.setName(layerName);
            fte.setTitle(layerName);
            fte.setSRS("EPSG:4326");
            fte.setNativeName(layerName);
            

            // VirtualTable encoder - csak az elérhető metódusokat használjuk
            GSVirtualTableEncoder vte = new GSVirtualTableEncoder();
            vte.setName(layerName);
            vte.setSql(sqlStatement);
            vte.addKeyColumn("id");
            vte.addVirtualTableGeometry("geom", "Point", "4326");


            // Virtual table beállítása (ez a metódus elérhető szokott lenni)
            fte.setMetadataVirtualTable(vte);

            // Layer encoder – szükséges, mert `publishDBLayer()` négy paramétert vár
            GSLayerEncoder layerEncoder = new GSLayerEncoder();
            layerEncoder.setDefaultStyle("default"); // vagy saját stílus

            // Réteg publikálása
            boolean published = geoServerManager.getPublisher()
                .publishDBLayer(workspace, storeName, fte, layerEncoder);

            if (!published) {
                System.err.println("Nem sikerült publikálni a réteget: " + layerName);
                return false;
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }





}
