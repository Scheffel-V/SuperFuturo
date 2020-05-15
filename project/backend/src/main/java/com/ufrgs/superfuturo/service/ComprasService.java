package com.ufrgs.superfuturo.service;

import java.util.List;

import com.ufrgs.superfuturo.domain.Compras;


public interface ComprasService {

    void salvar(Compras compras);

    void editar(Compras compras);

    void excluir(Long id);

    Compras buscarPorId(Long id);

    List<Compras> buscarTodos();
}