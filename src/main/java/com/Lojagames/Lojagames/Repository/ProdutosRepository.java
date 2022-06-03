package com.Lojagames.Lojagames.Repository;

import com.Lojagames.Lojagames.Model.ProdutosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutosRepository extends JpaRepository<ProdutosModel,Long>{

    public List<ProdutosModel> findAllByNomeContainingIgnoreCase (@Param("nome") String nome);

    public List<ProdutosModel> findAllByValorLessThanEqual(double valor);

    public List<ProdutosModel> findAllByValorGreaterThanEqual(double valor);

}
