package com.fifo.app_bodeguita.controller;

import com.fifo.app_bodeguita.dto.UsuarioDTO;
import com.fifo.app_bodeguita.dto.LoginResponseDTO;
import com.fifo.app_bodeguita.model.Usuario;
import com.fifo.app_bodeguita.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Listar todos los usuarios usando DTO para no enviar contraseñas a Angular
    @GetMapping("/listar")
    public List<UsuarioDTO> listar() throws Exception {
        return usuarioService.listarUsuariosDTO();
    }

    // Endpoint para activar/desactivar usuarios
    @PutMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable String id, @RequestParam boolean activo) throws Exception {
        return usuarioService.actualizarEstado(id, activo);
    }

    // Editar datos generales y contraseña
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editar(@PathVariable String id, @RequestBody Usuario nuevosDatos) {
        try {
            String respuesta = usuarioService.editarUsuario(id, nuevosDatos);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al actualizar usuario: " + e.getMessage());
        }
    }

    // Endpoint de Login modificado para devolver un LoginResponseDTO
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginReq) throws Exception {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        
        Optional<Usuario> usuarioEncontrado = usuarios.stream()
            .filter(u -> u.getUsername().equalsIgnoreCase(loginReq.getUsername()))
            .findFirst();

        if (usuarioEncontrado.isPresent()) {
            Usuario user = usuarioEncontrado.get();
            
            if (user.getPassword().equals(loginReq.getPassword())) {
                
                if (!user.isActivo()) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("Tu cuenta ha sido desactivada por el administrador.");
                }
                
                // Convertimos el Usuario a LoginResponseDTO para enviar al cliente
                LoginResponseDTO response = new LoginResponseDTO();
                response.setNombre(user.getNombre());
                response.setUsername(user.getUsername());
                response.setRol(user.getRol());
                response.setMensaje("Login exitoso");

                return ResponseEntity.ok(response);
            }
        }
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Usuario o contraseña incorrectos.");
    }
}