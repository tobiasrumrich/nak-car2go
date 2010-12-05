package de.nordakademie.wpk.team2.car2go.core.exception;

public class IllegalCommentException extends Exception {
	private static final long serialVersionUID = 1L;

	@Override
	public String getLocalizedMessage() {
		return "Ungueltiger Kommentar uebergeben.";
	}

	@Override
	public String getMessage() {
		return "Illegal comment provided.";
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
