package com.pedro.ej05_03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class MainService {

    private final ArrayList<String> emailsVotantes = new ArrayList<>();
    private final Map<Integer, Integer> votos = new HashMap<>();

    public boolean registrarVoto(String email, int opcion) {
        // Incrementar el contador de esa película (si no existe, empieza en 0)
        votos.put(opcion, votos.getOrDefault(opcion, 0) + 1);
        emailsVotantes.add(email);
        return true;
    }

    public boolean yaHaVotado(String email) {
        return emailsVotantes.contains(email);
    }
}
