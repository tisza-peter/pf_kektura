import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import Map from 'ol/Map';
import View from 'ol/View';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import { defaults as defaultControls, Attribution } from 'ol/control';


@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit {
  @ViewChild('mapContainer', { static: true }) mapContainer!: ElementRef;

  map!: Map;

  ngOnInit(): void {
    // Web Mercator EPSG=3857 BOX(1793786.8934115968, 5738320.528261868, 2548827.0187128135, 6204776.268819715)
    // GPS WGS-84 EPSG=4326 BOX(16.11386182804912, 45.73710996921497, 22.89650267486514, 48.58524099737088)
    // EOV EPSG=23700 BOX(426402.66, 43771.275, 937390.98, 362941.155)

    const hungaryExtent = [1793786.8934115968, 5738320.528261868, 2548827.0187128135, 6204776.268819715];
    const hungaryProjection = 'EPSG:3857';
    const hungaryCenter = [
      (hungaryExtent[0] + hungaryExtent[2]) / 2,
      (hungaryExtent[1] + hungaryExtent[3]) / 2
    ];
    const hungaryZoom = 8;
    const hungaryResolution = (hungaryExtent[3] - hungaryExtent[1]) / 256 / Math.pow(2, hungaryZoom);
    const hungaryMaxZoom = 18;
    const hungaryMinZoom = 2;
    const hungaryTileSize = [256, 256];

    
    this.map = new Map({
      target: this.mapContainer.nativeElement,
      layers: [
        new TileLayer({
          source: new OSM()
        })
      ],
      view: new View({
        center: hungaryCenter,
        zoom: hungaryZoom,
        minZoom: hungaryMinZoom,
        maxZoom: hungaryMaxZoom,
        projection: hungaryProjection,
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

  }
}