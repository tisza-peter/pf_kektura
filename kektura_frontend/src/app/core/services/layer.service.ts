import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LayerDefinitionDTO } from '../models/layer-definition.model';

@Injectable({
  providedIn: 'root',
})
export class LayerService {
  private readonly apiUrl = 'http://localhost:8084/api/openlayers-layers/all-layers';

  constructor(private http: HttpClient) {}

  getLayerDefinitions(): Observable<LayerDefinitionDTO[]> {
    return this.http.get<LayerDefinitionDTO[]>(this.apiUrl);
  }
}

