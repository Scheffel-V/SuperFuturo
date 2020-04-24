package com.ufrgs.superfuturo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrgs.superfuturo.dao.ComprasDAO;
import com.ufrgs.superfuturo.domain.Compras;

@Service @Transactional(readOnly = false)
public class ComprasServiceImpl implements ComprasService {
	
	@Autowired
	private ComprasDAO dao;

	@Override
	public void salvar(Compras compras) {
		dao.save(compras);		
	}

	@Override
	public void editar(Compras compras) {
		dao.update(compras);		
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);		
	}

	@Override @Transactional(readOnly = true)
	public Compras buscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Override @Transactional(readOnly = true)
	public List<Compras> buscarTodos() {
		
		return dao.findAll();
	}

}
