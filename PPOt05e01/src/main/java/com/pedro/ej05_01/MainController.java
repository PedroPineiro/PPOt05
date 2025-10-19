package com.pedro.ej05_01;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class MainController {
    
    @GetMapping({"/", "index", "home"})
    public String indexForm(Model model) {
        model.addAttribute("formInfo", new FormInfo());
        return "indexForm";
    }

    @PostMapping("/formSubmit")
    public String formSubmit(FormInfo formInfo, Model model) {
        int suma = formInfo.getNumero1() + formInfo.getNumero2();
        model.addAttribute("suma", suma);
        model.addAttribute("formInfo", formInfo);
        return "formSubmitView";
    }
    
    
}
