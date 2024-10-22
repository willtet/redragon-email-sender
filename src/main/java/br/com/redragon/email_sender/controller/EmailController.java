package br.com.redragon.email_sender.controller;

import br.com.redragon.email_sender.dtos.EmailBaseDto;
import br.com.redragon.email_sender.dtos.OutrosDto;
import br.com.redragon.email_sender.dtos.ParceriasDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmailController {

    @Autowired
    private final JavaMailSender mailSender;

    public EmailController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @PostMapping
    public String enviarEmail(@RequestBody ParceriasDto dto) {
        try {


            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo("williantet@gmail.com");
            helper.setSubject("Teste de envio de email");
            helper.setText("Testando o envio de email com o Spring Boot");

            // Decodifica a imagem Base64 para bytes
            byte[] imagemBytes = Base64.getDecoder().decode(dto.getApresentacao().get(0).getBase64());

            // Adiciona a imagem como anexo ao email
            helper.addAttachment("imagem.png", new ByteArrayResource(imagemBytes));

            // Envia o email
            mailSender.send(message);

//            SimpleMailMessage message = new SimpleMailMessage();
//
//            message.setFrom("williantet@gmail.com");
//            message.setTo("williantet@gmail.com");
//            message.setSubject("Teste de envio de email");
//            message.setText("Testando o envio de email com o Spring Boot");
//
//            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (MailException e) {
            return e.getMessage();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
