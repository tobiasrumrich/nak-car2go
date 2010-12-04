package de.nordakademie.wpk.team2.car2go.core.exception;

/**
 * 
 * @author Rumrich
 *
 */
public class RegistrationNumberNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getLocalizedMessage() {
		return "Ein Fahrzeug mit diesem Kennzeichen konnte nicht gefunden werden.";
		// return super.getLocalizedMessage();
	}

	@Override
	public String getMessage() {
		return "A car with this registration number could not be found.";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
