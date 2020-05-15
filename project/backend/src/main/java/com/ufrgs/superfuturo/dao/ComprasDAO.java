package com.ufrgs.superfuturo.dao;

import java.util.List;

import com.ufrgs.superfuturo.domain.Compras;

public interface ComprasDAO {

    void save(Compras compras );

    void update(Compras compras);

    void delete(Long id);

    Compras findById(Long id);

    List<Compras> findAll();
}
