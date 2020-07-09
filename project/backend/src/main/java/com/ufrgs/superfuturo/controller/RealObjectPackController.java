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
import com.ufrgs.superfuturo.model.RealObjectPack;
import com.ufrgs.superfuturo.service.RealObjectPackService;

@CrossOrigin
@RestController
@RequestMapping("/api/realobjectpack")
public class RealObjectPackController {

	@Autowired
	private RealObjectPackService realObjectPackService;
	
	@PostMapping("/")
	public ResponseEntity<RealObjectPack> createRealObjectPack(@Valid @RequestBody final RealObjectPack RealObjectPack) {
		this.realObjectPackService.addRealObjectPack(RealObjectPack);
		
		final URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(RealObjectPack.getId())
				.toUri();
		
		return ResponseEntity.created(location).body(RealObjectPack);
	}
	
	@GetMapping("/")
	public List<RealObjectPack> getRealObjectPack() {
		return this.realObjectPackService.getAllRealObjectPacks();
	}
	
	@PostMapping("/list")
	public ResponseEntity<List<RealObjectPack>> createRealObjectPackQuantityList(@Valid @RequestBody final List<RealObjectPack> RealObjectPackList) {
		YoloParserLogic.processNewObjectPackList(this.realObjectPackService.getAllRealObjectPacks(), RealObjectPackList);
		
		this.realObjectPackService.removeAllObjectPacks();
		
		for (final RealObjectPack object : RealObjectPackList) {
			this.realObjectPackService.addRealObjectPack(object);
		}
		
		final URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/")
				.buildAndExpand()
				.toUri();
		
		return ResponseEntity.created(location).body(RealObjectPackList);
	}
}
