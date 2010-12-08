package de.nordakademie.wpk.team2.car2go.core.interfaces;

import java.util.Set;

import de.nordakademie.wpk.team2.car2go.core.exception.DuplicateBookmarkException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalCommentException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalRegistrationNumberException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalUsernameException;
import de.nordakademie.wpk.team2.car2go.core.exception.MapRetrievalException;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;
import de.nordakademie.wpk.team2.car2go.core.exception.UsernameNotFoundException;

/**
 * 
 * @author Rumrich, Moehring
 *
 */
public interface ICarService {
	/**
	 * 
	 * @return List<ICar>
	 * @throws IllegalUsernameException 
	 */
	public Set<ICar> getVacantCars(String username) throws IllegalUsernameException;

	public Set<ICar> getVacantCars();
	
	public Set<ICar> getBookmarkedCars(String username) throws IllegalUsernameException;

	public ICar getCarData(String registrationNumber) throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException;

	public void addToBookmark(String registrationNumber, String username)
			throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException, DuplicateBookmarkException, IllegalUsernameException;

	public void removeFromBookmark(String registrationNumber, String username) throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException, UsernameNotFoundException, IllegalUsernameException;

	public void setComment(String registrationNumber, String comment)
			throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException, IllegalCommentException;

	/**
	 * Utilizes Google Maps and returns a map of the cars position
	 * @param registrationNumber is the registration number of the car
	 * @param width is the with of the requested image
	 * @param height is the height of the requested image
	 * @param zoom is the zoom factor of the requested image
	 * @return byte[] contains the requested image
	 * @throws RegistrationNumberNotFoundException 
	 * @throws IllegalRegistrationNumberException 
	 * @throws MapRetrievalException 
	 */
	public byte[] getMapForCar(String registrationNumber, int width,
			int height, int zoom) throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException, MapRetrievalException;

}
