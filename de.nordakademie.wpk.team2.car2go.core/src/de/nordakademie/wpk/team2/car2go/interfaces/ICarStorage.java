package de.nordakademie.wpk.team2.car2go.interfaces;

import java.util.Set;

import de.nordakademie.wpk.team2.car2go.core.exception.IllegalRegistrationNumberException;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;

/**
 * 
 * @author Rumrich, Moehring
 *
 */
public interface ICarStorage {
	/**
	 * Adds an ICar object to the storage.
	 * 
	 * @param car
	 */
	public void addCar(ICar car) throws IllegalRegistrationNumberException;

	/**
	 * Return the stored ICar objects as a Set<ICar>
	 * 
	 * @return Set<ICar>
	 */
	public Set<ICar> getCarSet();
	

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
	
	/**
	 * Finds a car by it's registration number
	 * @param registrationNumber
	 * @return
	 * @throws RegistrationNumberNotFoundException 
	 * @throws IllegalRegistrationNumberException 
	 */
	public ICar findCar(String registrationNumber) throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException;

}
