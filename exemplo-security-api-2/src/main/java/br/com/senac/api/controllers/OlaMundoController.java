package br.com.senac.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/olaMundo")
public class OlaMundoController {

    @GetMapping("/teste")
    public ResponseEntity<String> teste(){
        return ResponseEntity.ok("Ola Mundo");
    }
}
