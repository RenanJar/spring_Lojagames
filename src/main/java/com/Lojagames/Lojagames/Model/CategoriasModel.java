package com.Lojagames.Lojagames.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_categorias")
public class CategoriasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3,max=100)
    private String tipo;

    @NotBlank
    @Size(min = 3,max=100)
    private String descricao;

    @OneToMany(mappedBy = "categoria",cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("categoria")
    private List<ProdutosModel> produtosCtg;

}
