package de.nordakademie.wpk.team2.car2go.core.interfaces;

/**
 * Interfacte for a validator of car registration numbers
 * 
 * @author Rumrich, Moehring
 * 
 */
public interface ICarRegistrationNumberValidator {
	/**
	 * Validates a registration number
	 * 
	 * @param registrationNumber
	 *            String with the registration number to be validated.
	 * @return Boolean is true if the registrationNumber is valid
	 */
	public Boolean validateRegistrationNumber(String registrationNumber);
}
