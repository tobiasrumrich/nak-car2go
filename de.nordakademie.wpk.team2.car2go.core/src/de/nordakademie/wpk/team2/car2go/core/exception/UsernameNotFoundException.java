package de.nordakademie.wpk.team2.car2go.core.exception;

public class UsernameNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	@Override
	public String getLocalizedMessage() {
		return "Der angegebene Benutzer konnte nicht gefunden werden.";
	}

	@Override
	public String getMessage() {
		return "The specified user could not be found.";
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
