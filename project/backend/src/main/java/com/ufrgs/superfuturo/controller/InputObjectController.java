package com.ufrgs.superfuturo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ufrgs.superfuturo.logic.YoloParserLogic;
import com.ufrgs.superfuturo.model.InputObject;
import com.ufrgs.superfuturo.service.InputObjectService;

@CrossOrigin
@RestController
@RequestMapping("/api/inputobject")
public class InputObjectController {

	@Autowired
	private InputObjectService inputObjectService;
	
	@PostMapping("/list")
	public ResponseEntity<List<InputObject>> createInputObjectList(@Valid @RequestBody final List<InputObject> inputObjects) {
		// YoloParserLogic.processNewInputObjects(this.inputObjectService.getAllInputObjects(), inputObjects);
		YoloParserLogic.commitTransactions();
		
		this.inputObjectService.removeAllInputObjects();
		this.inputObjectService.addInputObjects(inputObjects);
	
		final URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/")
				.buildAndExpand()
				.toUri();
		
		return ResponseEntity.created(location).body(inputObjects);
	}
}
