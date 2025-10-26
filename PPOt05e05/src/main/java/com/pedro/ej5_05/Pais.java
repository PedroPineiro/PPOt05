package com.pedro.ej5_05;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Pais {
    
    private String nombre;
    private String capital;
    private int poblacion;
}
