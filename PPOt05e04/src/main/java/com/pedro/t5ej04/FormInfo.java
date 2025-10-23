package com.pedro.t5ej04;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FormInfo {
    
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El DNI no puede estar vacío")
    private String DNI;

    @Email(message = "El email no es válido")
    @NotBlank(message = "El email no puede estar vacío")
    private String email;

    @NotBlank(message = "La dirección de envío no puede estar vacía")
    private String direccionEnvio;

    public enum Opcion {
        FOTO_FIRMADA,
        ENTRADA_VIP,
        BUFANDA
    }

    @NotNull(message = "Debe elegir una opción")
    private Opcion opcion;

    @AssertTrue(message = "Debe aceptar los términos y condiciones")
    private boolean aceptarTerminos;

    private String nombreFicheroString;



}
