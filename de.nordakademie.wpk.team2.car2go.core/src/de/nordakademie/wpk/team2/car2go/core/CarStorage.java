package de.nordakademie.wpk.team2.car2go.core;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import de.nordakademie.wpk.team2.car2go.core.exception.IllegalRegistrationNumberException;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICar;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICarRegistrationNumberValidator;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICarStorage;

/**
 * This class stores all vacant cars
 * 
 * @author Rumrich, Moehring
 * 
 */
public class CarStorage implements ICarStorage {
	private static final Logger logger = Logger.getLogger(CarStorage.class);
	
	private Set<ICar> carStorage = new HashSet<ICar>();
	private ICarRegistrationNumberValidator registrationNumberValidator = new GenericRegistrationNumberValidator();

	@Override
	public void addCar(ICar car) throws IllegalRegistrationNumberException {
		logger.info("Add car " + car);
		
		if (car == null || !registrationNumberValidator.validateRegistrationNumber(
				car.getRegistrationNumber()) || !carStorage.add(car)) {
			logger.info("Illegal registrationnumber!");
			throw new IllegalRegistrationNumberException();
		}
	}

	@Override
	public Set<ICar> getCarSet() {
		return carStorage;
	}

	@Override
	public void removeCar(ICar car) throws IllegalRegistrationNumberException,
			RegistrationNumberNotFoundException {
		logger.info("Remove car " + car + " from storage");
		
		if (!registrationNumberValidator.validateRegistrationNumber(car
				.getRegistrationNumber())) {
			logger.info("Illegal registrationnumber!");
			throw new IllegalRegistrationNumberException();
		} else if (!carStorage.remove(car)) {
			logger.info("No car with this registrationnumber found in storage!");
			throw new RegistrationNumberNotFoundException();
		}

	}

	@Override
	public void removeCar(String registrationNumber)
			throws RegistrationNumberNotFoundException,
			IllegalRegistrationNumberException,
			RegistrationNumberNotFoundException {
		carStorage.remove(this.findCar(registrationNumber));
	}

	@Override
	public ICar findCar(String registrationNumber)
			throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException {
		logger.info("Search for car with registrationnumber " + registrationNumber);
		
		if (!registrationNumberValidator.validateRegistrationNumber(registrationNumber)) {
			logger.info("Illegal registrationnumber!");
			throw new IllegalRegistrationNumberException();
		}
		
		for (ICar myCar : carStorage) {
			if (myCar.getRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
				logger.info("Car found: " + myCar);
				return myCar;
			}
		}
		
		logger.info("Car not found!");
		throw new RegistrationNumberNotFoundException();
	}

}
