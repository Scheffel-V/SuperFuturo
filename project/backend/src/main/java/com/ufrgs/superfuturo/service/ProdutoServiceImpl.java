package com.ufrgs.superfuturo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrgs.superfuturo.dao.ProdutoDAO;
import com.ufrgs.superfuturo.domain.Produto;

@Service @Transactional(readOnly = false)
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	private ProdutoDAO dao;

	@Override
	public void salvar(Produto produto) {
		dao.save(produto);		
	}

	@Override
	public void editar(Produto produto) {
		dao.update(produto);		
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);		
	}

	@Override @Transactional(readOnly = true)
	public Produto buscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Override @Transactional(readOnly = true)
	public List<Produto> buscarTodos() {
		
		return dao.findAll();
	}

}
