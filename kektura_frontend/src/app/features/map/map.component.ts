import { Component, OnInit } from '@angular/core';
import Map from 'ol/Map';
import View from 'ol/View';
import { Attribution, defaults as defaultControls } from 'ol/control';
import GeoJSON from 'ol/format/GeoJSON';
import TileLayer from 'ol/layer/Tile';
import VectorLayer from 'ol/layer/Vector';
import { bbox as bboxStrategy } from 'ol/loadingstrategy';
import { OSM, TileWMS } from 'ol/source';
import VectorSource from 'ol/source/Vector';
import { LayerService } from '../../core/services/layer.service';
import { styleFromJson } from './map.style.parser';


@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
})
export class MapComponent implements OnInit {
  map!: Map;

  constructor(private layerService: LayerService) {}

  ngOnInit(): void {

    // Web Mercator EPSG=3857 BOX(1793786.8934115968, 5738320.528261868, 2548827.0187128135, 6204776.268819715)
    // GPS WGS-84 EPSG=4326 BOX(16.11386182804912, 45.73710996921497, 22.89650267486514, 48.58524099737088)
    // EOV EPSG=23700 BOX(426402.66, 43771.275, 937390.98, 362941.155)

    const hungaryExtent = [1793786.8934115968, 5738320.528261868, 2548827.0187128135, 6204776.268819715];
    const Projection_web_mercator = 'EPSG:3857';
    const Projection_WGS_84 = 'EPSG:4326';
    const hungaryCenter = [
      (hungaryExtent[0] + hungaryExtent[2]) / 2,
      (hungaryExtent[1] + hungaryExtent[3]) / 2
    ];
    const hungaryZoom = 8;
    const hungaryMaxZoom = 18;
    const hungaryMinZoom = 2;




    this.map = new Map({
      target: 'map',
      layers: [
        new TileLayer({
          source: new OSM(),
        }),
      ],
      view: new View({
        center: hungaryCenter,
        zoom: hungaryZoom,
        minZoom: hungaryMinZoom,
        maxZoom: hungaryMaxZoom,
        projection: Projection_web_mercator,
        constrainResolution: true // opcionális, szép zoomlépcsők
      }),
      controls: defaultControls({ attribution: false }).extend([
        new Attribution({
          collapsible: false,
          collapsed: false,
          className: 'ol-attribution ol-custom-attribution'
        })
      ])
    });

    this.layerService.getLayerDefinitions().subscribe((layers) => {
      layers.forEach((layerDef) => {
        let layer;

        const baseUrl = `http://localhost:8083/geoserver/${layerDef.workspaceName}/${layerDef.layerName}`;

        if (layerDef.serviceType === 'WMS') {
          layer = new TileLayer({
            source: new TileWMS({
              url: `${baseUrl}/wms`,
              params: {
                LAYERS: `${layerDef.workspaceName}:${layerDef.layerName}`,
                STYLES: `${layerDef.sldStyleName}`, 
                TILED: true,
                SRS: Projection_web_mercator
              },
              serverType: 'geoserver',
            }),
            opacity: layerDef.opacity,
            zIndex: layerDef.zindex,
            minZoom: layerDef.minZoom,
            maxZoom: layerDef.maxZoom,
            visible: true,
          });
        }

        if (layerDef.serviceType === 'WFS') {

          layer = new VectorLayer({
            source: new VectorSource({
              format: new GeoJSON({
    dataProjection: Projection_WGS_84,        // ahogy a GeoServer küldi
    featureProjection: Projection_web_mercator      // ahogy a térképed használja
  }),
              url: (extent) =>
                `${baseUrl}/wfs?service=WFS&` +
                `version=1.1.0&request=GetFeature&typename=${layerDef.workspaceName}:${layerDef.layerName}&` +
                `outputFormat=application/json&srsname=` + Projection_web_mercator + `&` +
                `bbox=${extent.join(',')},` + Projection_web_mercator ,
              strategy: bboxStrategy,
            }),
            style: styleFromJson(layerDef.olStyleJson),
            opacity: layerDef.opacity,
            zIndex: layerDef.zindex,
            minZoom: layerDef.minZoom,
            maxZoom: layerDef.maxZoom,
                
          });
        }

        if (layer) {
          this.map.addLayer(layer);
        }
      });
    });
  }
}



