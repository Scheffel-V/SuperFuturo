package com.ufrgs.superfuturo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrgs.superfuturo.model.InputPerson;
import com.ufrgs.superfuturo.repository.InputPersonRepository;

@Service
@Transactional
public class InputPersonService {

	@Autowired
	private InputPersonRepository personRepository;
	
	public void addPerson(final InputPerson person) {
		this.personRepository.save(person);
	}

	public List<InputPerson> getAllPersons() {
		final List<InputPerson> persons = new ArrayList<InputPerson>();
		this.personRepository.findAll().forEach(persons::add);
		
		return persons;
	}
	
	public void addPersons(final List<InputPerson> persons) {
		this.personRepository.saveAll(persons);
	}
	
	public void removeAllPersons() {
		this.personRepository.deleteAll();
	}
}
