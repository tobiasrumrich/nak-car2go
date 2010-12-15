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
 * Interface for the car service that is made available to the gui by the server
 * 
 * @author Rumrich, Moehring
 * 
 */
public interface ICarService {
	/**
	 * Returns a Set<ICar> of all vacant cars for a user
	 * 
	 * @return List<ICar> of all vacant cars for the user
	 * @throws IllegalUsernameException
	 *             is thrown if the username is illegal (e.g. empty or null)
	 */
	public Set<ICar> getVacantCars(String username)
			throws IllegalUsernameException;

	/**
	 * Returns a Set<Icar> of all vacant cars
	 * 
	 * @return Set<ICar> with all vacant cars
	 */
	public Set<ICar> getVacantCars();

	/**
	 * Returns a Set<Icar> of all ICars that have been bookmarked by a specific
	 * user
	 * 
	 * @param username
	 *            String with the username that shall be queried
	 * @return Set<Icar> of all ICars that have been bookmarked by a specific
	 *         user
	 * @throws IllegalUsernameException
	 *             is thrown if the username is illegal (e.g. empty or null)
	 */
	public Set<ICar> getBookmarkedCars(String username)
			throws IllegalUsernameException;

	/**
	 * Returns the ICar object for the car identified by the registration number
	 * 
	 * @param registrationNumber
	 *            String with the registration number of the ICar that shall be
	 *            queried
	 * @return ICar object with the registration number queried
	 * @throws RegistrationNumberNotFoundException
	 *             is thrown if the registration number is unknown by the
	 *             service
	 * @throws IllegalRegistrationNumberException
	 *             is thrown if the registration number of the car is illegal
	 */
	public ICar getCarData(String registrationNumber)
			throws RegistrationNumberNotFoundException,
			IllegalRegistrationNumberException;

	/**
	 * Bookmarks a car identified by its registration number for a user
	 * 
	 * @param registrationNumber
	 *            String with the registration number of the car that shall be
	 *            bookmarked
	 * @param username
	 *            String with the username to whom the car shall be bookmarked
	 *            for
	 * @throws RegistrationNumberNotFoundException
	 *             is thrown if the registration number is unknown by the
	 *             service
	 * @throws IllegalRegistrationNumberException
	 *             is thrown if the registration number is illegal
	 * @throws DuplicateBookmarkException
	 *             is thrown if the car is already bookmarked by the user
	 * @throws IllegalUsernameException
	 *             is thrown if the username is illegal (e.g. empty or null)
	 */
	public void addToBookmark(String registrationNumber, String username)
			throws RegistrationNumberNotFoundException,
			IllegalRegistrationNumberException, DuplicateBookmarkException,
			IllegalUsernameException;

	/**
	 * Removes the bookmark from a car
	 * 
	 * @param registrationNumber
	 *            String with the registration number of the car
	 * @param username
	 *            String with the username of the user who had bookmarked the
	 *            car
	 * @throws RegistrationNumberNotFoundException
	 *             is thrown if the registration number is unknown by the
	 *             service
	 * @throws IllegalRegistrationNumberException
	 *             is thrown if the registration number is illegal
	 * @throws UsernameNotFoundException
	 *             is thrown if the username is unknown by the service
	 * @throws IllegalUsernameException
	 *             is thrown if the username is illegal (e.g. empty or null)
	 */
	public void removeFromBookmark(String registrationNumber, String username)
			throws RegistrationNumberNotFoundException,
			IllegalRegistrationNumberException, UsernameNotFoundException,
			IllegalUsernameException;

	/**
	 * Sets the comment attribute of a car
	 * 
	 * @param registrationNumber
	 *            String with the registration of the car
	 * @param comment
	 *            String with the comment
	 * @throws RegistrationNumberNotFoundException
	 *             is thrown if the registration number is unknown by the
	 *             service
	 * @throws IllegalRegistrationNumberException
	 *             is thrown if the registration number is illegal
	 * @throws IllegalCommentException
	 *             is thrown if the comment is illegal (e.g. null)
	 */
	public void setComment(String registrationNumber, String comment)
			throws RegistrationNumberNotFoundException,
			IllegalRegistrationNumberException, IllegalCommentException;

	/**
	 * Utilizes Google Maps and returns a map of the cars position
	 * 
	 * @param registrationNumber
	 *            is the registration number of the car
	 * @param width
	 *            is the with of the requested image
	 * @param height
	 *            is the height of the requested image
	 * @param zoom
	 *            is the zoom factor of the requested image
	 * @return byte[] contains the requested image
	 * @throws RegistrationNumberNotFoundException
	 *             is thrown if the registration number is unknown by the
	 *             service
	 * @throws IllegalRegistrationNumberException
	 *             is thrown if the registration number is illegal
	 * @throws MapRetrievalException
	 *             is thrown if the service was unable to retrive the map from
	 *             Google
	 */
	public byte[] getMapForCar(String registrationNumber, int width,
			int height, int zoom) throws RegistrationNumberNotFoundException,
			IllegalRegistrationNumberException, MapRetrievalException;

}
