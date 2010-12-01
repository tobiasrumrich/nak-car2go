package de.nordakademie.wpk.team2.car2go.core;

import java.util.List;


import de.nordakademie.wpk.team2.car2go.core.businessobjects.ICar;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalRegistrationNumberException;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;

public interface ICarStorage {
	/**
	 * Adds an ICar object to the storage.
	 * 
	 * @param car
	 */
	public void addCar(ICar car) throws IllegalRegistrationNumberException;

	/**
	 * Return the stored ICar objects as a List<ICar>
	 * 
	 * @return List<ICar>
	 */
	public List<ICar> getCarList();

	/**
	 * Removes a car from the storage.
	 * 
	 * @param car
	 *            is a ICar object that equals to the car you would like to
	 *            remove
	 */
	public void removeCar(ICar car) throws IllegalRegistrationNumberException, RegistrationNumberNotFoundException;

	/**
	 * Removes a car from the storage
	 * 
	 * @param registration_number
	 *            of the car
	 */
	public void removeCar(String registration_number)
			throws RegistrationNumberNotFoundException,
			IllegalRegistrationNumberException, RegistrationNumberNotFoundException;

}