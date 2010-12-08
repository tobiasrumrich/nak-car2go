package de.nordakademie.wpk.team2.car2go.core.interfaces;

import java.util.Set;

/**
 * 
 * @author Rumrich, Moehring
 *
 */
public interface IUpdateableCarStorageOwner {
	public void pushCarStorageUdate(Set<ICar> carsFromApi);
}
