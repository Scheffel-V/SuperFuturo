package com.ufrgs.superfuturo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ufrgs.superfuturo.logic.YoloParserLogic;
import com.ufrgs.superfuturo.model.InputObject;


@CrossOrigin
@RestController
@RequestMapping("/api/inputobjectfromtopcamera")
public class InputObjectFromTopCameraController {
	
	@PostMapping("/list")
	public ResponseEntity<List<InputObject>> createInputObjectPackQuantityList(@Valid @RequestBody final List<InputObject> inputObjects) {
		YoloParserLogic.processTopInputObjects(inputObjects);
		YoloParserLogic.commitTransactions();
		
		final URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/")
				.buildAndExpand()
				.toUri();
		
		return ResponseEntity.created(location).body(inputObjects);
	}
	
	@PostMapping("/startlist")
	public ResponseEntity<List<InputObject>> createStartInputObjectList(@Valid @RequestBody final List<InputObject> inputObjects) {
		YoloParserLogic.setupTopInputObjects(inputObjects);

		final URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/")
				.buildAndExpand()
				.toUri();
		
		return ResponseEntity.created(location).body(inputObjects);
	}
}
