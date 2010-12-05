package de.nordakademie.wpk.team2.car2go.interfaces;

import java.util.Set;

import de.nordakademie.wpk.team2.car2go.core.exception.DuplicateBookmarkException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalRegistrationNumberException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalUsernameException;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;
import de.nordakademie.wpk.team2.car2go.core.exception.UsernameNotFoundException;

public interface IBookmarkStorage {
	public void addBookmark(String username, ICar car) throws DuplicateBookmarkException, IllegalUsernameException, IllegalRegistrationNumberException;
	public Set<ICar> getBookmarks(String username) throws IllegalUsernameException;
	public void removeBookmark(String username, String registrationnumber) throws RegistrationNumberNotFoundException, IllegalRegistrationNumberException, UsernameNotFoundException, IllegalUsernameException;
	public void updateBookmarkedCars(Set<ICar> vacantCars);
}
