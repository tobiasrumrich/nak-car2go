package de.nordakademie.wpk.team2.car2go.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import de.nordakademie.wpk.team2.car2go.core.businessobjects.ICar;
import de.nordakademie.wpk.team2.car2go.core.connectors.GoogleMapsLoader;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;

public class CarService implements ICarService {
	private static final Logger logger = Logger.getLogger(CarService.class);
	private ICarStorage carStorage = new CarStorage();

	private HashMap<String, HashSet<ICar>> bookmarks = new HashMap<String, HashSet<ICar>>();
	private GoogleMapsLoader googleMapsLoader = new GoogleMapsLoader();

	@Override
	public ICar getCarData(String registrationNumber) throws RegistrationNumberNotFoundException {
		return carStorage.findCar(registrationNumber);
	}

	@Override
	public void addToBookmark(String registrationNumber, String username)
			throws RegistrationNumberNotFoundException {

		ICar findCar = carStorage.findCar(registrationNumber);
		HashSet<ICar> carHashSet;

		if (bookmarks.containsKey(username)) {
			carHashSet = bookmarks.get(username);
		} else {
			carHashSet = new HashSet<ICar>();
		}
		carHashSet.add(findCar);
		bookmarks.put(username, carHashSet);

	}

	@Override
	public void removeFromBookmark(String registrationNumber, String username)
			throws RegistrationNumberNotFoundException {
		ICar findCar = carStorage.findCar(registrationNumber);
		HashSet<ICar> carHashSet;

		if (bookmarks.containsKey(username)) {
			carHashSet = bookmarks.get(username);
		} else {
			carHashSet = new HashSet<ICar>();
		}
		carHashSet.remove(findCar);
		bookmarks.put(username, carHashSet);

	}

	@Override
	public Set<ICar> getVacantCars(String username) {

		Set<ICar> carSet = carStorage.getCarSet();
		HashSet<ICar> hashSet = bookmarks.get(username);
		HashSet<ICar> newHashSet = new HashSet<ICar>();

		for (ICar iCar : carSet) {
			if (!hashSet.contains(iCar)) {
				newHashSet.add(iCar);
			}
		}

		return newHashSet;
	}

	@Override
	public Set<ICar> getBookmarkedCars(String username) {
		if (bookmarks.containsKey(username)) {
			return bookmarks.get(username);
		}
		else {
			return new HashSet<ICar>();
		}
	}

	@Override
	public void setComment(String registrationNumber, String comment) throws RegistrationNumberNotFoundException {
		carStorage.findCar(registrationNumber).setDescription(comment);
		
	}

	@Override
	public byte[] getMapForCar(String registrationNumber,int width, int height, int zoom) throws RegistrationNumberNotFoundException {
		return googleMapsLoader.getMapForCar(carStorage.findCar(registrationNumber), width, height, zoom);
		
	}

}
