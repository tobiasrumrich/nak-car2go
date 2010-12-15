package de.nordakademie.wpk.team2.car2go.core.interfaces;

/**
 * Kennzeichenvalidator der die formale Gültigkeit eines Kennzeichens überprüft.
 * @author Rumrich, Moehring
 *
 */
public interface ICarRegistrationNumberValidator {
	/**
	 * Validiert das übergebene
	 * @param registrationNumber - ist das zu validierende Kennzeichen als String.
	 * @return Boolean - liefert true wenn es sich um ein gültiges Kennzeichen handelt.
	 */
	public Boolean validateRegistrationNumber(String registrationNumber);
}
