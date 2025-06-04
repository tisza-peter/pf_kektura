import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LayerDefinitionDTO } from '../models/layer-definition.model';

@Injectable({
  providedIn: 'root'
})
export class OpenlayersLayerService {
  private readonly apiUrl = 'http://localhost:8084/api/openlayers-layers';

  constructor(private http: HttpClient) {}

  /** Backendről lekéri a rétegdefiníciókat */
  getLayerDefinitions(): Observable<LayerDefinitionDTO[]> {
    return this.http.get<LayerDefinitionDTO[]>(this.apiUrl);
  }
}
