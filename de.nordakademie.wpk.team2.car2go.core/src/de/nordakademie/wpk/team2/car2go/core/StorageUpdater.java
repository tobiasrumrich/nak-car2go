package de.nordakademie.wpk.team2.car2go.core;

import java.util.Set;

import de.nordakademie.wpk.team2.car2go.core.xmldata.CarLoader;
import de.nordakademie.wpk.team2.car2go.interfaces.ICar;
import de.nordakademie.wpk.team2.car2go.interfaces.IUpdateableCarStorageOwner;

public class StorageUpdater extends Thread {

	private IUpdateableCarStorageOwner carService;
	private int updateInterval;

	/**
	 * 
	 * @param carService
	 *            is the CarService that will be pushed with the updates. If it
	 *            is set to 0, there will be no periodic updates.
	 * @param updateInterval
	 *            is the interval in seconds between the updates and must be a
	 *            postive int
	 */
	public StorageUpdater(IUpdateableCarStorageOwner carService,
			int updateInterval) {
		if (updateInterval < 0)
			throw new IllegalArgumentException(
					"Update interval must be a positive int.");
		if (carService == null)
			throw new IllegalArgumentException(
					"This could have resulted in a null pointer exception: The car service must not be null.");
		this.carService = carService;
		this.updateInterval = updateInterval;
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

			if (updateInterval > 0) {
				try {
					Thread.sleep(1000 * 60 * updateInterval);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				interrupt();
			}

		}
	}
}
