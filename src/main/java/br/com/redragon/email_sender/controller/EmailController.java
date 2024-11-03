package br.com.redragon.email_sender.controller;

import br.com.redragon.email_sender.dtos.*;
import br.com.redragon.email_sender.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
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

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Objects;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/garantia")
    public String garantiaMail(@RequestBody GarantiaDto dto) {
        return emailService.enviarGarantia(dto);
    }

    @PostMapping("/software")
    public String softwareMail(@RequestBody GarantiaDto dto) {
        return emailService.enviarGarantia(dto);
    }

    @PostMapping("/outros")
    public String outrosMail(@RequestBody OutrosDto dto) {
        return emailService.enviarOutros(dto);
    }

    @PostMapping("/revenda")
    public String revendaMail(@RequestBody RevendaDto dto) {
        return emailService.enviarParcerias(dto);
    }

    @PostMapping("/parceria")
    public String parceriaMail(@RequestBody RevendaDto dto) {
        return emailService.enviarParcerias(dto);
    }
}
