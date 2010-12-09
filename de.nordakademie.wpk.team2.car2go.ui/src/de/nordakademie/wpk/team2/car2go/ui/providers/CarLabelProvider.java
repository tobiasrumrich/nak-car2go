package de.nordakademie.wpk.team2.car2go.ui.providers;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.swt.graphics.Image;

import com.swtdesigner.ResourceManager;

import de.nordakademie.wpk.team2.car2go.core.interfaces.ICar;
import de.nordakademie.wpk.team2.car2go.ui.views.NodeBean;

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

		if (node.getValue() instanceof NodeBean && columnIndex == 0) {
			// Images for RootNodeBeans
			NodeBean rootNode = (NodeBean) node.getValue();
			if (rootNode.getImagePath() != null
					&& rootNode.getImagePath() != "") {
				return ResourceManager.getPluginImage(
						"de.nordakademie.wpk.team2.car2go.ui",
						rootNode.getImagePath());

			}
		} else if (node instanceof ICar && columnIndex == 0) {
			// Images for ICar's
			ICar car = (ICar) node.getValue();
			if (car.getVacantState() == true) {
				return ResourceManager.getPluginImage(
						"de.nordakademie.wpk.team2.car2go.ui",
						"resources/icons/greenBall.png");
			} else {
				return ResourceManager.getPluginImage(
						"de.nordakademie.wpk.team2.car2go.ui",
						"resources/icons/redBall.png");
			}
		}
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		TreeNode node = (TreeNode) element;

		try {

			if (node.getValue() instanceof NodeBean && columnIndex == 0) {
				// Text for RootNodeBean's
				NodeBean rootNode = (NodeBean) node.getValue();
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
