package com.pedro.t5ej04;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FormInfo {
    
    private String nombre;

    private String DNI;

    @Email
    private String email;

    private String direccionEnvio;

    public enum Opcion {
        FOTO_FIRMADA,
        ENTRADA_VIP,
        BUFANDA
    }

    @NotNull(message = "Debe elegir una opción")
    private Opcion opcion;

    private boolean aceptarTerminos;


}
