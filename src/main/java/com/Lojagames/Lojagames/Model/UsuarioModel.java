package com.Lojagames.Lojagames.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_usuarios")
public class UsuarioModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull(message = "O atributo Nome é Obrigatório!")
        private String nome;

        @NotNull(message = "O atributo Usuário é Obrigatório!")
        @Email(message = "O atributo Usuário deve ser um email válido!")
        private String usuario;

        @NotBlank(message = "O atributo Senha é Obrigatório!")
        @Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
        private String senha;

        private String foto;
}
