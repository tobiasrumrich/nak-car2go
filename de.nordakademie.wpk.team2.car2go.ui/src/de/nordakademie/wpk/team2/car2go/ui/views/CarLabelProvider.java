package de.nordakademie.wpk.team2.car2go.ui.views;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.swt.graphics.Image;

import com.swtdesigner.ResourceManager;

import de.nordakademie.wpk.team2.car2go.core.businessobjects.ICar;

public class CarLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		TreeNode node = (TreeNode) element;

		if(node.getValue() instanceof String && columnIndex == 0) {
			if(node.getValue().equals(Car2goView.BOOKMARKED)){
				return ResourceManager.getPluginImage("de.nordakademie.wpk.team2.car2go.ui", "resources/icons/bookmarked_icon.png");
			}else if (node.getValue().equals(Car2goView.VACANT)) {
				return ResourceManager.getPluginImage("de.nordakademie.wpk.team2.car2go.ui", "resources/icons/vacant_icon.png");
			}
		}else if (node instanceof ICar && columnIndex == 0) {
			ICar car = (ICar) node.getValue();
			if(car.getVacantState() == true){
				return ResourceManager.getPluginImage("de.nordakademie.wpk.team2.car2go.ui", "resources/icons/vacant_icon.png");				
			}else {
				return ResourceManager.getPluginImage("de.nordakademie.wpk.team2.car2go.ui", "resources/icons/bookmarked_icon.png");
			}
		}
		return null;		
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		TreeNode node = (TreeNode) element;

		if(node.getValue() instanceof String && columnIndex == 0) {
			return ((String) node.getValue());
		}
		
		if (node.getValue() instanceof ICar) {
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
						+ String.valueOf(car.getCoordinates().getLongitude());
			case 6:
				return car.getLocation();
			default:
				return "";
			}
		} else if(node.getValue() instanceof String && columnIndex == 0) {
				return ((String) node.getValue());
		}
		return "";
	}
}
