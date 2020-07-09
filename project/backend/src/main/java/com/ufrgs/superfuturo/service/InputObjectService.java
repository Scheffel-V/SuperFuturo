package com.ufrgs.superfuturo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrgs.superfuturo.model.InputObject;
import com.ufrgs.superfuturo.repository.InputObjectRepository;

@Service
@Transactional
public class InputObjectService {
	
	@Autowired
	private InputObjectRepository inputObjectRepository;

	public List<InputObject> getAllInputObjects() {
		final List<InputObject> InputObjects = new ArrayList<InputObject>();
		this.inputObjectRepository.findAll().forEach(InputObjects::add);
		
		return InputObjects;
	}
	
	public void addInputObjects(final List<InputObject> InputObjects) {
		this.inputObjectRepository.saveAll(InputObjects);
	}
	
	public void removeAllInputObjects() {
		this.inputObjectRepository.deleteAll();
	}
}
