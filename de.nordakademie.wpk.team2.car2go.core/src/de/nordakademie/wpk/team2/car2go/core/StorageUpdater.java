package de.nordakademie.wpk.team2.car2go.core;

import java.util.Set;

import org.apache.log4j.Logger;

import de.nordakademie.wpk.team2.car2go.core.xmldata.CarLoader;
import de.nordakademie.wpk.team2.car2go.interfaces.ICar;
import de.nordakademie.wpk.team2.car2go.interfaces.IUpdateableCarStorageOwner;

/**
 * 
 * @author Rumrich, Moehring
 *
 */
public class StorageUpdater extends Thread {
	private static final Logger logger = Logger.getLogger(CarStorage.class);
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
		logger.info("Initializing Updateservice for storage.");

		if (updateInterval < 0) {
			logger.error("Error: Update interval must be a positive int.");
			throw new IllegalArgumentException(
					"Update interval must be a positive int.");
		}

		if (carService == null) {
			logger.error("Error: no CarService specified!");
			throw new IllegalArgumentException(
					"This could have resulted in a null pointer exception: The car service must not be null.");
		}

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
			logger.info("Starting updatesequence...");
			CarLoader carLoader = new CarLoader();

			Set<ICar> carsFromApi = carLoader.getCarsFromApi();

			carService.pushCarStorageUdate(carsFromApi);

			if (updateInterval > 0) {
				try {
					logger.info("Falling into sleep...");
					Thread.sleep(1000 * 60 * updateInterval);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				interrupt();
			}
		}
	}
}
