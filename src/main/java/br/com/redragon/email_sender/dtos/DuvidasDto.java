package br.com.redragon.email_sender.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class DuvidasDto extends EmailBaseDto{
    private String nomeProduto;
    private String serieProduto;
    private String problemaDetalhado;
    private Date dataCompra;
    private String problemaResumo;
}
