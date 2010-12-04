package de.nordakademie.wpk.team2.car2go.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import de.nordakademie.wpk.team2.car2go.core.businessobjects.Car;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;
import de.nordakademie.wpk.team2.car2go.interfaces.IBookmarkStorage;
import de.nordakademie.wpk.team2.car2go.interfaces.ICar;

public class BookmarkStorage implements IBookmarkStorage {
	private static final Logger logger = Logger.getLogger(BookmarkStorage.class);
	private HashMap<String, HashSet<ICar>> bookmarks = new HashMap<String, HashSet<ICar>>();
	
	@Override
	public void addBookmark(String username, ICar car) {
		HashSet<ICar> carHashSet;

		if (bookmarks.containsKey(username)) {
			carHashSet = bookmarks.get(username);
		} else {
			logger.info("Create new bookmarklist for user " + username);
			carHashSet = new HashSet<ICar>();
			bookmarks.put(username, carHashSet);
		}
		
		carHashSet.add(car);
	}

	@Override
	public Set<ICar> getBookmarks(String username) {
		if (bookmarks.containsKey(username)) {
			return bookmarks.get(username);
		} else {
			return new HashSet<ICar>();
		}
	}

	@Override
	public void removeBookmark(String username, String registrationNumber) throws RegistrationNumberNotFoundException {
		Car searchCar = new Car();
		searchCar.setRegistrationNumber(registrationNumber);
		
		if (bookmarks.containsKey(username)) {
			if (!bookmarks.get(username).remove(searchCar)) {
				throw new RegistrationNumberNotFoundException();
			}
		}
	}

	@Override
	public void updateBookmarkedCars(Set<ICar> vacantCars) {
		for (String key : bookmarks.keySet()) {
			HashSet<ICar> hashSet = bookmarks.get(key);
			
			for (ICar iCar : hashSet) {
				iCar.setVacantState(vacantCars.contains(iCar));
			}
		}
	}

}
