package de.nordakademie.wpk.team2.car2go.core.interfaces;

/**
 * Kennzeichenvalidator der die formale G�ltigkeit eines Kennzeichens �berpr�ft.
 * @author Rumrich, Moehring
 *
 */
public interface ICarRegistrationNumberValidator {
	/**
	 * Validiert das �bergebene
	 * @param registrationNumber - ist das zu validierende Kennzeichen als String.
	 * @return Boolean - liefert true wenn es sich um ein g�ltiges Kennzeichen handelt.
	 */
	public Boolean validateRegistrationNumber(String registrationNumber);
}
