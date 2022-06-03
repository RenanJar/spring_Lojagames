package com.Lojagames.Lojagames.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_console")
public class ConsoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3,max=100)
    private String tipo;

    @NotBlank
    @Size(min = 3,max=100)
    private String descricao;

    @OneToMany(mappedBy = "console",cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("console")
    private List<ProdutosModel> produtosCnsl;

}
