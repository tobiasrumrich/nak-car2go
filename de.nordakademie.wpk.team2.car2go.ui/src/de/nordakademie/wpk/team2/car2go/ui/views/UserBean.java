package de.nordakademie.wpk.team2.car2go.ui.views;

/**
 * UserBean holds the username and the signIn state of the user. The bean is
 * used to exchange data between the UsernameDialog and the Car2goView.
 * 
 * @author: Alexander Westen, Matthias Lüders
 */
public class UserBean {

	private String username;
	private boolean signIn;

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setSignIn(boolean signIn) {
		this.signIn = signIn;
	}

	public boolean isSignIn() {
		return signIn;
	}
}
