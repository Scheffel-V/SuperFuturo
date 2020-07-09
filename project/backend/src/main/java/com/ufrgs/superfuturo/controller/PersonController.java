package com.ufrgs.superfuturo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ufrgs.superfuturo.logic.YoloParserLogic;
import com.ufrgs.superfuturo.model.Person;
import com.ufrgs.superfuturo.service.PersonService;

@CrossOrigin
@RestController
@RequestMapping("/api/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@PostMapping("/")
	public ResponseEntity<Person> createRealObject(@Valid @RequestBody final Person person) {
		this.personService.addPerson(person);
		
		final URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(person.getId())
				.toUri();
		
		return ResponseEntity.created(location).body(person);
	}
	
	@GetMapping("/")
	public List<Person> getPerson() {
		return this.personService.getAllPersons();
	}
	
	@PostMapping("/list")
	public ResponseEntity<List<Person>> createPerson(@Valid @RequestBody final List<Person> newPersons) {
		YoloParserLogic.processNewPersons(this.personService.getAllPersons(), newPersons);
		
		this.personService.removeAllPersons();
		this.personService.addPersons(newPersons);
	
		final URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/")
				.buildAndExpand()
				.toUri();
		
		return ResponseEntity.created(location).body(newPersons);
	}
}
