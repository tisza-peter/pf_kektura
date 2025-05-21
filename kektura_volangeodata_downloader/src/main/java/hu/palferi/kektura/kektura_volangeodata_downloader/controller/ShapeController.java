package hu.palferi.kektura.kektura_volangeodata_downloader.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.palferi.kektura.kektura_volangeodata_downloader.service.ShapeRouteService;
import hu.palferi.kektura.kektura_volangeodata_downloader.service.ShapeStopService;

@RestController
@RequestMapping("/api/shapes")
public class ShapeController {

    private final ShapeRouteService shapeRouteService;
    private final ShapeStopService shapeStopService;

    public ShapeController(ShapeRouteService shapeRouteService, ShapeStopService shapeStopService) {
        this.shapeRouteService = shapeRouteService;
        this.shapeStopService = shapeStopService;
    }




    @PostMapping("/update-route-geometries")
    public ResponseEntity<Void> updateRouteGeometries() {
        shapeRouteService.updateShapeGeometries();
        return ResponseEntity.ok().build();
    }


    @PostMapping("/update-stop-geometries")
    public ResponseEntity<Void> updateStopGeometries() {
        shapeStopService.updateStopGeometries();
        return ResponseEntity.ok().build();
    }

}
