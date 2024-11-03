package br.com.redragon.email_sender.service;

import br.com.redragon.email_sender.dtos.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Base64;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public String enviarGarantia(GarantiaDto dto){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            StringBuilder fotosNomes = new StringBuilder();
            for (FileDto foto : dto.getFotos()) {
                fotosNomes.append(foto.getName()).append(", ");
            }
            StringBuilder notaFiscalNomes = new StringBuilder();
            for (FileDto nota : dto.getNotaFiscal()) {
                notaFiscalNomes.append(nota.getName()).append(", ");
            }


            MimeMessage message = mailSender.createMimeMessage();
            InternetAddress destinatario = new InternetAddress("willian0404@hotmail.co.jp");

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress("williantet@gmail.com", dto.getNomeCompleto()));
            helper.setTo(destinatario);
            helper.setCc(dto.getEmail());
            helper.setSubject("Garantia - Redragon");
            helper.setText(
                    "- Nome Completo: " + dto.getNomeCompleto() + "\r" +
                            "- Email: " + dto.getEmail() + "\r" +
                            "- Telefone: " + dto.getTelefone() + "\r" +
                            "- Nome Produto: " + dto.getNomeProduto() + "\r" +
                            "- Série Produto: " + dto.getSerieProduto() + "\r" +
                            "- Descrição: " + dto.getDescricao() + "\r" +
                            "- Data da Compra: " + formatter.format(dto.getDataCompra()) + "\r" +
                            "- Fotos: " + fotosNomes.toString() + "\r" +
                            "- Nota Fiscal: " + notaFiscalNomes.toString() + "\r"
            );

            // Adiciona as fotos como anexo ao email
            for (FileDto foto : dto.getFotos()) {
                byte[] fotoBytes = Base64.getDecoder().decode(foto.getBase64());
                helper.addAttachment(foto.getName(), new ByteArrayResource(fotoBytes));
            }

            // Adiciona as notas fiscais como anexo ao email
            for (FileDto nota : dto.getNotaFiscal()) {
                byte[] notaBytes = Base64.getDecoder().decode(nota.getBase64());
                helper.addAttachment(nota.getName(), new ByteArrayResource(notaBytes));
            }

            mailSender.send(message);

            return "Email enviado com sucesso!";
        } catch (MailException | MessagingException | UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }

    public String enviarParcerias(RevendaDto dto) {
        try {

            MimeMessage message = mailSender.createMimeMessage();
            InternetAddress destinatario = new InternetAddress("willian0404@hotmail.co.jp");

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress("williantet@gmail.com", "Teste will"));
            helper.setTo(destinatario);
            helper.setCc(dto.getEmail());
            helper.setSubject("Revenda - Redragon");
            helper.setText(
                    "- Nome Completo: " + dto.getNomeCompleto() + "\r" +
                            "- Email: " + dto.getEmail() + "\r" +
                            "- Telefone: " + dto.getTelefone() + "\r\n\n" +
                            "- Nome da Loja: " + dto.getNomeLoja() + "\r" +
                            "- CNPJ: " + dto.getCnpj() + "\r" +
                            "- Site: " + dto.getSite() + "\r" +
                            "- Whatsapp: " + dto.getWhatsapp() + "\r" +
                            "- Cidade: " + dto.getCidade() + "\r" +
                            "- Inscrição Estadual: " + dto.getInscricaoEstadual() + "\r" +
                            "- Mensagem para a marca: " + dto.getMensagemMarca() + "\r" +
                            "- Observações: " + dto.getObservacao());

            // Envia o email
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (MailException | MessagingException | UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }

    public String enviarOutros(OutrosDto dto) {
        try {

            MimeMessage message = mailSender.createMimeMessage();
            InternetAddress destinatario = new InternetAddress("willian0404@hotmail.co.jp", "Teste will");

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress("", "Teste will"));
            helper.setTo(destinatario);
            helper.setCc(dto.getEmail());
            helper.setSubject("Outras dúvidas - Redragon");
            helper.setText(
                    "- Nome Completo: "+dto.getNomeCompleto() + "\r" +
                            "- Email: "+dto.getEmail() + "\r" +
                            "- Telefone: "+dto.getTelefone() + "\r" +
                            "- Por favor, descreva sua dúvida abaixo: \r" + dto.getDuvida());

            // Envia o email
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (MailException | MessagingException | UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }
}
