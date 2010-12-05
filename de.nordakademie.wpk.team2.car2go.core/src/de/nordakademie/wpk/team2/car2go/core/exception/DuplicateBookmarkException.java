package de.nordakademie.wpk.team2.car2go.core.exception;

public class DuplicateBookmarkException extends Exception {
	private static final long serialVersionUID = 1L;

	@Override
	public String getLocalizedMessage() {
		return "Dieses Auto wurde vom Benutzer schon gebookmarked.";
	}

	@Override
	public String getMessage() {
		return "The car is already bookmarked by this user.";
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
