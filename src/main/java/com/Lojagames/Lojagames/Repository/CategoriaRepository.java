package com.Lojagames.Lojagames.Repository;

import com.Lojagames.Lojagames.Model.CategoriasModel;
import com.Lojagames.Lojagames.Model.ConsoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriasModel,Long> {

}
