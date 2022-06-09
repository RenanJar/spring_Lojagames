package com.Lojagames.Lojagames.Controller;

import com.Lojagames.Lojagames.Model.UsuarioLoginModel;
import com.Lojagames.Lojagames.Model.UsuarioModel;
import com.Lojagames.Lojagames.Repository.UsuarioRepository;
import com.Lojagames.Lojagames.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/all")
    public ResponseEntity <List<UsuarioModel>> getAll(){
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> getById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<List<UsuarioModel>> getById(@PathVariable String usuario) {
        return ResponseEntity.ok(usuarioRepository.findAllByUsuarioContainingIgnoreCase(usuario));
    }


    @PostMapping("/logar")
    public ResponseEntity<UsuarioLoginModel> login(@RequestBody Optional<UsuarioLoginModel> usuarioLogin) {
        return usuarioService.autenticarUsuario(usuarioLogin)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioModel> postUsuario(@Valid @RequestBody UsuarioModel usuario) {
        return usuarioService.cadastrarUsuario(usuario)
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }


    @PutMapping("/atualizar")
    public ResponseEntity<UsuarioModel> putUsuario(@Valid @RequestBody UsuarioModel usuario) {
        return usuarioService.atualizarUsuario(usuario)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
