package br.com.redragon.email_sender.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/version")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VersionController {

    @Value("${version}")
    private String version;

    @GetMapping()
    public ResponseEntity<String> getVersion() {
        return ResponseEntity.status(HttpStatus.OK).body(version);
    }
}
