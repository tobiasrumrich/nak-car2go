package de.nordakademie.wpk.team2.car2go.core;

import org.apache.log4j.Logger;

import de.nordakademie.wpk.team2.car2go.core.interfaces.ICarRegistrationNumberValidator;

/**
 * The GenericRegistrationNumberValidator is the implementation of an
 * ICarRegistrationNumberValidator and validates registration numbers for not
 * being empty or being null
 * 
 * @author Moehring, Rumrich
 * 
 */
public class GenericRegistrationNumberValidator implements
		ICarRegistrationNumberValidator {
	private static final Logger logger = Logger
			.getLogger(GenericRegistrationNumberValidator.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.nordakademie.wpk.team2.car2go.core.interfaces.
	 * ICarRegistrationNumberValidator
	 * #validateRegistrationNumber(java.lang.String)
	 */
	@Override
	public Boolean validateRegistrationNumber(String registrationNumber) {
		logger.info("Validating registrationnumber " + registrationNumber);
		return (registrationNumber != null) && !registrationNumber.isEmpty();
	}

}
