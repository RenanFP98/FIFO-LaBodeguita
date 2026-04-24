package com.fifo.app_bodeguita.model;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Escaneo {
    @DocumentId
    private String id;
    private String nombre;
    private String codigo;
    private int cantidad;
    private String lote;
    private String vencimiento;
    private String usuario; // Nombre de quien escaneó
    private Object fecha;   // Timestamp de registro
}