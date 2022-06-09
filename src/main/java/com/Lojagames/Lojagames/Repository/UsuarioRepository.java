package com.Lojagames.Lojagames.Repository;

import com.Lojagames.Lojagames.Model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel,Long> {

    public Optional<UsuarioModel> findByUsuario(String usuario);

    public List<UsuarioModel> findAllByUsuarioContainingIgnoreCase (@Param("usuario") String usuario);

}
