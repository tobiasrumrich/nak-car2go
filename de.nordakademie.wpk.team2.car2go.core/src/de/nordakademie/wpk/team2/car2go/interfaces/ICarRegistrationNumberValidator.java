package de.nordakademie.wpk.team2.car2go.interfaces;

public interface ICarRegistrationNumberValidator {
	/**
	 * Valdiates the registration number
	 * @param registrationNumber
	 * @return Boolean
	 */
	public Boolean validateRegistrationNumber(String registrationNumber);
}