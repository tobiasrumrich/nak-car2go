package de.nordakademie.wpk.team2.car2go.ui.exceptions;

public class ServiceNotAvailableException extends Exception {
	private static final long serialVersionUID = 1L;

	@Override
	public String getLocalizedMessage() {
		return "Service steht nicht zur Verf�gung.";
	}

	@Override
	public String getMessage() {
		return "Service is not available.";
	}

	@Override
	public String toString() {
		return super.toString();
	}}
