package br.com.redragon.email_sender.dtos;

import lombok.Data;

@Data
public class EmailBaseDto {
    private String nomeCompleto;

    private String email;

    private String telefone;
}
