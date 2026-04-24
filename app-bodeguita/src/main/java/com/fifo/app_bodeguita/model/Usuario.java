package com.fifo.app_bodeguita.model;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Usuario {
    @DocumentId
    private String id;
    private String nombre;   // El nombre que sale en tu captura
    private String username;
    private String password;
    private String rol;      // Administrador / Empleado
    private boolean activo;  // Campo booleano para el bloqueo
}