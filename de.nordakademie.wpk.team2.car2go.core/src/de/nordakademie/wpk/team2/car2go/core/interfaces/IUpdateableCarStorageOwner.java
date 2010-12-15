package de.nordakademie.wpk.team2.car2go.core.interfaces;

import java.util.Set;

/**
 * Interface von an Object that contains a ICarStorage object that shall
 * be updated by an updater thread
 * 
 * @author Moehring, Rumrich
 * 
 */
public interface IUpdateableCarStorageOwner {
	/**
	 * Updates the carStorage of the owner with the provided Set<ICar>
	 * 
	 * @param carsFromApi
	 *            Set<ICar> with the cars to be updates
	 */
	public void pushCarStorageUdate(Set<ICar> carsFromApi);
}
