package br.com.redragon.email_sender.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Data
public class ParceriasDto extends EmailBaseDto {
    private List<FileDto> apresentacao;
    private String mensagem;
    private String apresentacaoArquivo;
}
