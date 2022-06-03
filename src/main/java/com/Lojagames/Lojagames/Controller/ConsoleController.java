package com.Lojagames.Lojagames.Controller;

import com.Lojagames.Lojagames.Model.CategoriasModel;
import com.Lojagames.Lojagames.Model.ConsoleModel;
import com.Lojagames.Lojagames.Repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/console")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ConsoleController {

    @Autowired
    ConsoleRepository consoleRepository;

    @GetMapping
    public ResponseEntity <List<ConsoleModel>> getAll(ConsoleModel console){
        return ResponseEntity.ok(consoleRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<ConsoleModel> postConsole(@RequestBody ConsoleModel console){
        return ResponseEntity.status(HttpStatus.CREATED).body(consoleRepository.save(console));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsoleModel> getById(@PathVariable Long id){
        return consoleRepository.findById(id)
                .map(categoria -> ResponseEntity.status(HttpStatus.OK).body(categoria))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delCategoria(@PathVariable Long id) {
        return consoleRepository.findById(id)
                .map(r -> {
                    consoleRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<ConsoleModel> putCategoria (@RequestBody ConsoleModel console){
        if(console.getId() == null)
            return ResponseEntity.badRequest().build();

        return consoleRepository.findById(console.getId())
                .map(r-> ResponseEntity.status(HttpStatus.OK)
                        .body(consoleRepository.save(r)))
                .orElse(ResponseEntity.notFound().build());
    }




}
