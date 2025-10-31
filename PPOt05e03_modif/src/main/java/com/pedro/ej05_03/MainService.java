package com.pedro.ej05_03;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class MainService {

    @Autowired
    private JavaMailSender sender;

    private List<Integer> votosFoto = new ArrayList<>(List.of(0, 0, 0));
    private List<String> emails = new ArrayList<>();
    private Optional<String> txtErr = Optional.empty();
    // Variable para almacenar el FormInfo pendiente de verificación
    private PelisForm formPending;

    public void votar(PelisForm formInfo) {
        String email = formInfo.getEmail();
        int indiceVoto = formInfo.getVoto();
        if (emails.contains(email)) {
            txtErr = Optional.of(email + " ya votó");
            return;
        }
        if (email.equals("") || !email.contains("@gmail.com")) {
            txtErr = Optional.of("Escribe un email valido");
            return;
        } else {
            emails.add(email);
            votosFoto.set(indiceVoto, votosFoto.get(indiceVoto) + 1);
        }
        txtErr = Optional.empty();
        formPending = null;
    }

    public boolean sendEmail(PelisForm formInfo) {
        formInfo.setCode((int) (Math.random() * 9000) + 1000);
        System.out.println("Código generado: " + formInfo.getCode());

        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(formInfo.getEmail());
            helper.setText("Tu código de verificación es: " + formInfo.getCode());
            helper.setSubject("Verificación de Voto");

            sender.send(message);
            return true;
        } catch (MessagingException e) {
            System.out.println("Error al enviar el email: " + e.getMessage());
            return false;
        }
    }

    public boolean verifyCode(int code) {
        if (formPending != null && formPending.getCode() == code) {
            txtErr = Optional.empty();
            return true;
        } else {
            txtErr = Optional.of("Código incorrecto");
            return false;
        }
    }

    public boolean isEmailUsed(String email) {
        if (emails.contains(email)) {
            txtErr = Optional.of(email + " ya votó");
            return true;
        }
        return false;
    }

}
