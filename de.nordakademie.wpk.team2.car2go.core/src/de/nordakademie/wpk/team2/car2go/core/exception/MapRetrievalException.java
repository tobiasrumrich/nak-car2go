package de.nordakademie.wpk.team2.car2go.core.exception;

public class MapRetrievalException extends Exception {
	private static final long serialVersionUID = 1L;

	@Override
	public String getLocalizedMessage() {
		return "Fehler beim Laden der Karten aus dem Internet.";
	}

	@Override
	public String getMessage() {
		return "Error while loading map from internet.";
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
