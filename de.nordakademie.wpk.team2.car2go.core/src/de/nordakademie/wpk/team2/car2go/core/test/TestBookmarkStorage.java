package de.nordakademie.wpk.team2.car2go.core.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.nordakademie.wpk.team2.car2go.core.BookmarkStorage;
import de.nordakademie.wpk.team2.car2go.core.businessobjects.Car;
import de.nordakademie.wpk.team2.car2go.core.exception.DuplicateBookmarkException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalRegistrationNumberException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalUsernameException;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;
import de.nordakademie.wpk.team2.car2go.core.exception.UsernameNotFoundException;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICar;


public class TestBookmarkStorage {
	private BookmarkStorage bs;
	private Car testCar1;
	private Car testCar2;
	private Car testCar3;
	
	@Before
	public void setUp() throws Exception {
		bs = new BookmarkStorage();
		
		testCar1 = new Car();
		testCar1.setRegistrationNumber("YY-YY-YYYY");
		testCar1.setVacantState(true);
		
		testCar2 = new Car();
		testCar2.setRegistrationNumber("XX-XX-XXXX");
		testCar2.setVacantState(true);
		
		testCar3 = new Car();
		testCar3.setRegistrationNumber("ZZ-ZZ-ZZZZ");
		testCar3.setVacantState(true);
		
		bs.addBookmark("Tester1", testCar1);
		bs.addBookmark("Tester1", testCar2);
		bs.addBookmark("Tester2", testCar1);
	}

	@Test
	public void testAddBookmark() throws DuplicateBookmarkException, IllegalUsernameException, IllegalRegistrationNumberException {
		bs.addBookmark("Tester3", testCar1);
		assertEquals(true, bs.getBookmarks().get("Tester3").contains(testCar1));
	}

	@Test (expected = DuplicateBookmarkException.class)
	public void testAddBookmarkDuplicate() throws DuplicateBookmarkException, IllegalUsernameException, IllegalRegistrationNumberException {
		bs.addBookmark("Tester3", testCar1);
		bs.addBookmark("Tester3", testCar1);
	}
	
	@Test (expected = IllegalUsernameException.class)
	public void testAddBookmarkEmptyUsername() throws DuplicateBookmarkException, IllegalUsernameException, IllegalRegistrationNumberException {
		bs.addBookmark("", testCar1);
	}
	
	@Test (expected = IllegalUsernameException.class)
	public void testAddBookmarkNullUsername() throws DuplicateBookmarkException, IllegalUsernameException, IllegalRegistrationNumberException {
		bs.addBookmark(null, testCar1);
	}
	
	@Test (expected = IllegalRegistrationNumberException.class)
	public void testAddBookmarkNullCar() throws DuplicateBookmarkException, IllegalUsernameException, IllegalRegistrationNumberException {
		bs.addBookmark("Tester3", null);
	}
	
	@Test (expected = IllegalRegistrationNumberException.class)
	public void testAddBookmarkNullRegistrationNumber() throws DuplicateBookmarkException, IllegalUsernameException, IllegalRegistrationNumberException {
		Car c = new Car();
		bs.addBookmark("Tester3", c);
	}
	
	@Test (expected = IllegalRegistrationNumberException.class)
	public void testAddBookmarkEmptyRegistrationNumber() throws DuplicateBookmarkException, IllegalUsernameException, IllegalRegistrationNumberException {
		Car c = new Car();
		c.setRegistrationNumber("");
		
		bs.addBookmark("Tester3", c);
	}
	
	@Test
	public void testGetBookmarksString() throws IllegalUsernameException {
		assertEquals(true, bs.getBookmarks("Tester1").contains(testCar1));
		assertEquals(true, bs.getBookmarks("Tester1").contains(testCar2));
		assertEquals(true, bs.getBookmarks("Tester2").contains(testCar1));
	}

	@Test (expected = IllegalUsernameException.class)
	public void testGetBookmarksNullString() throws IllegalUsernameException {
		bs.getBookmarks(null);
	}
	
	@Test (expected = IllegalUsernameException.class)
	public void testGetBookmarksEmptyString() throws IllegalUsernameException {
		bs.getBookmarks("");
	}
	
	@Test
	public void testGetBookmarksEmpty() throws IllegalUsernameException {
		assertEquals(true, bs.getBookmarks("Tester3").isEmpty());
	}
	
	@Test
	public void testRemoveBookmark() throws IllegalUsernameException, RegistrationNumberNotFoundException, IllegalRegistrationNumberException, UsernameNotFoundException {
		assertEquals(true, bs.getBookmarks("Tester1").contains(testCar1));
		assertEquals(true, bs.getBookmarks("Tester1").contains(testCar2));
		
		bs.removeBookmark("Tester1", testCar1.getRegistrationNumber());
		
		assertEquals(false, bs.getBookmarks("Tester1").contains(testCar1));
		assertEquals(true, bs.getBookmarks("Tester1").contains(testCar2));
	}

	@Test (expected = IllegalUsernameException.class)
	public void testRemoveBookmarkUsernameNull() throws IllegalUsernameException, RegistrationNumberNotFoundException, IllegalRegistrationNumberException, UsernameNotFoundException {
		bs.removeBookmark(null, testCar1.getRegistrationNumber());
	}
	
	@Test (expected = IllegalUsernameException.class)
	public void testRemoveBookmarkUsernameEmpty() throws IllegalUsernameException, RegistrationNumberNotFoundException, IllegalRegistrationNumberException, UsernameNotFoundException {
		bs.removeBookmark("", testCar1.getRegistrationNumber());
	}
	
	@Test (expected = UsernameNotFoundException.class)
	public void testRemoveBookmarkWrongUsername() throws IllegalUsernameException, RegistrationNumberNotFoundException, IllegalRegistrationNumberException, UsernameNotFoundException {
		bs.removeBookmark("Tester3", testCar1.getRegistrationNumber());
	}
	
	@Test (expected = IllegalRegistrationNumberException.class)
	public void testRemoveBookmarkRegistrationNumberNull() throws IllegalUsernameException, RegistrationNumberNotFoundException, IllegalRegistrationNumberException, UsernameNotFoundException {
		bs.removeBookmark("Tester1", null);
	}
	
	@Test (expected = IllegalRegistrationNumberException.class)
	public void testRemoveBookmarkRegistrationNumberEmpty() throws IllegalUsernameException, RegistrationNumberNotFoundException, IllegalRegistrationNumberException, UsernameNotFoundException {
		bs.removeBookmark("Tester1", "");
	}
	
	@Test (expected = RegistrationNumberNotFoundException.class)
	public void testRemoveBookmarkWrongRegistrationNumber() throws IllegalUsernameException, RegistrationNumberNotFoundException, IllegalRegistrationNumberException, UsernameNotFoundException {
		bs.removeBookmark("Tester1", "CC-CC-CCCC");
	}
	
	@Test
	public void testUpdateBookmarkedCars() {
		assertEquals(true, testCar1.getVacantState());
		assertEquals(true, testCar2.getVacantState());
		assertEquals(true, testCar3.getVacantState());
		
		Set<ICar> updateSet = new HashSet<ICar>();
		updateSet.add(testCar1);
		
		bs.updateBookmarkedCars(updateSet);
		
		assertEquals(true, testCar1.getVacantState());
		assertEquals(false, testCar2.getVacantState());
		assertEquals(true, testCar3.getVacantState());
	}
}
