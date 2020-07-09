package com.ufrgs.superfuturo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrgs.superfuturo.model.RealObject;
import com.ufrgs.superfuturo.repository.RealObjectRepository;

@Service
@Transactional
public class RealObjectService {
	
	@Autowired
	private RealObjectRepository realObjectRepository;

	public void addRealObject(final RealObject realObject) {
		this.realObjectRepository.save(realObject);
	}
	
	public List<RealObject> getAllRealObjects() {
		final List<RealObject> realObjects = new ArrayList<RealObject>();
		this.realObjectRepository.findAll().forEach(realObjects::add);
		
		return realObjects;
	}
	
	public void removeAllObjects() {
		this.realObjectRepository.deleteAll();
	}
}
