package hu.palferi.kektura.kektura_volangeodata_downloader.service;

import org.springframework.stereotype.Service;

import hu.palferi.kektura.kektura_volangeodata_downloader.repository.ShapeRouteRepository;

@Service
public class ShapeRouteService {

    private final ShapeRouteRepository shapeRouteRepository;

    public ShapeRouteService(ShapeRouteRepository shapeRouteRepository) {
        this.shapeRouteRepository = shapeRouteRepository;
    }

    public void updateShapeGeometries() {
        shapeRouteRepository.updateShapeGeometries();
    }

}
