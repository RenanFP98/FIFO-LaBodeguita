package com.fifo.app_bodeguita.controller;

import com.fifo.app_bodeguita.dto.EscaneoDetalleDTO;
import com.fifo.app_bodeguita.service.EscaneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/escaneos")
@CrossOrigin(origins = "*")
public class EscaneoController {

    @Autowired
    private EscaneoService escaneoService;

    @GetMapping("/historial")
    public List<EscaneoDetalleDTO> listar() throws Exception {
        return escaneoService.listarHistorialDTO();
    }
}