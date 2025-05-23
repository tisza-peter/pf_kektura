package hu.palferi.kektura.kektura_control_center.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.palferi.kektura.kektura_control_center.dto.OpenLayersLayerDefinitionDTO;
import hu.palferi.kektura.kektura_control_center.service.OpenlayersService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/openlayers-layers")
@RequiredArgsConstructor
public class OpenlayersController {

    private final OpenlayersService openlayersService;

    @GetMapping("/all-layers")
    public List<OpenLayersLayerDefinitionDTO> getAllLayers() {
        return openlayersService.getAllLayers();
    }
}
