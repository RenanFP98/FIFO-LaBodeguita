package com.fifo.app_bodeguita.controller;

import com.fifo.app_bodeguita.dto.ProductoStockDTO;
import com.fifo.app_bodeguita.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/todo")
    public List<ProductoStockDTO> obtenerTodo() throws Exception {
        return productoService.listarStockDTO();
    }
}