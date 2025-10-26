package com.pedro.ej5_05;

public class PaisNotFoundException extends Exception {
    public PaisNotFoundException(){
        super("El país no se ha encontrado.");
    }
}