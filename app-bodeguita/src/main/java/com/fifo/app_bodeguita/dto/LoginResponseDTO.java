package com.fifo.app_bodeguita.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String nombre;
    private String username;
    private String rol;
    private String mensaje;
}