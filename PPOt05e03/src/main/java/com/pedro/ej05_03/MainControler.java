package com.pedro.ej05_03;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class MainControler {
    
    // UN SOLO Map que guarda todos los votos
    private final MainService mainService = new MainService();
    
    @GetMapping({"/", "/home", "/index"})
    public String index(Model model) {
        model.addAttribute("votos", mainService.getVotos());
        model.addAttribute("pelisForm", new PelisForm());
        return "index";
    }

    @PostMapping("/pelisForm/submit")
    public String vote(@Valid PelisForm pelisform, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("votos", mainService.getVotos());
            model.addAttribute("pelisForm", pelisform);
            return "index";
        }

        if(mainService.yaHaVotado(pelisform.getEmail())) {
            model.addAttribute("error", "Ya has votado con este email");
            model.addAttribute("votos", mainService.getVotos());
            model.addAttribute("pelisForm", pelisform);
            return "index";
        }
        mainService.registrarVoto(pelisform.getEmail(), pelisform.getOpcion());
        return "redirect:/"; // Volver a la página principal
    }
    
    
    
}
