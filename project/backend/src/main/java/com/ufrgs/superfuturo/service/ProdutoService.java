package com.ufrgs.superfuturo.service;

import java.util.List;

import com.ufrgs.superfuturo.domain.Produto;


public interface ProdutoService {

    void salvar(Produto produto);

    void editar(Produto produto);

    void excluir(Long id);

    Produto buscarPorId(Long id);

    List<Produto> buscarTodos();
}