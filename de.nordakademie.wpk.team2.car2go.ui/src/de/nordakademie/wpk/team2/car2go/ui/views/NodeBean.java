package de.nordakademie.wpk.team2.car2go.ui.views;

import org.eclipse.jface.viewers.TreeNode;

/**
 * RootNodeBean holds the RootNodes information for the carTrees in the
 * Car2goViews to provide the information for the CarLabelProvider.
 * 
 * @author: Alexander Westen, Matthias Lüders
 */
public class NodeBean {

	private String nodeText;
	private String imagePath;
	private TreeNode parent;

	public NodeBean(String nodeText) {
		this.nodeText = nodeText;
	}

	public NodeBean(String nodeText, String imagePath, TreeNode parent) {
		this.nodeText = nodeText;
		this.imagePath = imagePath;
		this.parent = parent;
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

	public TreeNode getParent() {
		return parent;
	}

	/**
	 * Returns the nodeText hast code plus the parents hash code if set.
	 */
	@Override
	public int hashCode() {
		return (parent != null) ? nodeText.hashCode() + parent.hashCode()
				: nodeText.hashCode();
	}

	/**
	 * Compares the node Text and the node parent if set.
	 */
	@Override
	public boolean equals(Object object) {
		if (object != null && object instanceof NodeBean) {
			NodeBean nodeBean = (NodeBean) object;
			if (parent != null && nodeBean.parent != null) {
				return (this.getNodeText().equals(nodeBean.getNodeText()) && this
						.getParent().equals(nodeBean.getParent()));
			} else {
				return (this.getNodeText().equals(nodeBean.getNodeText()));
			}
		}
		return false;
	}
}
