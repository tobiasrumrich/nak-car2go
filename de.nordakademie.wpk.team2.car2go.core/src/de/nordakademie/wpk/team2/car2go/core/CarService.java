package de.nordakademie.wpk.team2.car2go.core;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import de.nordakademie.wpk.team2.car2go.core.connectors.GoogleMapsLoader;
import de.nordakademie.wpk.team2.car2go.core.exception.DuplicateBookmarkException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalCommentException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalRegistrationNumberException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalUsernameException;
import de.nordakademie.wpk.team2.car2go.core.exception.MapRetrievalException;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;
import de.nordakademie.wpk.team2.car2go.core.exception.UsernameNotFoundException;
import de.nordakademie.wpk.team2.car2go.core.interfaces.IBookmarkStorage;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICar;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICarService;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICarStorage;
import de.nordakademie.wpk.team2.car2go.core.interfaces.IUpdateableCarStorageOwner;

/**
 * Is an implementation of ICarService. The CarService is made available to the
 * RCP client by the application server.
 * 
 * @author Moehring, Rumrich
 * 
 */
public class CarService implements ICarService, IUpdateableCarStorageOwner {
	private static final Logger logger = Logger.getLogger(CarService.class);
	private ICarStorage carStorage = new CarStorage();
	private IBookmarkStorage bookmarkStorage = new BookmarkStorage();
	private StorageUpdater updater;

	private GoogleMapsLoader googleMapsLoader = new GoogleMapsLoader();

	public CarService() {
		updater = new StorageUpdater(this, 1);
		updater.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.nordakademie.wpk.team2.car2go.core.interfaces.ICarService#getCarData
	 * (java.lang.String)
	 */
	@Override
	public ICar getCarData(String registrationNumber)
			throws RegistrationNumberNotFoundException,
			IllegalRegistrationNumberException {
		logger.info("Reading infos for car with registrationnumber: "
				+ registrationNumber);
		return carStorage.findCar(registrationNumber);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.nordakademie.wpk.team2.car2go.core.interfaces.ICarService#addToBookmark
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public void addToBookmark(String registrationNumber, String username)
			throws RegistrationNumberNotFoundException,
			IllegalRegistrationNumberException, DuplicateBookmarkException,
			IllegalUsernameException {
		logger.info("Add car with registrationnumber " + registrationNumber
				+ " to bookmarks of user " + username);

		bookmarkStorage.addBookmark(username,
				carStorage.findCar(registrationNumber));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.nordakademie.wpk.team2.car2go.core.interfaces.ICarService#
	 * removeFromBookmark(java.lang.String, java.lang.String)
	 */
	@Override
	public void removeFromBookmark(String registrationNumber, String username)
			throws RegistrationNumberNotFoundException,
			IllegalRegistrationNumberException, UsernameNotFoundException,
			IllegalUsernameException {
		logger.info("Remove car with registrationnumber " + registrationNumber
				+ " from bookmarks of user " + username);

		bookmarkStorage.removeBookmark(username, registrationNumber);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.nordakademie.wpk.team2.car2go.core.interfaces.ICarService#getVacantCars
	 * (java.lang.String)
	 */
	@Override
	public Set<ICar> getVacantCars(String username)
			throws IllegalUsernameException {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.nordakademie.wpk.team2.car2go.core.interfaces.ICarService#
	 * getBookmarkedCars(java.lang.String)
	 */
	@Override
	public Set<ICar> getBookmarkedCars(String username)
			throws IllegalUsernameException {
		logger.info("Get bookmarked cars for user " + username);

		return bookmarkStorage.getBookmarks(username);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.nordakademie.wpk.team2.car2go.core.interfaces.ICarService#setComment
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public void setComment(String registrationNumber, String comment)
			throws RegistrationNumberNotFoundException,
			IllegalRegistrationNumberException, IllegalCommentException {
		logger.info("Set comment for car with registrationnumber "
				+ registrationNumber);

		if (comment == null) {
			throw new IllegalCommentException();
		}

		carStorage.findCar(registrationNumber).setComment(comment);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.nordakademie.wpk.team2.car2go.core.interfaces.ICarService#getMapForCar
	 * (java.lang.String, int, int, int)
	 */
	@Override
	public byte[] getMapForCar(String registrationNumber, int width,
			int height, int zoom) throws RegistrationNumberNotFoundException,
			IllegalRegistrationNumberException, MapRetrievalException {
		logger.info("Get map from google for car with registrationnumber "
				+ registrationNumber);
		return googleMapsLoader.getMapForCar(
				carStorage.findCar(registrationNumber), width, height, zoom);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.nordakademie.wpk.team2.car2go.core.interfaces.IUpdateableCarStorageOwner
	 * #pushCarStorageUdate(java.util.Set)
	 */
	@Override
	public void pushCarStorageUdate(Set<ICar> newCarSet) {
		logger.info("New list of vacant cars available, updating carStorage...");
		logger.info("New car count: " + newCarSet.size());
		Set<ICar> carSet = carStorage.getCarSet();
		carSet.retainAll(newCarSet);
		carSet.addAll(newCarSet);

		logger.info("Updating all bookmarks...");
		bookmarkStorage.updateBookmarkedCars(newCarSet);

		logger.info("Update finished.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.nordakademie.wpk.team2.car2go.core.interfaces.ICarService#getVacantCars
	 * ()
	 */
	@Override
	public Set<ICar> getVacantCars() {
		logger.info("Getting all cars from storage.");
		return carStorage.getCarSet();
	}

}
