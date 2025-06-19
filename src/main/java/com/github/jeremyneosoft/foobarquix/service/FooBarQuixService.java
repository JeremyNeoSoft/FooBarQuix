package com.github.jeremyneosoft.foobarquix.service;

import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

import com.github.jeremyneosoft.foobarquix.domain.ValidatedNumber;

@Service
public class FooBarQuixService {

	public String transformToFooBarQuix(ValidatedNumber validatedNumber) {

		StringBuilder result = new StringBuilder();

		if (validatedNumber.getValue() % 3 == 0) {
			result.append("FOO");
		}
		if (validatedNumber.getValue() % 5 == 0) {
			result.append("BAR");
		}

		String numberString = String.valueOf(validatedNumber.getValue());
		for (char c : numberString.toCharArray()) {
			if (c == '3') {
				result.append("FOO");
			}
			if (c == '5') {
				result.append("BAR");
			}
			if (c == '7') {
				result.append("QUIX");
			}
		}

		return result.isEmpty() ? numberString : result.toString();
	}

	public String transformToFooBarQuixAlternative(ValidatedNumber validatedNumber) {

		StringBuilder result = new StringBuilder();

		if (validatedNumber.getValue() % 3 == 0) {
			result.append("FOO");
		}
		if (validatedNumber.getValue() % 5 == 0) {
			result.append("BAR");
		}

		String numberString = String.valueOf(validatedNumber.getValue());

		IntStream.range(0, numberString.length()).mapToObj(i -> numberString.charAt(i)).forEach(c -> {
			if (c == '3') {
				result.append("FOO");
			}
			if (c == '5') {
				result.append("BAR");
			}
			if (c == '7') {
				result.append("QUIX");
			}
		});

		return result.isEmpty() ? numberString : result.toString();
	}

}
