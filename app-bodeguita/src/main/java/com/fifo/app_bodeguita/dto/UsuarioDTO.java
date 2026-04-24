package com.fifo.app_bodeguita.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String id;
    private String nombre;
    private String username;
    private String rol;
    private boolean activo;
}