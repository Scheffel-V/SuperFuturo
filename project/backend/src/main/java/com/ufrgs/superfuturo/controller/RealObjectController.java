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

import com.ufrgs.superfuturo.model.RealObject;
import com.ufrgs.superfuturo.service.RealObjectService;

@CrossOrigin
@RestController
@RequestMapping("/api/realobject")
public class RealObjectController {

	@Autowired
	private RealObjectService realObjectService;
	
	@PostMapping("/")
	public ResponseEntity<RealObject> createRealObject(@Valid @RequestBody RealObject realObject) {
		realObjectService.addRealObject(realObject);
		
		final URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(realObject.getId())
				.toUri();
		
		return ResponseEntity.created(location).body(realObject);
	}
	
	@GetMapping("/")
	public List<RealObject> getRealObject() {
		return realObjectService.getAllRealObjects();
	}
	
	@PostMapping("/list")
	public ResponseEntity<List<RealObject>> createRealObjectList(@Valid @RequestBody List<RealObject> realObjectList) {
		YoloParserController.processNewObjectsList(realObjectService.getAllRealObjects(), realObjectList);
		
		realObjectService.removeAllObjects();
		
		for (final RealObject object : realObjectList) {
			realObjectService.addRealObject(object);
		}
		
		final URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/")
				.buildAndExpand()
				.toUri();
		
		return ResponseEntity.created(location).body(realObjectList);
	}
}
