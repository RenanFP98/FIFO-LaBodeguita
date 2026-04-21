package com.fifo.app_bodeguita.model;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Producto {
    @DocumentId
    private String id;
    private String nombre;
    private String codigoBarras;    // Será el que mande el iPhone
    private int cantidad;
    private String fechaIngreso;    // Fecha actual
    private String fechaVencimiento; 
    private String tipo;            // "LOTE" o "PERECIBLE"
    private String usuarioId;       // <--- Aquí guardaremos el ID de la flecha roja más adelante
}