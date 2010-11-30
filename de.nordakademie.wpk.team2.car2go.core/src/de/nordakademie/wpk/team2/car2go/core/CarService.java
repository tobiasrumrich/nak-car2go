package de.nordakademie.wpk.team2.car2go.core;

import java.util.List;

import org.apache.log4j.Logger;

import de.nordakademie.wpk.team2.car2go.core.businessobjects.ICar;

public class CarService implements ICarService {
	private static final Logger logger = Logger.getLogger(CarService.class);

	@Override
	public List<ICar> getVacantCars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ICar> getBookmarkedCars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICar getCarData(String registrationNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addToBookmark(String registrationNumber, String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFromBookmark(String registrationNumber, String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComment(String comment, String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] getMapForCar(String registrationNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
