package com.pedro.t5ej04;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class MainController {

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/")
    public String index(@RequestParam Optional<String> usuario, Model model) {
        // Fecha actual
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        model.addAttribute("fechaActual", fechaActual.format(formatter));
        
        // Mensaje de bienvenida
        if (usuario.isPresent()) {
            model.addAttribute("mensajeBienvenida", usuario.get());
        } else {
            model.addAttribute("mensajeBienvenida", null);
        }
        return "index";
    }

    @GetMapping("/palmares")
    public String palmares(Model model) {
        ArrayList<String> titulos = new ArrayList<>();
        titulos.add("Copa Libertadores: 1981,2019,2022");
        titulos.add("Campeonato Brasileño: 1980, 1982, 1983, 1992, 2009, 2019, 2020");
        titulos.add("Copa do Brasil: 1990, 2006, 2013, 2022");
        titulos.add("Supercopa do Brasil: 2020, 2021");
        titulos.add("Campeonato Carioca: múltiples ediciones");
        model.addAttribute("titulos", titulos);
        return "palmares";
    }

    @GetMapping("/form")
    public String formulario(Model model) {
        model.addAttribute("formInfo", new FormInfo());
        return "formView";
    }

    @PostMapping("/form/submit")
    public String postMethodName(FormInfo formInfo, @RequestParam MultipartFile file, Model model) {
        fileStorageService.store(file);
        model.addAttribute("formInfo", formInfo);
        return "formViewResult";
    }
    
    
}
