package com.github.jeremyneosoft.foobarquix.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.jeremyneosoft.foobarquix.domain.ValidatedNumber;

@SpringBootTest
public class FooBarQuixServiceTest {

	@Autowired
	private FooBarQuixService fooBarQuixService;

	@Test
	public void testFooBarQuix() {
		assertEquals("1", fooBarQuixService.transformToFooBarQuix(new ValidatedNumber(1)));
		assertEquals("FOOFOO", fooBarQuixService.transformToFooBarQuix(new ValidatedNumber(3)));
		assertEquals("BARBAR", fooBarQuixService.transformToFooBarQuix(new ValidatedNumber(5)));
		assertEquals("QUIX", fooBarQuixService.transformToFooBarQuix(new ValidatedNumber(7)));
		assertEquals("FOO", fooBarQuixService.transformToFooBarQuix(new ValidatedNumber(9)));
		assertEquals("FOOBARBAR", fooBarQuixService.transformToFooBarQuix(new ValidatedNumber(15)));
		assertEquals("FOOFOOFOO", fooBarQuixService.transformToFooBarQuix(new ValidatedNumber(33)));
		assertEquals("FOOBAR", fooBarQuixService.transformToFooBarQuix(new ValidatedNumber(51)));
		assertEquals("BARFOO", fooBarQuixService.transformToFooBarQuix(new ValidatedNumber(53)));
	}

	@Test
	public void testFooBarQuixAlternative() {
		assertEquals("1", fooBarQuixService.transformToFooBarQuixAlternative(new ValidatedNumber(1)));
		assertEquals("FOOFOO", fooBarQuixService.transformToFooBarQuixAlternative(new ValidatedNumber(3)));
		assertEquals("BARBAR", fooBarQuixService.transformToFooBarQuixAlternative(new ValidatedNumber(5)));
		assertEquals("QUIX", fooBarQuixService.transformToFooBarQuixAlternative(new ValidatedNumber(7)));
		assertEquals("FOO", fooBarQuixService.transformToFooBarQuixAlternative(new ValidatedNumber(9)));
		assertEquals("FOOBARBAR", fooBarQuixService.transformToFooBarQuixAlternative(new ValidatedNumber(15)));
		assertEquals("FOOFOOFOO", fooBarQuixService.transformToFooBarQuixAlternative(new ValidatedNumber(33)));
		assertEquals("FOOBAR", fooBarQuixService.transformToFooBarQuixAlternative(new ValidatedNumber(51)));
		assertEquals("BARFOO", fooBarQuixService.transformToFooBarQuixAlternative(new ValidatedNumber(53)));
	}
	
	@Test
    public void testInvalidNumber() {
        assertThrows(IllegalArgumentException.class, () -> new ValidatedNumber(-1));
        assertThrows(IllegalArgumentException.class, () -> new ValidatedNumber(101));
    }

}
