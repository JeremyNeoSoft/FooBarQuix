package com.github.jeremyneosoft.foobarquix;

import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class FooBarQuixService {

	public String transformToFooBarQuix(int number) {
		if (number < 0 || number > 100) {
			throw new IllegalArgumentException("Number must be between 0 and 100");
		}

		StringBuilder result = new StringBuilder();

		if (number % 3 == 0) {
			result.append("FOO");
		}
		if (number % 5 == 0) {
			result.append("BAR");
		}

		String numberString = String.valueOf(number);
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

	public String transformToFooBarQuixAlternative(int number) {
		if (number < 0 || number > 100) {
			throw new IllegalArgumentException("Number must be between 0 and 100");
		}

		StringBuilder result = new StringBuilder();

		if (number % 3 == 0) {
			result.append("FOO");
		}
		if (number % 5 == 0) {
			result.append("BAR");
		}

		String numberString = String.valueOf(number);

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
