package de.nordakademie.wpk.team2.car2go.core.exception;

public class IllegalUsernameException extends Exception {
	private static final long serialVersionUID = 1L;

	@Override
	public String getLocalizedMessage() {
		return "Benutzername ist ungueltig.";
	}

	@Override
	public String getMessage() {
		return "Username is invalid.";
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
