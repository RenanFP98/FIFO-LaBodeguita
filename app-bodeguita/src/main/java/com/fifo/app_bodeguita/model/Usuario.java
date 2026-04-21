package com.fifo.app_bodeguita.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Usuario {
    
    private String id;
    private String username;
    private String password;
}
