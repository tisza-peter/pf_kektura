export interface LayerDefinitionDTO {
  workspaceName: string;
  serviceType: 'WMS' | 'WFS' | 'WMTS' | 'TMS' | 'XYZ' | 'GEOJSON' | 'IMAGE' | 'VECTOR' | 'KML' | 'GPX' | 'TOPOJSON' | 'MVT';  // Bővíthető
  layerName: string;
  opacity: number;
  zindex: number;
  minZoom: number;
  maxZoom: number;
  olStyleJson: string;
  sldStyleName: string;
}
