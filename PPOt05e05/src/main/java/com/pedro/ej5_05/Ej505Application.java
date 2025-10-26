package com.pedro.ej5_05;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ej505Application {

	public static void main(String[] args) {
		SpringApplication.run(Ej505Application.class, args);
	}

	@Bean
    CommandLineRunner loadPaises(MainService mainService) {
        return args -> {
            List<Pais> paises = new ArrayList<>();
            List<String> nombrePaises = new ArrayList<>();

            // 'cargarPaisesDesdeFichero()' me devuelve una lista de líneas del fichero CSV (List<String>)
            for(String line : MainService.cargarPaisesDesdeFichero()){
                // Separo cada línea por el carácter ';' y obtengo un array de campos (pais, capital, población)
                String[] fields = line.split(";");
                nombrePaises.add(fields[0]);
                // 3º valor es un int en la clase 'Pais', así que lo convierto con 'Integer.valueOf()'
                paises.add(new Pais(fields[0], fields[1], Integer.parseInt(fields[2])));
            }

            mainService.setPaises(paises);
            mainService.setNombrePaises(nombrePaises);
        };
    }

}
