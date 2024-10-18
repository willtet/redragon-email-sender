package br.com.redragon.email_sender.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final JavaMailSender mailSender;

    public EmailController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RequestMapping()
    public String enviarEmail(){
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("williantet@gmail.com");
            message.setTo("williantet@gmail.com");
            message.setSubject("Teste de envio de email");
            message.setText("Testando o envio de email com o Spring Boot");

            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (MailException e) {
            return e.getMessage();
        }
    }
}
