package com.pedro.ej5_05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping({""})
    public String goPaises(Model model) {
        return "redirect:/paises";
    }

    @GetMapping({"/paises"})
    public String showPaises(Model model) {
        model.addAttribute("paises", mainService.getPaises());
        if(model.getAttribute("pais") == null)
            model.addAttribute("pais", new Pais());
        if(model.getAttribute("found") == null)
            model.addAttribute("found", false);

        return "index";
    }

    @PostMapping("/submit")
    public RedirectView redirectShowPaises(Pais pais, RedirectAttributes attributes) {
        // 'addFlashAttribute' mantiene y añade los atributos durante el redireccionamiento
        try {
            attributes.addFlashAttribute("pais", mainService.getPais(pais.getNombre()));
            attributes.addFlashAttribute("found", true); // Indica que se encontró el país y lo muestra en la vista // linea 14 index.html
        } catch (PaisNotFoundException e) {
        }

        return new RedirectView("/paises");
    }
}
