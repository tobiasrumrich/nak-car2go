package de.nordakademie.wpk.team2.car2go.core;

import java.util.Set;

import de.nordakademie.wpk.team2.car2go.core.xmldata.CarLoader;
import de.nordakademie.wpk.team2.car2go.interfaces.ICar;
import de.nordakademie.wpk.team2.car2go.interfaces.IUpdateableCarStorageOwner;

public class StorageUpdater extends Thread {

	private IUpdateableCarStorageOwner carService;

	public StorageUpdater(IUpdateableCarStorageOwner carService) {
		this.carService = carService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (true) {
			
			CarLoader carLoader = new CarLoader();
			
			Set<ICar> carsFromApi = carLoader.getCarsFromApi();
			
			carService.pushCarStorageUdate(carsFromApi);
			
			try {
				Thread.sleep(1000 * 60* 5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
	}

}
