package de.nordakademie.wpk.team2.car2go.product;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/*
 * This perspective includes the Car2goView.
 * 
 * @Autor: Alexander Westen, Matthias Lüders
 */
public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(true);
		layout.addStandaloneView(
				"de.nordakademie.wpk.team2.car2go.ui.views.Car2goView", true,
				IPageLayout.LEFT, 0.65f, layout.getEditorArea());
		layout.setFixed(true);
	}

}
