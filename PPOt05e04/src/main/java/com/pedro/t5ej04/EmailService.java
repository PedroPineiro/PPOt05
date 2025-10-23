package com.pedro.t5ej04;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class EmailService {
    @Autowired
    private JavaMailSender enviador;

    public boolean enviarEmail(String destinatario, String asunto, String texto, String archivo){
        try{
            MimeMessage message = enviador.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(destinatario);
            helper.setText(texto, true);
            helper.setSubject(asunto);
            File archivoAdjunto = new File(archivo);
            if (!archivoAdjunto.exists()) {
                throw new RuntimeException("El archivo no existe: " + archivo);
            }
            helper.addAttachment(archivoAdjunto.getName(), archivoAdjunto);
            enviador.send(message);
            return true;
        }catch(MessagingException e){return false;}
    }
}
