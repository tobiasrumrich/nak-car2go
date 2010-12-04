package de.nordakademie.wpk.team2.car2go.core;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import de.nordakademie.wpk.team2.car2go.core.connectors.GoogleMapsLoader;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;
import de.nordakademie.wpk.team2.car2go.interfaces.IBookmarkStorage;
import de.nordakademie.wpk.team2.car2go.interfaces.ICar;
import de.nordakademie.wpk.team2.car2go.interfaces.ICarService;
import de.nordakademie.wpk.team2.car2go.interfaces.ICarStorage;
import de.nordakademie.wpk.team2.car2go.interfaces.IUpdateableCarStorageOwner;

/**
 * 
 * @author Rumrich, Moehring
 *
 */
public class CarService implements ICarService, IUpdateableCarStorageOwner {
	private static final Logger logger = Logger.getLogger(CarService.class);
	private ICarStorage carStorage = new CarStorage();
	private IBookmarkStorage bookmarkStorage = new BookmarkStorage();
	
	private GoogleMapsLoader googleMapsLoader = new GoogleMapsLoader();

	@Override
	public ICar getCarData(String registrationNumber)
			throws RegistrationNumberNotFoundException {
		logger.info("Reading infos for car with registrationnumber: " + registrationNumber);
		return carStorage.findCar(registrationNumber);
	}

	@Override
	public void addToBookmark(String registrationNumber, String username)
			throws RegistrationNumberNotFoundException {
		logger.info("Add car with registrationnumber " + registrationNumber + " to bookmarks of user " + username);
		
		bookmarkStorage.addBookmark(username, carStorage.findCar(registrationNumber));
	}

	@Override
	public void removeFromBookmark(String registrationNumber, String username)
			throws RegistrationNumberNotFoundException {
		logger.info("Remove car with registrationnumber " + registrationNumber + " from bookmarks of user " + username);

		bookmarkStorage.removeBookmark(username, registrationNumber);
	}

	@Override
	public Set<ICar> getVacantCars(String username) {
		logger.info("Get vacant cars for user " + username);
		
		Set<ICar> carSet = carStorage.getCarSet();
		Set<ICar> hashSet = bookmarkStorage.getBookmarks(username);
		Set<ICar> newHashSet = new HashSet<ICar>();

		for (ICar iCar : carSet) {
			if (!hashSet.contains(iCar)) {
				newHashSet.add(iCar);
			}
		}

		return newHashSet;
	}

	@Override
	public Set<ICar> getBookmarkedCars(String username) {
		logger.info("Get bookmarked cars for user " + username);
		
		return bookmarkStorage.getBookmarks(username);
	}

	@Override
	public void setComment(String registrationNumber, String comment)
			throws RegistrationNumberNotFoundException {
		logger.info("Set comment for car with registrationnumber " + registrationNumber);
		carStorage.findCar(registrationNumber).setDescription(comment);
	}

	@Override
	public byte[] getMapForCar(String registrationNumber, int width,
			int height, int zoom) throws RegistrationNumberNotFoundException {
		logger.info("Get map from google for car with registrationnumber " + registrationNumber);
		return googleMapsLoader.getMapForCar(
				carStorage.findCar(registrationNumber), width, height, zoom);
	}

	@Override
	public void pushCarStorageUdate(Set<ICar> newCarSet) {
		logger.info("New list of vacant cars available, updating carStorage...");
		Set<ICar> carSet = carStorage.getCarSet();
		carSet.retainAll(newCarSet);
		carSet.addAll(newCarSet);

		logger.info("Updating all bookmarks...");
		bookmarkStorage.updateBookmarkedCars(newCarSet);
		
		logger.info("Update finished.");
	}

	@Override
	public Set<ICar> getVacantCars() {
		logger.info("Getting all cars from storage.");
		return carStorage.getCarSet();
	}

}
