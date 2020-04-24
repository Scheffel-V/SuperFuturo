package com.ufrgs.superfuturo.dao;

import java.util.List;

import com.ufrgs.superfuturo.domain.Produto;

public interface ProdutoDAO {

    void save(Produto produto );

    void update(Produto produto);

    void delete(Long id);

    Produto findById(Long id);

    List<Produto> findAll();
}
