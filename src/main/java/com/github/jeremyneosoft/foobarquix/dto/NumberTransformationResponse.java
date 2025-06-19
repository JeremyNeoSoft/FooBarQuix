package com.github.jeremyneosoft.foobarquix.dto;

public record NumberTransformationResponse(int inputNumber, String transformedString, String error) {

	public NumberTransformationResponse(int number, String transformedString) {
		this(number, transformedString, null);
	}
}