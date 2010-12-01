package de.nordakademie.wpk.team2.car2go.core;

public class GenericRegistrationNumberValidator implements
		ICarRegistrationNumberValidator {

	@Override
	public Boolean validateRegistrationNumber(String registrationNumber) {
		return  (registrationNumber != null) && !registrationNumber.isEmpty();
	}

}
