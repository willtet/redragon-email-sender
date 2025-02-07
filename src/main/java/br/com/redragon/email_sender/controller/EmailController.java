package br.com.redragon.email_sender.controller;

import br.com.redragon.email_sender.configuration.RabbitMQConfig;
import br.com.redragon.email_sender.dtos.*;
import br.com.redragon.email_sender.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/garantia")
    public ResponseEntity<Object> garantiaMail(@RequestBody GarantiaDto dto) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.QUEUE_GARANTIA, dto);

        return ResponseEntity.status(HttpStatus.OK).body("Email enviado com sucesso!");
    }

    @PostMapping("/software")
    public ResponseEntity<Object> softwareMail(@RequestBody SoftwareDto dto) {;
        return ResponseEntity.status(HttpStatus.OK).body(emailService.enviarSoftware(dto));
    }

    @PostMapping("/outros")
    public ResponseEntity<Object> outrosMail(@RequestBody OutrosDto dto) {;
        return ResponseEntity.status(HttpStatus.OK).body(emailService.enviarOutros(dto));
    }

    @PostMapping("/revenda")
    public ResponseEntity<Object> revendaMail(@RequestBody RevendaDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(emailService.enviarRevenda(dto));
    }

    @PostMapping("/parceria")
    public ResponseEntity<Object> parceriaMail(@RequestBody ParceriasDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(emailService.enviarParcerias(dto));
    }

    @PostMapping("/duvida")
    public ResponseEntity<Object> duvidaMail(@RequestBody DuvidasDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(emailService.enviarDuvidas(dto));
    }
}
