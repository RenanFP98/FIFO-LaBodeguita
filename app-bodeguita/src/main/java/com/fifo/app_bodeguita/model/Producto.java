package com.fifo.app_bodeguita.model;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Producto {
    @DocumentId
    private String codigo;      // Usamos el código de barras como ID del documento
    private String nombre;
    private int cantidad;       // Suma total de todos los lotes
    private Object ultima_actualizacion;
}