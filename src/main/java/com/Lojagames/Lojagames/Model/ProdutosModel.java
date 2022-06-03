package com.Lojagames.Lojagames.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "tb_produtos")
public class ProdutosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max=255)
    private String nome;

    @NotBlank
    @Size(min = 3, max=255)
    private String descricao;

    @NotNull
    private double valor;

    @NotNull
    private Long quantidade;

    @ManyToOne
    @JsonIgnoreProperties("produtosCtg")
    private CategoriasModel categoria;

    @ManyToOne
    @JsonIgnoreProperties("produtosCnsl")
    private ConsoleModel console;

    private String foto;

}
