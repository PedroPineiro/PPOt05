package com.pedro.ej05_03;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainControler {
    
    // UN SOLO Map que guarda todos los votos
    private final Map<Integer, Integer> votos = new HashMap<>();
    
    // Constructor: inicializar votos en 0
    public MainControler() {
        votos.put(0, 0);  // Scarface empieza con 0 votos
        votos.put(1, 0);  // The Thing empieza con 0 votos  
        votos.put(2, 0);  // Leon empieza con 0 votos
    }
    
    @GetMapping({"/", "/home", "/index"})
    public String index(Model model) {
        model.addAttribute("votos", votos);
        return "index";
    }

    @GetMapping("/vote")
    public String vote(@RequestParam("foto") int fotoId, Model model) {
            // Obtengo el valor actual y le sumo 1
            int votosActuales = votos.get(fotoId);
            votos.put(fotoId, votosActuales + 1);
        return "redirect:/"; // Volver a la página principal
    }
    
    
    
}
