package de.nordakademie.wpk.team2.car2go.core;

import org.apache.log4j.Logger;

import de.nordakademie.wpk.team2.car2go.core.interfaces.ICarRegistrationNumberValidator;

/**
 * 
 * @author Rumrich, Moehring
 *
 */
public class GenericRegistrationNumberValidator implements
		ICarRegistrationNumberValidator {
	private static final Logger logger = Logger.getLogger(GenericRegistrationNumberValidator.class);
	
	@Override
	public Boolean validateRegistrationNumber(String registrationNumber) {
		logger.info("Validating registrationnumber " + registrationNumber);
		return  (registrationNumber != null) && !registrationNumber.isEmpty();
	}

}
