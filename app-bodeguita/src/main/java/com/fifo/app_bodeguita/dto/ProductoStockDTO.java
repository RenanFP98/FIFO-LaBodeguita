package com.fifo.app_bodeguita.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ProductoStockDTO {
    private String codigo;
    private String nombre;
    private int cantidadTotal;
}