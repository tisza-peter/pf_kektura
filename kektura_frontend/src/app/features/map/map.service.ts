import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LayerDefinitionDTO } from '../../core/models/layer-definition.model';
import { LayerService } from '../../core/services/layer.service';

@Injectable({
  providedIn: 'root'
})
export class OpenlayersService {
  constructor(private api: LayerService) {}

  /** A MapComponent-nek továbbítja a layerdef-eket */
  fetchLayers(): Observable<LayerDefinitionDTO[]> {
    return this.api.getLayerDefinitions();
    
  }
}
