package com.ufrgs.superfuturo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ufrgs.superfuturo.logic.YoloParserLogic;
import com.ufrgs.superfuturo.model.InputObject;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/inputobjectfromfrontcamera", method = RequestMethod.POST)
public class InputObjectFromFrontCameraController {
	
	@PostMapping("/list")
	public ResponseEntity<List<InputObject>> createInputObjectList(@Valid @RequestBody final List<InputObject> inputObjects) {
		System.out.println("PRINTING A LOT OF STUFF: front: ");
		InputObject.printListOfInputObjects(inputObjects);
		YoloParserLogic.processFrontInputObjects(inputObjects);
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
		System.out.println("PRINTING A LOT OF STUFF: front: ");
		InputObject.printListOfInputObjects(inputObjects);

		YoloParserLogic.setupFrontInputObjects(inputObjects);

		final URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/")
				.buildAndExpand()
				.toUri();
		
		return ResponseEntity.created(location).body(inputObjects);
	}
}
