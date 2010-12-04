package de.nordakademie.wpk.team2.car2go.interfaces;

import java.util.Set;

import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;

public interface IBookmarkStorage {
	public void addBookmark(String username, ICar car);
	public Set<ICar> getBookmarks(String username);
	public void removeBookmark(String username, String registrationnumber) throws RegistrationNumberNotFoundException;
	public void updateBookmarkedCars(Set<ICar> vacantCars);
}
