package com.ufrgs.superfuturo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class SuperFuturoController {

	@GetMapping("/start")
	public ResponseEntity<Object>  startSuperFuturo() {	
		this.start();
		return ResponseEntity.ok().build();
	}
	
	private void start() {
		
	}
}
