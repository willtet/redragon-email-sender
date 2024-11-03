package br.com.redragon.email_sender.dtos;

import lombok.Data;

@Data
public class RevendaDto extends EmailBaseDto {
    private String nomeLoja;
    private String cnpj;
    private String site;
    private String whatsapp;
    private String cidade;
    private String inscricaoEstadual;
    private String mensagemMarca;
    private String observacao;
}
