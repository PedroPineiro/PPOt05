package com.pedro.ej05_03;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PelisForm {
    
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Introduce un email válido")
    private String email;
    
    private Integer opcion;
}
