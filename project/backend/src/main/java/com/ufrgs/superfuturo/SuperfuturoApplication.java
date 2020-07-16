package com.ufrgs.superfuturo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ufrgs.superfuturo.logic.SuperFuturoLogic;


@SpringBootApplication
public class SuperfuturoApplication {

	public static void main(final String[] args) {
		SpringApplication.run(SuperfuturoApplication.class, args);
		SuperFuturoLogic.start();
	}
}
