package com.ufrgs.superfuturo.dao;

import org.springframework.stereotype.Repository;

import com.ufrgs.superfuturo.domain.Produto;

@Repository
public class ProdutoDAOImpl extends AbstractDAO<Produto, Long> implements ProdutoDAO {

}