package com.ufrgs.superfuturo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrgs.superfuturo.model.RealObjectPack;
import com.ufrgs.superfuturo.repository.RealObjectPackRepository;

@Service
@Transactional
public class RealObjectPackService {
	
	@Autowired
	private RealObjectPackRepository realObjectPackRepository;

	public List<RealObjectPack> getAllRealObjectPacks() {
		final List<RealObjectPack> realObjectPacks = new ArrayList<RealObjectPack>();
		this.realObjectPackRepository.findAll().forEach(realObjectPacks::add);
		
		return realObjectPacks;
	}
	
	public void addRealObjectPack(final RealObjectPack realObjectPack) {
		this.realObjectPackRepository.save(realObjectPack);
	}
	
	public void removeAllObjectPacks() {
		this.realObjectPackRepository.deleteAll();
	}
}
