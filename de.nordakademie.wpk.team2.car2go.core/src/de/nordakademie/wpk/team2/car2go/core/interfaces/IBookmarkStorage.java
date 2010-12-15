package de.nordakademie.wpk.team2.car2go.core.interfaces;

import java.util.Set;

import de.nordakademie.wpk.team2.car2go.core.exception.DuplicateBookmarkException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalRegistrationNumberException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalUsernameException;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;
import de.nordakademie.wpk.team2.car2go.core.exception.UsernameNotFoundException;

/**
 * Stores the ICar objects that have been bookmarked by a user
 * 
 * @author Moehring, Rumrich
 * 
 */
public interface IBookmarkStorage {
	/**
	 * Adds a bookmark for a user and a car
	 * 
	 * @param username
	 *            - String with the username
	 * @param car
	 *            - ICar object that shall be bookmarked
	 * @throws DuplicateBookmarkException
	 *             is thrown if the ICar object is already bookmarked for the
	 *             user
	 * @throws IllegalUsernameException
	 *             is thrown if the username is illegal (e.g. empty or null)
	 * @throws IllegalRegistrationNumberException
	 *             is thrown if the registration number of the car is illegal
	 */
	public void addBookmark(String username, ICar car)
			throws DuplicateBookmarkException, IllegalUsernameException,
			IllegalRegistrationNumberException;

	/**
	 * Returns a Set<Icar> that contains all ICar objects bookmarked by the user
	 * 
	 * @param username
	 *            String with the username that shall be queried
	 * @return Set<ICar> with all bookmarked Icars for this user
	 * @throws IllegalUsernameException
	 *             is thrown if the username is illegal (e.g. empty or null)
	 */
	public Set<ICar> getBookmarks(String username)
			throws IllegalUsernameException;

	/**
	 * Removes a bookmark for the user an the ICar identified by the
	 * registration number
	 * 
	 * @param username
	 *            - String with the username whose bookmark shall be removed
	 * @param registrationnumber
	 *            - String with the registration number of the car
	 * @throws RegistrationNumberNotFoundException
	 *             is thrown if no car is found under the registration number
	 *             provided
	 * @throws IllegalRegistrationNumberException
	 *             is thrown if the registration number of the car is illegal
	 * @throws UsernameNotFoundException
	 *             is thrown if the username provided is unkown by the storage
	 * @throws IllegalUsernameException
	 *             is thrown if the username is illegal (e.g. empty or null)
	 */
	public void removeBookmark(String username, String registrationnumber)
			throws RegistrationNumberNotFoundException,
			IllegalRegistrationNumberException, UsernameNotFoundException,
			IllegalUsernameException;

	/**
	 * Updates the stored ICars and sets the vacant state of a car to false if
	 * the car is no longer vacant
	 * 
	 * @param vacantCars
	 *            - Set<ICar> with the cars to be updated. If a car stored in
	 *            the storage is not on that Set<ICar> its vacant state is set
	 *            to false
	 */
	public void updateBookmarkedCars(Set<ICar> vacantCars);
}
