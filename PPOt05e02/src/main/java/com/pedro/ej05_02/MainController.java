package com.pedro.ej05_02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final MainService mainService = new MainService();
    
    @GetMapping({"/", "/home"})
    public String index(Model model) {
        model.addAttribute("dateForm", new DateForm());
        return "indexForm";
    }

    @PostMapping("/submit")
    public String submit(DateForm dateForm, Model model) {
        boolean hayError = false;

        // Validaciones manuales
        if (dateForm.getDate1() == null) {
            model.addAttribute("errorDate1", "Debe introducir la fecha 1");
            hayError = true;
        }
        if (dateForm.getDate2() == null) {
            model.addAttribute("errorDate2", "Debe introducir la fecha 2");
            hayError = true;
        }
        if (dateForm.getOpcion() == 0) {
            model.addAttribute("errorOpcion", "Debe seleccionar una opción");
            hayError = true;
        }

        // Si hay errores, volver al formulario
        if (hayError) {
            model.addAttribute("dateForm", dateForm);
            return "indexForm";
        }

        // Procesamiento normal
        switch (dateForm.getOpcion()) {
            case 1 -> model.addAttribute("resultado", mainService.contarDiasEntreFechas(dateForm));
            case 2 -> model.addAttribute("resultado", mainService.listarAnosBisiestos(dateForm));
            case 3 -> model.addAttribute("resultado", mainService.contarEneroDomingo(dateForm));
            default -> {
                model.addAttribute("errorOpcion", "Opción no válida");
                return "indexForm";
            }
        }
        return "formResult";
    }  
}
