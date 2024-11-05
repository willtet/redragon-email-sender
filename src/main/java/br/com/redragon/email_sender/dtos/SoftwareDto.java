package br.com.redragon.email_sender.dtos;

import lombok.Data;

import java.util.List;

@Data
public class SoftwareDto extends EmailBaseDto{
    private String nomeProduto;
    private String serieProduto;
    private String mensagemOS;
    private String mensagemSpec;
    private String mensagemProblema;
    private String comecoProblema;
    private String mudancaOS;
    private String caso;
    private List<FileDto> fotos;
    private List<FileDto> problema;
}
