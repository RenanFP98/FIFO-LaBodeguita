package com.fifo.app_bodeguita.controller;

import com.fifo.app_bodeguita.model.Producto;
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

    @PostMapping("/registrar")
    public String registrar(@RequestBody Producto producto) throws Exception {
        return "Producto registrado. ID: " + productoService.guardarProducto(producto);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Producto> obtenerMisProductos(@PathVariable String usuarioId) throws Exception {
        return productoService.listarPorUsuario(usuarioId);
    }

}