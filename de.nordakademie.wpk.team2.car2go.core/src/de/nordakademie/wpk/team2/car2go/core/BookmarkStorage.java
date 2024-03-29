package de.nordakademie.wpk.team2.car2go.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import de.nordakademie.wpk.team2.car2go.core.businessobjects.Car;
import de.nordakademie.wpk.team2.car2go.core.exception.DuplicateBookmarkException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalRegistrationNumberException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalUsernameException;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;
import de.nordakademie.wpk.team2.car2go.core.exception.UsernameNotFoundException;
import de.nordakademie.wpk.team2.car2go.core.interfaces.IBookmarkStorage;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICar;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICarRegistrationNumberValidator;

/**
 * The BookmarkStorage stored which cars have been bookmarked by users
 * 
 * @author Moehring, Rumrich
 * 
 */
public class BookmarkStorage implements IBookmarkStorage {
	private static final Logger logger = Logger
			.getLogger(BookmarkStorage.class);
	private HashMap<String, HashSet<ICar>> bookmarks = new HashMap<String, HashSet<ICar>>();
	private ICarRegistrationNumberValidator registrationNumberValidator = new GenericRegistrationNumberValidator();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.nordakademie.wpk.team2.car2go.core.interfaces.IBookmarkStorage#addBookmark
	 * (java.lang.String, de.nordakademie.wpk.team2.car2go.core.interfaces.ICar)
	 */
	@Override
	public void addBookmark(String username, ICar car)
			throws DuplicateBookmarkException, IllegalUsernameException,
			IllegalRegistrationNumberException {
		HashSet<ICar> carHashSet;

		if (username == null || username.equals("")) {
			throw new IllegalUsernameException();
		}

		if (car == null
				|| !registrationNumberValidator.validateRegistrationNumber(car
						.getRegistrationNumber())) {
			throw new IllegalRegistrationNumberException();
		}

		if (bookmarks.containsKey(username)) {
			carHashSet = bookmarks.get(username);
		} else {
			logger.info("Create new bookmarklist for user " + username);
			carHashSet = new HashSet<ICar>();
			bookmarks.put(username, carHashSet);
		}

		if (!carHashSet.add(car)) {
			throw new DuplicateBookmarkException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.nordakademie.wpk.team2.car2go.core.interfaces.IBookmarkStorage#
	 * getBookmarks(java.lang.String)
	 */
	@Override
	public Set<ICar> getBookmarks(String username)
			throws IllegalUsernameException {
		if (username == null || username.equals("")) {
			throw new IllegalUsernameException();
		}

		if (bookmarks.containsKey(username)) {
			return bookmarks.get(username);
		} else {
			return new HashSet<ICar>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.nordakademie.wpk.team2.car2go.core.interfaces.IBookmarkStorage#
	 * removeBookmark(java.lang.String, java.lang.String)
	 */
	@Override
	public void removeBookmark(String username, String registrationNumber)
			throws RegistrationNumberNotFoundException,
			IllegalRegistrationNumberException, UsernameNotFoundException,
			IllegalUsernameException {
		if (username == null || username.equals("")) {
			throw new IllegalUsernameException();
		}

		if (!registrationNumberValidator
				.validateRegistrationNumber(registrationNumber)) {
			throw new IllegalRegistrationNumberException();
		}

		Car searchCar = new Car();
		searchCar.setRegistrationNumber(registrationNumber);

		if (bookmarks.containsKey(username)) {
			if (!bookmarks.get(username).remove(searchCar)) {
				throw new RegistrationNumberNotFoundException();
			}
		} else {
			throw new UsernameNotFoundException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see de.nordakademie.wpk.team2.car2go.core.interfaces.IBookmarkStorage#updateBookmarkedCars(java.util.Set)
	 */
	@Override
	public void updateBookmarkedCars(Set<ICar> vacantCars) {
		for (String key : bookmarks.keySet()) {
			HashSet<ICar> hashSet = bookmarks.get(key);

			for (ICar iCar : hashSet) {
				iCar.setVacantState(vacantCars.contains(iCar));
			}
		}
	}

	/**
	 * Returns the HashMap with all cars stored by users
	 * @return HashMap<String, HashSet<ICar>>
	 */
	public HashMap<String, HashSet<ICar>> getBookmarks() {
		return bookmarks;
	}
}
