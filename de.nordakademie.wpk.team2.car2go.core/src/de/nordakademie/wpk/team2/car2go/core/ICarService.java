package de.nordakademie.wpk.team2.car2go.core;

import java.util.List;

import de.nordakademie.wpk.team2.car2go.core.businessobjects.ICar;


public interface ICarService {
	/**
	 * 
	 * @return List<ICar>
	 */
	public List<ICar> getVacantCars();
	public List<ICar> getBookmarkedCars();
	public ICar getCarData(String registrationNumber);
	public void addToBookmark(String registrationNumber, String username);
	public void removeFromBookmark(String registrationNumber, String username);
	public void addComment(String comment, String username);
	
	/**
	 * Utilizes Google Maps and returns a map of the cars position
	 * @param registrationNumber
	 * @return byte[] cont
	 */
	public byte[] getMapForCar(String registrationNumber);
}
