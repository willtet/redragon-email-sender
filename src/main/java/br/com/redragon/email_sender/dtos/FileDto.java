package br.com.redragon.email_sender.dtos;

import lombok.Data;

@Data
public class FileDto {
    private String name;
    private String type;
    private long size;
    private String base64;
}
