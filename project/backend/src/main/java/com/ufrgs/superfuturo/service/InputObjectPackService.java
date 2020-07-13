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
	private InputObjectPackRepository realObjectPackRepository;

	public List<InputObjectPack> getAllRealObjectPacks() {
		final List<InputObjectPack> realObjectPacks = new ArrayList<InputObjectPack>();
		this.realObjectPackRepository.findAll().forEach(realObjectPacks::add);
		
		return realObjectPacks;
	}
	
	public void addRealObjectPack(final InputObjectPack realObjectPack) {
		this.realObjectPackRepository.save(realObjectPack);
	}
	
	public void removeAllObjectPacks() {
		this.realObjectPackRepository.deleteAll();
	}
}
