package br.com.senac.api.controllers;

import br.com.senac.api.useCases.usuarios.UsuariosService;
import br.com.senac.api.useCases.usuarios.domains.UsuariosRequestDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?>
        cadastrarUsuario(@RequestBody UsuariosRequestDom usuario) {

        try {
            return ResponseEntity
                    .ok(usuariosService.criarUsuario(usuario));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Erro não mapeado: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuariosRequestDom usuario){
        try {
            return ResponseEntity.ok(usuariosService
                    .loginUsuario(usuario));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Erro não mapeado: " + e.getMessage());
        }
    }
}
