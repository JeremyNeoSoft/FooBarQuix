package com.github.jeremyneosoft.foobarquix.domain;

public class ValidatedNumber {

	private final int value;

	public ValidatedNumber(int value) {
		if (value < 0 || value > 100) {
			throw new IllegalArgumentException("Number must be between 0 and 100");
		}
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
