package de.nordakademie.wpk.team2.car2go.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import de.nordakademie.wpk.team2.car2go.core.exception.IllegalRegistrationNumberException;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;
import de.nordakademie.wpk.team2.car2go.interfaces.ICar;
import de.nordakademie.wpk.team2.car2go.interfaces.ICarRegistrationNumberValidator;
import de.nordakademie.wpk.team2.car2go.interfaces.ICarStorage;

/**
 * This class stores
 * 
 * @author tobias, nico
 * 
 */
public class CarStorage implements ICarStorage {

	private Set<ICar> carStorage = new HashSet<ICar>();
	private ICarRegistrationNumberValidator registrationNumberValidator = new GenericRegistrationNumberValidator();

	@Override
	public void addCar(ICar car) throws IllegalRegistrationNumberException {
		if (!registrationNumberValidator.validateRegistrationNumber(car
				.getRegistrationNumber()) || !carStorage.add(car)) {
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
		if (!registrationNumberValidator.validateRegistrationNumber(car
				.getRegistrationNumber())) {
			throw new IllegalRegistrationNumberException();
		} else if (!carStorage.remove(car)) {
			throw new RegistrationNumberNotFoundException();
		}

	}

	@Override
	public void removeCar(String registrationNumber)
			throws RegistrationNumberNotFoundException,
			IllegalRegistrationNumberException,
			RegistrationNumberNotFoundException {

		if (!registrationNumberValidator
				.validateRegistrationNumber(registrationNumber)) {
			throw new IllegalRegistrationNumberException();
		} else if (!carStorage.remove(carStorage)) {
			throw new RegistrationNumberNotFoundException();
		}

	}

	@Override
	public ICar findCar(String registrationNumber)
			throws RegistrationNumberNotFoundException {
		for (ICar myCar : carStorage) {
			if (myCar.getRegistrationNumber().equals(registrationNumber))
				return myCar;
		}
		throw new RegistrationNumberNotFoundException();
	}

}
