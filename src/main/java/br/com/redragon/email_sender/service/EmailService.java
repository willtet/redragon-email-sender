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
import java.util.Objects;

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
                            "- Nota Fiscal: " + notaFiscalNomes.toString() + "\r" +
                            (Objects.nonNull(dto.getMensagem()) ? "- Por que sua Nota Fiscal não está disponível? : " + dto.getMensagem() : "")
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

    public String enviarParcerias(ParceriasDto dto) {
        try {

            StringBuilder apresentacaoNomes = new StringBuilder();
            for (FileDto foto : dto.getApresentacao()) {
                apresentacaoNomes.append(foto.getName()).append(", ");
            }

            MimeMessage message = mailSender.createMimeMessage();
            InternetAddress destinatario = new InternetAddress("willian0404@hotmail.co.jp");

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress("williantet@gmail.com", dto.getNomeCompleto()));
            helper.setTo(destinatario);
            helper.setCc(dto.getEmail());
            helper.setSubject("Parcerias - Redragon");
            helper.setText(
                    "- Nome Completo: " + dto.getNomeCompleto() + "\r" +
                            "- Email: " + dto.getEmail() + "\r" +
                            "- Telefone: " + dto.getTelefone() + "\r" +
                            "- Mensagem: " + dto.getMensagem() + "\r" +
                            "- Apresentação: " + (Objects.isNull(dto.getApresentacaoArquivo()) ? apresentacaoNomes.toString() : dto.getApresentacaoArquivo()) + "\r"


            );
            if(Objects.isNull(dto.getApresentacaoArquivo())){
                for (FileDto foto : dto.getApresentacao()) {
                    byte[] fotoBytes = Base64.getDecoder().decode(foto.getBase64());
                    helper.addAttachment(foto.getName(), new ByteArrayResource(fotoBytes));
                }
            }


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
            InternetAddress destinatario = new InternetAddress("willian0404@hotmail.co.jp", dto.getNomeCompleto());

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress("williantet@gmail.com", dto.getNomeCompleto()));
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

    public String enviarRevenda(RevendaDto dto) {
        try {

            MimeMessage message = mailSender.createMimeMessage();
            InternetAddress destinatario = new InternetAddress("willian0404@hotmail.co.jp", dto.getNomeCompleto());

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress("williantet@gmail.com", dto.getNomeCompleto()));
            helper.setTo(destinatario);
            helper.setCc(dto.getEmail());
            helper.setSubject("Revenda - Redragon");
            helper.setText(
                    "- Nome Completo: "+dto.getNomeCompleto() + "\r" +
                            "- Email: "+dto.getEmail() + "\r" +
                            "- Telefone: "+dto.getTelefone() + "\r" +
                            "- Nome da loja: " + dto.getNomeLoja() + "\r" +
                            "- CNPJ: " + dto.getCnpj() + "\r" +
                            "- Site: " + dto.getSite() + "\r" +
                            "- Whatsapp: " + dto.getWhatsapp() + "\r" +
                            "- Cidade: " + dto.getCidade() + "\r" +
                            "- Inscrição Estadual: " + dto.getInscricaoEstadual() + "\r" +
                            "- Mensagem para a marca: \n" + dto.getMensagemMarca() + "\r" +
                            "- Observações: \n" + dto.getObservacao()

            );

            // Envia o email
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (MailException | MessagingException | UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }

    public String enviarDuvidas(DuvidasDto dto) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


            MimeMessage message = mailSender.createMimeMessage();
            InternetAddress destinatario = new InternetAddress("willian0404@hotmail.co.jp");

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress("williantet@gmail.com", dto.getNomeCompleto()));
            helper.setTo(destinatario);
            helper.setCc(dto.getEmail());
            helper.setSubject("Dúvidas - Redragon");
            helper.setText(
                    "- Nome Completo: " + dto.getNomeCompleto() + "\r" +
                            "- Email: " + dto.getEmail() + "\r" +
                            "- Telefone: " + dto.getTelefone() + "\r" +
                            "- Nome Produto: " + dto.getNomeProduto() + "\r" +
                            "- Série Produto: " + dto.getSerieProduto() + "\r" +
                            "- Descrição: " + dto.getProblemaDetalhado() + "\r" +
                            "- Data da Compra: " + formatter.format(dto.getDataCompra()) + "\r" +
                            "- Resumo do problema: " + dto.getProblemaResumo() + "\r"
            );

            mailSender.send(message);

            return "Email enviado com sucesso!";
        } catch (MailException | MessagingException | UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }

    public String enviarSoftware(SoftwareDto dto) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            StringBuilder fotosNomes = new StringBuilder();
            for (FileDto foto : dto.getFotos()) {
                fotosNomes.append(foto.getName()).append(", ");
            }
            StringBuilder notaProblemas = new StringBuilder();
            for (FileDto nota : dto.getProblema()) {
                notaProblemas.append(nota.getName()).append(", ");
            }


            MimeMessage message = mailSender.createMimeMessage();
            InternetAddress destinatario = new InternetAddress("willian0404@hotmail.co.jp");

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress("williantet@gmail.com", dto.getNomeCompleto()));
            helper.setTo(destinatario);
            helper.setCc(dto.getEmail());
            helper.setSubject("Software - Redragon");
            helper.setText(
                    "- Nome Completo: " + dto.getNomeCompleto() + "\r" +
                            "- Email: " + dto.getEmail() + "\r" +
                            "- Telefone: " + dto.getTelefone() + "\r" +
                            "- Nome Produto: " + dto.getNomeProduto() + "\r" +
                            "- Série Produto: " + dto.getSerieProduto() + "\r" +
                            "- Mensagem do Sistema Operacional: " + dto.getMensagemOS() + "\r" +
                            "- Mensagem das Especificações: " + dto.getMensagemSpec() + "\r" +
                            (Objects.nonNull(dto.getMensagemProblema()) ?  ("- Mensagem do Problema: " + dto.getMensagemProblema() + "\r") : "") +
                            (Objects.nonNull(dto.getComecoProblema()) ?  ("- Início do Problema: " + dto.getComecoProblema() + "\r") : "") +
                            (Objects.nonNull(dto.getMudancaOS()) ?  ("- Mudança no Sistema Operacional: " + dto.getMudancaOS() + "\r") : "") +
                            "- Caso: " + dto.getCaso() + "\r" +
                            "- Fotos: " + fotosNomes.toString() + "\r" +
                            ( notaProblemas.isEmpty() ? "" : ("- Problemas: " + notaProblemas.toString()))
            );

            // Adiciona as fotos como anexo ao email
            for (FileDto foto : dto.getFotos()) {
                byte[] fotoBytes = Base64.getDecoder().decode(foto.getBase64());
                helper.addAttachment(foto.getName(), new ByteArrayResource(fotoBytes));
            }

            // Adiciona as notas fiscais como anexo ao email
            for (FileDto nota : dto.getProblema()) {
                byte[] notaBytes = Base64.getDecoder().decode(nota.getBase64());
                helper.addAttachment(nota.getName(), new ByteArrayResource(notaBytes));
            }

            mailSender.send(message);

            return "Email enviado com sucesso!";
        } catch (MailException | MessagingException | UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }
}
