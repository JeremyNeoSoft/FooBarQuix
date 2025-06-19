package com.github.jeremyneosoft.foobarquix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FooBarQuixServiceTest {

	@Autowired
	private FooBarQuixService fooBarQuixService;

	@Test
	public void testFooBarQuix() {
		assertEquals("1", fooBarQuixService.transformToFooBarQuix(1));
		assertEquals("FOOFOO", fooBarQuixService.transformToFooBarQuix(3));
		assertEquals("BARBAR", fooBarQuixService.transformToFooBarQuix(5));
		assertEquals("QUIX", fooBarQuixService.transformToFooBarQuix(7));
		assertEquals("FOO", fooBarQuixService.transformToFooBarQuix(9));
		assertEquals("FOOBARBAR", fooBarQuixService.transformToFooBarQuix(15));
		assertEquals("FOOFOOFOO", fooBarQuixService.transformToFooBarQuix(33));
		assertEquals("FOOBAR", fooBarQuixService.transformToFooBarQuix(51));
		assertEquals("BARFOO", fooBarQuixService.transformToFooBarQuix(53));
	}

	@Test
	public void testFooBarQuixAlternative() {
		assertEquals("1", fooBarQuixService.transformToFooBarQuixAlternative(1));
		assertEquals("FOOFOO", fooBarQuixService.transformToFooBarQuixAlternative(3));
		assertEquals("BARBAR", fooBarQuixService.transformToFooBarQuixAlternative(5));
		assertEquals("QUIX", fooBarQuixService.transformToFooBarQuixAlternative(7));
		assertEquals("FOO", fooBarQuixService.transformToFooBarQuixAlternative(9));
		assertEquals("FOOBARBAR", fooBarQuixService.transformToFooBarQuixAlternative(15));
		assertEquals("FOOFOOFOO", fooBarQuixService.transformToFooBarQuixAlternative(33));
		assertEquals("FOOBAR", fooBarQuixService.transformToFooBarQuixAlternative(51));
		assertEquals("BARFOO", fooBarQuixService.transformToFooBarQuixAlternative(53));
	}

}
