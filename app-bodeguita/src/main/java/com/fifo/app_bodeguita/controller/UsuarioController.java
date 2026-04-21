package com.fifo.app_bodeguita.controller;

import com.fifo.app_bodeguita.model.Usuario;
import com.fifo.app_bodeguita.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // Importante para que Angular no te de error luego
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crear")
    public String crearUsuario(@RequestBody Usuario usuario) throws Exception {
        return "Usuario creado con ID: " + usuarioService.guardarUsuario(usuario);
    }
}