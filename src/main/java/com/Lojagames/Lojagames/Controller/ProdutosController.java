package com.Lojagames.Lojagames.Controller;

import com.Lojagames.Lojagames.Model.ProdutosModel;
import com.Lojagames.Lojagames.Repository.CategoriaRepository;
import com.Lojagames.Lojagames.Repository.ConsoleRepository;
import com.Lojagames.Lojagames.Repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class ProdutosController {

    @Autowired
    ProdutosRepository produtosRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ConsoleRepository consoleRepository;

    @GetMapping
    public ResponseEntity<List<ProdutosModel>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(produtosRepository.findAll());
    }

    @GetMapping("menorvalor/{valor}")
    public ResponseEntity<List<ProdutosModel>> findLessThan(@PathVariable Double valor){
        return ResponseEntity.status(HttpStatus.OK).body(produtosRepository.findAllByValorLessThanEqual(valor));
    }

    @GetMapping("maior/{valor}")
    public ResponseEntity<List<ProdutosModel>> findGreaterThan(@PathVariable Double valor){
        return ResponseEntity.status(HttpStatus.OK).body(produtosRepository.findAllByValorGreaterThanEqual(valor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutosModel> getById(@PathVariable Long id){
        return produtosRepository.findById(id)
                .map(produto-> ResponseEntity.ok(produto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutosModel> postProdutos(@Valid @RequestBody ProdutosModel produtos){
        if(produtos.getCategoria().getId()==null||
            produtos.getConsole().getId() ==null||
            !categoriaRepository.existsById(produtos.getCategoria().getId())||
                !consoleRepository.existsById(produtos.getConsole().getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository.save(produtos));
    }

    @PutMapping
    public ResponseEntity<ProdutosModel> putProdutos(@RequestBody ProdutosModel produto){
        if(produto.getConsole().getId() ==null ||
                produto.getCategoria().getId()== null||
                produto.getId()==null||
                !consoleRepository.existsById(produto.getConsole().getId())||
                !categoriaRepository.existsById(produto.getCategoria().getId()))
         return ResponseEntity.badRequest().build();
        return produtosRepository.findById(produto.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(produtosRepository.save(produto)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutosModel> delProdutos(@PathVariable Long id){
        if(produtosRepository.existsById(id)){
            produtosRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.notFound().build();
    }



}
