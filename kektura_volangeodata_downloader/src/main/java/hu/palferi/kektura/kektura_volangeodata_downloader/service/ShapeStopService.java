package hu.palferi.kektura.kektura_volangeodata_downloader.service;

import org.springframework.stereotype.Service;

import hu.palferi.kektura.kektura_volangeodata_downloader.repository.ShapeStopRepository;

@Service
public class ShapeStopService {

    private final ShapeStopRepository shapeStopRepository;

    public ShapeStopService(ShapeStopRepository shapeStopRepository) {
        this.shapeStopRepository = shapeStopRepository;
    }

    public void updateStopGeometries() {
        shapeStopRepository.updateStopGeometries();
    }


}
