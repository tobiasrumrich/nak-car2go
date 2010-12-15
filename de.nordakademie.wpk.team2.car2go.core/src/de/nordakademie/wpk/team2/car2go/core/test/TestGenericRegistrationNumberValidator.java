package de.nordakademie.wpk.team2.car2go.core.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.nordakademie.wpk.team2.car2go.core.GenericRegistrationNumberValidator;

/**
 * This test checks for appropriate behavior of the
 * ICarRegistrationNumberValidator implementation
 * 
 * @author dep18237
 * 
 */
public class TestGenericRegistrationNumberValidator {
	private GenericRegistrationNumberValidator grnv;

	@Before
	public void setUp() throws Exception {
		grnv = new GenericRegistrationNumberValidator();
	}

	@Test
	public void testValidateRegistrationNumberEmptyString() {
		assertEquals(false, grnv.validateRegistrationNumber(""));
	}

	@Test
	public void testValidateRegistrationNumberNullString() {
		assertEquals(false, grnv.validateRegistrationNumber(""));
	}

	@Test
	public void testValidateRegistrationNumber() {
		assertEquals(true, grnv.validateRegistrationNumber("XX-XX-XXXX"));
	}
}
