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
import com.ufrgs.superfuturo.model.InputObjectPack;
import com.ufrgs.superfuturo.service.InputObjectPackService;

@CrossOrigin
@RestController
@RequestMapping("/api/realobjectpack")
public class InputObjectPackController {

	@Autowired
	private InputObjectPackService inputObjectPackService;
	
	@PostMapping("/list")
	public ResponseEntity<List<InputObjectPack>> createInputObjectPackQuantityList(@Valid @RequestBody final List<InputObjectPack> InputObjectPackList) {
		YoloParserLogic.processNewObjectPackList(this.inputObjectPackService.getAllInputObjectPacks(), InputObjectPackList);
		
		this.inputObjectPackService.removeAllObjectPacks();
		
		for (final InputObjectPack object : InputObjectPackList) {
			this.inputObjectPackService.addInputObjectPack(object);
		}
		
		final URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/")
				.buildAndExpand()
				.toUri();
		
		return ResponseEntity.created(location).body(InputObjectPackList);
	}
}
