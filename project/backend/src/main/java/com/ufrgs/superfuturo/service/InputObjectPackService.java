package com.ufrgs.superfuturo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrgs.superfuturo.model.InputObjectPack;
import com.ufrgs.superfuturo.repository.InputObjectPackRepository;

@Service
@Transactional
public class InputObjectPackService {
	
	@Autowired
	private InputObjectPackRepository inputObjectPackRepository;

	public List<InputObjectPack> getAllInputObjectPacks() {
		final List<InputObjectPack> inputObjectPacks = new ArrayList<InputObjectPack>();
		this.inputObjectPackRepository.findAll().forEach(inputObjectPacks::add);
		
		return inputObjectPacks;
	}
	
	public void addInputObjectPack(final InputObjectPack inputObjectPack) {
		this.inputObjectPackRepository.save(inputObjectPack);
	}
	
	public void removeAllObjectPacks() {
		this.inputObjectPackRepository.deleteAll();
	}
}
