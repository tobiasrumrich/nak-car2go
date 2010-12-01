package de.nordakademie.wpk.team2.car2go.core;

import de.nordakademie.wpk.team2.car2go.interfaces.ICarRegistrationNumberValidator;

public class GenericRegistrationNumberValidator implements
		ICarRegistrationNumberValidator {

	@Override
	public Boolean validateRegistrationNumber(String registrationNumber) {
		return  (registrationNumber != null) && !registrationNumber.isEmpty();
	}

}
