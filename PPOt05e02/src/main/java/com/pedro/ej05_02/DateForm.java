package com.pedro.ej05_02;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DateForm {
    
    @NotNull(message = "La fecha 1 es obligatoria")
    private LocalDate date1;

    @NotNull(message = "La fecha 2 es obligatoria")
    private LocalDate date2;

    @Positive(message = "Debe seleccionar una opción")
    private int opcion;
    
    // Getters y Setters
    public LocalDate getDate1() {
        return date1;
    }
    public void setDate1(LocalDate date1) {
        this.date1 = date1;
    }
    public LocalDate getDate2() {
        return date2;
    }
    public void setDate2(LocalDate date2) {
        this.date2 = date2;
    }
    public int getOpcion() {
        return opcion;
    }
    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }
}
