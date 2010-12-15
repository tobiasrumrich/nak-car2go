package de.nordakademie.wpk.team2.car2go.core.interfaces;

/**
 * 
 * @author Rumrich, Moehring
 *
 */
public enum EState {
	EXCELLENT("Excellent"), GOOD("Gut"),ISSUESEXIST("akzeptabel"),UNACCEPTABLE("Unakzeptabel");
	String text;
	
	private EState(String text){
		this.text = text;
	}
	
	public String getText(){
		return this.text;
	}
}
