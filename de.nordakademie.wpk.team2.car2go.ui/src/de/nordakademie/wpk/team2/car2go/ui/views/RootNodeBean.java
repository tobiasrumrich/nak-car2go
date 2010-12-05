package de.nordakademie.wpk.team2.car2go.ui.views;

/**
 * RootNodeBean holds the RootNodes information for the carTrees in the
 * Car2goViews to provide the information for the CarLabelProvider.
 * 
 * @author: Alexander Westen, Matthias Lüders
 */
public class RootNodeBean {

	private String nodeText;
	private UserBean user;

	public RootNodeBean(String nodeText, UserBean user) {
		this.nodeText = nodeText;
		this.user = user;
	}

	public void setNodeText(String nodeText) {
		this.nodeText = nodeText;
	}

	public String getNodeText() {
		return nodeText;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public UserBean getUser() {
		return user;
	}
}
