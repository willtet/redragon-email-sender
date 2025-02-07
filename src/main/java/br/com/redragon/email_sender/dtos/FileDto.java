package br.com.redragon.email_sender.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileDto  implements Serializable {
    private String name;
    private String type;
    private long size;
    private String base64;
}
