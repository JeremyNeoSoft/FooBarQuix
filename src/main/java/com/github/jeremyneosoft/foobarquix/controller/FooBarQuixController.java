package com.github.jeremyneosoft.foobarquix.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.jeremyneosoft.foobarquix.domain.ValidatedNumber;
import com.github.jeremyneosoft.foobarquix.dto.NumberTransformationResponse;
import com.github.jeremyneosoft.foobarquix.service.FooBarQuixService;

@RestController
public class FooBarQuixController {

	private final FooBarQuixService fooBarQuixService;

	public FooBarQuixController(FooBarQuixService fooBarQuixService) {
		this.fooBarQuixService = fooBarQuixService;
	}

	@GetMapping("/foobarquix")
	public ResponseEntity<NumberTransformationResponse> fooBarQuix(@RequestParam int number) {
		try {
			ValidatedNumber validatedNumber = new ValidatedNumber(number);
			String transformedString = fooBarQuixService.transformToFooBarQuix(validatedNumber);
			return ResponseEntity.ok(new NumberTransformationResponse(number, transformedString));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new NumberTransformationResponse(number, null, e.getMessage()));
		}
	}

	@GetMapping("/foobarquix-alt")
	public ResponseEntity<NumberTransformationResponse> fooBarQuixAlternative(@RequestParam int number) {
		try {
			ValidatedNumber validatedNumber = new ValidatedNumber(number);
			String transformedString = fooBarQuixService.transformToFooBarQuixAlternative(validatedNumber);
			return ResponseEntity.ok(new NumberTransformationResponse(number, transformedString));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new NumberTransformationResponse(number, null, e.getMessage()));
		}
	}

}
