package com.pedro.ej05_03;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainControler {

    @Autowired
    private MainService peliculasService;

    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("votosFoto", peliculasService.getVotosFoto());
        model.addAttribute("formInfo", new PelisForm());
        model.addAttribute("txtErr", peliculasService.getTxtErr());
        return "index";
    }

    @PostMapping("/")
    public String vote(PelisForm formInfo, Model model) {
        boolean emailUsado = peliculasService.isEmailUsed(formInfo.getEmail());
        if (emailUsado) {
            return "redirect:/";
        }

        boolean enviado = peliculasService.sendEmail(formInfo);
        if (enviado) {
            peliculasService.setFormPending(formInfo);
            peliculasService.setTxtErr(Optional.empty());
            return "verificationView";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/verification")
    public String verifyCode(@RequestParam("code") String code, Model model) {
        int enteredCode = Integer.parseInt(code);

        if (peliculasService.verifyCode(enteredCode)) {
            peliculasService.votar(peliculasService.getFormPending());
            return "redirect:/";
        } else {
            model.addAttribute("txtErr", peliculasService.getTxtErr().orElse(""));
            return "verificationView";
        }
    }
}