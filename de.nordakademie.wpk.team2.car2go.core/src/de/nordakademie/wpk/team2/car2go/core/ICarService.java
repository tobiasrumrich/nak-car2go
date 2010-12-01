package de.nordakademie.wpk.team2.car2go.core;

import java.util.Set;

import de.nordakademie.wpk.team2.car2go.core.businessobjects.ICar;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;

public interface ICarService {
	/**
	 * 
	 * @return List<ICar>
	 */
	public Set<ICar> getVacantCars(String username);

	public Set<ICar> getBookmarkedCars(String username);

	public ICar getCarData(String registrationNumber) throws RegistrationNumberNotFoundException;

	public void addToBookmark(String registrationNumber, String username)
			throws RegistrationNumberNotFoundException;

	public void removeFromBookmark(String registrationNumber, String username) throws RegistrationNumberNotFoundException;

	public void setComment(String registrationNumber, String comment)
			throws RegistrationNumberNotFoundException;

	/**
	 * Utilizes Google Maps and returns a map of the cars position
	 * @param registrationNumber is the registration number of the car
	 * @param width is the with of the requested image
	 * @param height is the height of the requested image
	 * @param zoom is the zoom factor of the requested image
	 * @return byte[] contains the requested image
	 * @throws RegistrationNumberNotFoundException 
	 */
	public byte[] getMapForCar(String registrationNumber, int width,
			int height, int zoom) throws RegistrationNumberNotFoundException;

}
