package de.nordakademie.wpk.team2.car2go.ui.views;

/**
 * RootNodeBean holds the RootNodes information for the carTrees in the
 * Car2goViews to provide the information for the CarLabelProvider.
 * 
 * @author: Alexander Westen, Matthias Lüders
 */
public class NodeBean {

	private String nodeText;
	private String imagePath;
	
	public NodeBean(String nodeText) {
		this.nodeText = nodeText;
	}

	public NodeBean(String nodeText, String imagePath) {
		this.nodeText = nodeText;
		this.imagePath = imagePath;
	}

	public void setNodeText(String nodeText) {
		this.nodeText = nodeText;
	}

	public String getNodeText() {
		return nodeText;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImagePath() {
		return imagePath;
	}
}
