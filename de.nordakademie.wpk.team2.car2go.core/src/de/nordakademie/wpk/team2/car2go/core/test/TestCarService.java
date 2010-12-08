package de.nordakademie.wpk.team2.car2go.core.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.nordakademie.wpk.team2.car2go.core.CarService;
import de.nordakademie.wpk.team2.car2go.core.businessobjects.Car;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalCommentException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalRegistrationNumberException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalUsernameException;
import de.nordakademie.wpk.team2.car2go.core.exception.MapRetrievalException;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICar;



public class TestCarService {
	private CarService cs;
	private Car testCar1;
	private Car testCar2;
	private Car testCar3;
	private Set<ICar> cars;
	private String testUser1;
	private String testUser2;
	
	@Before
	public void setUp() throws Exception {
		cs = new CarService();
		cars = new HashSet<ICar>();
		
		testCar1 = new Car();
		testCar1.setRegistrationNumber("YY-YY-YYYY");
		testCar1.setVacantState(true);
		
		testCar2 = new Car();
		testCar2.setRegistrationNumber("XX-XX-XXXX");
		testCar2.setVacantState(true);
		
		testCar3 = new Car();
		testCar3.setRegistrationNumber("ZZ-ZZ-ZZZZ");
		testCar3.setVacantState(true);
		
		cars.add(testCar1);
		cars.add(testCar2);
		cars.add(testCar3);
		
		testUser1 = "Tester1";
		testUser2 = "Tester2";
		
		cs.pushCarStorageUdate(cars);
		cs.addToBookmark(testCar1.getRegistrationNumber(), testUser1);
		cs.addToBookmark(testCar2.getRegistrationNumber(), testUser1);
		cs.addToBookmark(testCar1.getRegistrationNumber(), testUser2);
	}

	@Test
	public void testGetVacantCars() {
		assertEquals(cars, cs.getVacantCars());
	}
	
	@Test
	public void testGetBookmarks() throws IllegalUsernameException {
		assertEquals(true, cs.getBookmarkedCars(testUser1).contains(testCar1));
		assertEquals(true, cs.getBookmarkedCars(testUser1).contains(testCar2));
		assertEquals(false, cs.getBookmarkedCars(testUser1).contains(testCar3));
		
		assertEquals(false, cs.getVacantCars(testUser1).contains(testCar1));
		assertEquals(false, cs.getVacantCars(testUser1).contains(testCar2));
		assertEquals(true, cs.getVacantCars(testUser1).contains(testCar3));
		
		assertEquals(true, cs.getBookmarkedCars(testUser2).contains(testCar1));
		assertEquals(false, cs.getBookmarkedCars(testUser2).contains(testCar2));
		assertEquals(false, cs.getBookmarkedCars(testUser2).contains(testCar3));
		
		assertEquals(false, cs.getVacantCars(testUser2).contains(testCar1));
		assertEquals(true, cs.getVacantCars(testUser2).contains(testCar2));
		assertEquals(true, cs.getVacantCars(testUser2).contains(testCar3));
	}
	
	@Test
	public void testUpdateCars() throws IllegalUsernameException {
		Set<ICar> updateCars = new HashSet<ICar>();
		updateCars.add(testCar2);
		
		cs.pushCarStorageUdate(updateCars);
		
		assertEquals(true, cs.getVacantCars().contains(testCar2));
		assertEquals(false, cs.getVacantCars().contains(testCar1));
		assertEquals(false, cs.getVacantCars().contains(testCar3));
		
		assertEquals(true, cs.getBookmarkedCars(testUser1).contains(testCar1));
		assertEquals(true, cs.getBookmarkedCars(testUser1).contains(testCar2));
		assertEquals(false, cs.getBookmarkedCars(testUser1).contains(testCar3));
		
		assertEquals(true, cs.getBookmarkedCars(testUser2).contains(testCar1));
		assertEquals(false, cs.getBookmarkedCars(testUser2).contains(testCar2));
		assertEquals(false, cs.getBookmarkedCars(testUser2).contains(testCar3));
		
		assertEquals(true, testCar2.getVacantState());
		assertEquals(false, testCar1.getVacantState());
	}
	
	@Test
	public void testSetComment() throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException, IllegalCommentException {
		cs.setComment("ZZ-ZZ-ZZZZ", "Test");
		assertEquals("Test", testCar3.getComment());
	}
	
	@Test(expected = IllegalRegistrationNumberException.class)
	public void testSetCommentNullRegistrationNumber() throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException, IllegalCommentException {
		cs.setComment(null, "");
	}
	
	@Test(expected = IllegalCommentException.class)
	public void testSetCommentEmptyRegistrationNumber() throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException, IllegalCommentException {
		cs.setComment("ZZ-ZZ-ZZZZ", null);
	}
	
	@Test(expected = RegistrationNumberNotFoundException.class)
	public void testSetCommentWrongRegistrationNumber() throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException, IllegalCommentException {
		cs.setComment("CC-CC-CCCC", "Test");
	}
	
	@Test(expected = RegistrationNumberNotFoundException.class)
	public void testGetMapWrongRegistrationNumber() throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException, MapRetrievalException {
		cs.getMapForCar("CC-CC-CCCC", 100, 100, 10);
	}
	
	@Test(expected = IllegalRegistrationNumberException.class)
	public void testGetMapNullRegistrationNumber() throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException, MapRetrievalException {
		cs.getMapForCar(null, 100, 100, 10);
	}
	
	@Test(expected = IllegalRegistrationNumberException.class)
	public void testGetMapEmptyRegistrationNumber() throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException, MapRetrievalException {
		cs.getMapForCar("", 100, 100, 10);
	}
	
	@Test(expected = MapRetrievalException.class)
	public void testGetMapWrongWidth() throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException, IllegalCommentException, MapRetrievalException {
		cs.getMapForCar("ZZ-ZZ-ZZZZ", 1, 100, 10);
	}
	
	@Test(expected = MapRetrievalException.class)
	public void testGetMapWrongHeight() throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException, IllegalCommentException, MapRetrievalException {
		cs.getMapForCar("ZZ-ZZ-ZZZZ", 100, 1, 10);
	}
	
	@Test(expected = MapRetrievalException.class)
	public void testGetMapWrongZoom() throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException, IllegalCommentException, MapRetrievalException {
		cs.getMapForCar("ZZ-ZZ-ZZZZ", 100, 100, 0);
	}
}
