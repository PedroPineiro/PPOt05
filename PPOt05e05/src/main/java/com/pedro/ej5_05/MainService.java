package com.pedro.ej5_05;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class MainService {

    private List<Pais> paises;
    private List<String> nombrePaises;

    static public List<String> cargarPaisesDesdeFichero() throws FileNotFoundException, IOException{
        return Files.readAllLines(ResourceUtils.getFile("classpath:paises.csv").toPath());
    }

    public void setPaises(List<Pais> paises){
        this.paises = paises;
    }

    public void setNombrePaises(List<String> nombrePaises){
        this.nombrePaises = nombrePaises;
    }

    public List<String> getPaises(){
        return nombrePaises;
    }

    public Pais getPais(String nombre) throws PaisNotFoundException {
        for(Pais p : paises)
            if(p.getNombre().equals(nombre))
                return p;

        throw new PaisNotFoundException();
    }
}
