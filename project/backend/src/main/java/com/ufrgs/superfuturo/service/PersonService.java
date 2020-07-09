package com.ufrgs.superfuturo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrgs.superfuturo.model.Person;
import com.ufrgs.superfuturo.repository.PersonRepository;

@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public void addPerson(final Person person) {
		this.personRepository.save(person);
	}

	public List<Person> getAllPersons() {
		final List<Person> persons = new ArrayList<Person>();
		this.personRepository.findAll().forEach(persons::add);
		
		return persons;
	}
	
	public void addPersons(final List<Person> persons) {
		this.personRepository.saveAll(persons);
	}
	
	public void removeAllPersons() {
		this.personRepository.deleteAll();
	}
}
