package com.Lojagames.Lojagames.Controller;

import com.Lojagames.Lojagames.Model.CategoriasModel;
import com.Lojagames.Lojagames.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<CategoriasModel>> getAll(){
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<CategoriasModel> postCategoria(@RequestBody CategoriasModel categoria){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriasModel> getById(@PathVariable Long id){
        return categoriaRepository.findById(id)
                .map(categoria -> ResponseEntity.status(HttpStatus.OK).body(categoria))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delCategoria(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(r -> {
                    categoriaRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }



    @PutMapping
    public ResponseEntity<CategoriasModel> putCategoria (@RequestBody CategoriasModel categoria){
        if(categoria.getId() == null)
            return ResponseEntity.badRequest().build();

        return categoriaRepository.findById(categoria.getId())
                .map(r-> ResponseEntity.status(HttpStatus.OK)
                        .body(categoriaRepository.save(r)))
                .orElse(ResponseEntity.notFound().build());
    }

}
