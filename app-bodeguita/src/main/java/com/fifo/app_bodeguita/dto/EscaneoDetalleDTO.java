package com.fifo.app_bodeguita.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class EscaneoDetalleDTO {
    private String nombre;
    private String vencimiento;
    private int cantidad;
    private String usuario;
}