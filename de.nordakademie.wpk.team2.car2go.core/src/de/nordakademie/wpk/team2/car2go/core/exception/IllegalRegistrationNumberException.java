package de.nordakademie.wpk.team2.car2go.core.exception;

/**
 * 
 * @author Rumrich
 *
 */
public class IllegalRegistrationNumberException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getLocalizedMessage() {
		return "Das Kennzeichen ist ungültig oder eine mehrfache Nutzung des Kennzeichens wurde durch eine ICarStorage festgestellt.";
		// return super.getLocalizedMessage();
	}

	@Override
	public String getMessage() {
		return "The registration number of this car is either not valid or a multiple usage of a registration number has been detected by an ICarStorage.";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
