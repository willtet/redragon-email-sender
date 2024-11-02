package br.com.redragon.email_sender.controller;

import br.com.redragon.email_sender.dtos.EmailBaseDto;
import br.com.redragon.email_sender.dtos.GarantiaDto;
import br.com.redragon.email_sender.dtos.OutrosDto;
import br.com.redragon.email_sender.dtos.ParceriasDto;
import br.com.redragon.email_sender.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private JavaMailSender mailSender;

    @Autowired
    private EmailService emailService;


    @PostMapping("/garantia")
    public String garantiaMail(@RequestBody GarantiaDto dto) {
        try {


            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo("williantet@gmail.com");
            helper.setSubject("Teste de envio de email");
            helper.setText(
                    "- Nome Completo: "+dto.getNomeCompleto() + "\r" +
                            "- Email: "+dto.getEmail() + "\r" +
                            "- Telefone: "+dto.getTelefone() + "\r" +
                            "- Nome Produto: " + dto.getNomeProduto() + "\r" +
                            "- Série Produto: " + dto.getSerieProduto() + "\r" +
                            "- Descrição: " + dto.getDescricao() + "\r" +
                            "- Data da Compra: " + dto.getDataCompra() + "\r" +
                            "- Fotos: " + dto.getFotos() + "\r" +
                            "- Nota Fiscal: " + dto.getNotaFiscal() + "\r"
                    );


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

    @PostMapping("/outros")
    public String outrosMail(@RequestBody OutrosDto dto) {
        try {

            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo("williantet@gmail.com");
            helper.setSubject("Outras dúvidas - Redragon");
            helper.setText(
                    "- Nome Completo: "+dto.getNomeCompleto() + "\r" +
                    "- Email: "+dto.getEmail() + "\r" +
                    "- Telefone: "+dto.getTelefone() + "\r" +
                    "- Por favor, descreva sua dúvida abaixo: \r" + dto.getDuvida());

            // Envia o email
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (MailException e) {
            return e.getMessage();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
