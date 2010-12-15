package de.nordakademie.wpk.team2.car2go.core.interfaces;

/**
 * An Enum to represent the status of an car
 * 
 * @author Rumrich, Moehring
 * 
 */
public enum EState {
	EXCELLENT("Excellent"), GOOD("Gut"), ISSUESEXIST("Akzeptabel"), UNACCEPTABLE(
			"Unakzeptabel");
	String text;

	private EState(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
}
