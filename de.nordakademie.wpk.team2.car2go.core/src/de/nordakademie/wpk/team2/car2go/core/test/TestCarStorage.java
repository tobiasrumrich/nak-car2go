package de.nordakademie.wpk.team2.car2go.core.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.nordakademie.wpk.team2.car2go.core.CarStorage;
import de.nordakademie.wpk.team2.car2go.core.businessobjects.Car;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalRegistrationNumberException;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICarStorage;


public class TestCarStorage {
	private ICarStorage cs;
	private Car testCar1, testCar2, testCar3;
	
	@Before
	public void setUp() throws Exception {
		cs = new CarStorage();
		testCar1 = new Car();
		testCar1.setRegistrationNumber("XX-XX-XXXX");
		
		testCar2 = new Car();
		testCar2.setRegistrationNumber("YY-YY-YYYY");
		
		testCar3 = new Car();
		testCar3.setRegistrationNumber("ZZ-ZZ-ZZZZ");
		cs.addCar(testCar3);
	}

	@Test(expected = IllegalRegistrationNumberException.class)
	public void testAddCarNull() throws IllegalRegistrationNumberException {
		cs.addCar(null);
	}

	@Test(expected = IllegalRegistrationNumberException.class)
	public void testAddCarNoRegistrationNumber() throws IllegalRegistrationNumberException {
		Car c = new Car();
		cs.addCar(c);
	}
	
	@Test
	public void testAddCar() throws IllegalRegistrationNumberException {
		cs.addCar(testCar1);
		assertEquals(true, cs.getCarSet().contains(testCar1));
		
		cs.addCar(testCar2);
		assertEquals(true, cs.getCarSet().contains(testCar2));
	}

	@Test(expected = IllegalRegistrationNumberException.class)
	public void testAddCarDoubleCar() throws IllegalRegistrationNumberException {
		cs.addCar(testCar3);
	}
	
	@Test
	public void testFindCar() throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException {
		assertEquals(testCar3, cs.findCar("ZZ-ZZ-ZZZZ"));
	}
	
	@Test
	public void testFindCarLowerCase() throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException {
		assertEquals(testCar3, cs.findCar("zz-zz-zzzz"));
	}
	
	@Test (expected = IllegalRegistrationNumberException.class)
	public void testFindCarNull() throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException {
		cs.findCar(null);
	}

	@Test (expected = RegistrationNumberNotFoundException.class)
	public void testFindCarWrongRegistrationNumber() throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException {
		cs.findCar("CC-CC-CCCC");
	}
	
	@Test(expected = IllegalRegistrationNumberException.class)
	public void testRemoveCarNoRegistrationNumber() throws IllegalRegistrationNumberException, RegistrationNumberNotFoundException {
		cs.removeCar(new Car());
	}

	@Test(expected = IllegalRegistrationNumberException.class)
	public void testRemoveCarEmptyString() throws IllegalRegistrationNumberException, RegistrationNumberNotFoundException {
		cs.removeCar("");
	}
	
	@Test(expected = RegistrationNumberNotFoundException.class)
	public void testRemoveCarWrongCar() throws IllegalRegistrationNumberException, RegistrationNumberNotFoundException {
		Car c = new Car();
		c.setRegistrationNumber("CC-CC-CCCC");
		
		cs.removeCar(c);
	}
	
	@Test (expected = RegistrationNumberNotFoundException.class)
	public void testRemoveCarWrongCarString() throws IllegalRegistrationNumberException, RegistrationNumberNotFoundException {
		cs.removeCar("YY-YY-YYYY");
	}
	
	@Test
	public void testRemoveCarString() throws IllegalRegistrationNumberException, RegistrationNumberNotFoundException {
		assertEquals(true, cs.getCarSet().contains(testCar3));
		cs.removeCar(testCar3);
		assertEquals(false, cs.getCarSet().contains(testCar3));
	}

	
}
