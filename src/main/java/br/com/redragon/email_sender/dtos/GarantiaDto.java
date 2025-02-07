package br.com.redragon.email_sender.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class GarantiaDto extends EmailBaseDto implements Serializable {
    private String nomeProduto;
    private String serieProduto;
    private String descricao;
    private Date dataCompra;
    private List<FileDto> fotos;
    private List<FileDto> notaFiscal;
    private String mensagem;
}
