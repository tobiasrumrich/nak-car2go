package de.nordakademie.wpk.team2.car2go.ui.providers;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.swt.graphics.Image;

import com.swtdesigner.ResourceManager;

import de.nordakademie.wpk.team2.car2go.core.businessobjects.ICar;
import de.nordakademie.wpk.team2.car2go.ui.views.Car2goView;
import de.nordakademie.wpk.team2.car2go.ui.views.RootNodeBean;

/**
 * CarLabelProvider
 * 
 * @author: Alexander Westen, Matthias Lüders
 */
public class CarLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		TreeNode node = (TreeNode) element;

		if (node.getValue() instanceof RootNodeBean && columnIndex == 0) {
			// Images for RootNodeBeans
			RootNodeBean rootNode = (RootNodeBean) node.getValue();
			if (rootNode.getNodeText().equals(Car2goView.BOOKMARKED)) {
				if (rootNode.getUser().isSignIn()) {
					return ResourceManager.getPluginImage(
							"de.nordakademie.wpk.team2.car2go.ui",
							"resources/icons/bookmarked_icon.png");
				} else {
					return ResourceManager.getPluginImage(
							"de.nordakademie.wpk.team2.car2go.ui",
							"resources/icons/bookmarked_gray_icon.png");
				}
			} else if (rootNode.getNodeText().equals(Car2goView.VACANT)) {
				return ResourceManager.getPluginImage(
						"de.nordakademie.wpk.team2.car2go.ui",
						"resources/icons/vacant_icon.png");
			}
		} else if (node instanceof ICar && columnIndex == 0) {
			// Images for ICar's
			ICar car = (ICar) node.getValue();
			if (car.getVacantState() == true) {
				return ResourceManager.getPluginImage(
						"de.nordakademie.wpk.team2.car2go.ui",
						"resources/icons/vacant_icon.png");
			} else {
				return ResourceManager.getPluginImage(
						"de.nordakademie.wpk.team2.car2go.ui",
						"resources/icons/bookmarked_icon.png");
			}
		}
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		TreeNode node = (TreeNode) element;

		try {

			if (node.getValue() instanceof RootNodeBean && columnIndex == 0) {
				// Text for RootNodeBean's
				RootNodeBean rootNode = (RootNodeBean) node.getValue();
				if (rootNode.getNodeText().equals(Car2goView.BOOKMARKED)
						&& rootNode.getUser().isSignIn()) {
					return rootNode.getNodeText() + " ("
							+ rootNode.getUser().getUsername() + ")";
				}
				return rootNode.getNodeText();
			} else if (node.getValue() instanceof ICar) {
				// Text for ICar's
				ICar car = (ICar) node.getValue();
				switch (columnIndex) {
				case 1:
					return car.getRegistrationNumber();
				case 2:
					return String.valueOf(car.getFuelState());
				case 3:
					return String.valueOf(car.getExteriorState());
				case 4:
					return String.valueOf(car.getInteriorState());
				case 5:
					return String.valueOf(car.getCoordinates().getLongitude())
							+ String.valueOf(car.getCoordinates()
									.getLongitude());
				case 6:
					return car.getLocation();
				default:
					return "";
				}
			} else if (node.getValue() instanceof String && columnIndex == 0) {
				return ((String) node.getValue());
			}
		} catch (Exception e) {
			return "";
		}
		return "";
	}
}
