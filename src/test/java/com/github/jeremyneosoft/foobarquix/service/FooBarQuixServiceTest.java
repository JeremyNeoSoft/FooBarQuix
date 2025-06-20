package com.github.jeremyneosoft.foobarquix.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.jeremyneosoft.foobarquix.domain.ValidatedNumber;

@SpringBootTest
public class FooBarQuixServiceTest {

	@Autowired
	private FooBarQuixService fooBarQuixService;

	private static final String FOO = "FOO";
	private static final String BAR = "BAR";
	private static final String QUIX = "QUIX";

	private static Stream<Arguments> provideNumbersForFooBarQuixTransfo() {
		return Stream.of(
				Arguments.of(1, "1"),
				Arguments.of(3, FOO + FOO),
				Arguments.of(5, BAR + BAR),
				Arguments.of(7, QUIX),
				Arguments.of(9, FOO),
				Arguments.of(15, FOO + BAR + BAR),
				Arguments.of(33, FOO + FOO + FOO),
				Arguments.of(51, FOO + BAR),
				Arguments.of(53, BAR + FOO));
	}

	@ParameterizedTest
	@MethodSource("provideNumbersForFooBarQuixTransfo")
	public void testTransformToFooBarQuix(int input, String expected) {
		assertEquals(expected, fooBarQuixService.transformToFooBarQuix(new ValidatedNumber(input)));
	}

	@ParameterizedTest
	@MethodSource("provideNumbersForFooBarQuixTransfo")
	public void testTransformToFooBarQuixAlternative(int input, String expected) {
		assertEquals(expected, fooBarQuixService.transformToFooBarQuixAlternative(new ValidatedNumber(input)));
	}

	@Test
	public void testInvalidNumber() {
		assertThrows(IllegalArgumentException.class, () -> new ValidatedNumber(-1));
		assertThrows(IllegalArgumentException.class, () -> new ValidatedNumber(101));
	}

}
