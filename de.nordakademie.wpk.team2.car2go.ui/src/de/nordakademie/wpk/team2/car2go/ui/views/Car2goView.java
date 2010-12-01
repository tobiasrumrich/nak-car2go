package de.nordakademie.wpk.team2.car2go.ui.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.part.ViewPart;

import de.nordakademie.wpk.team2.car2go.core.ICarService;
import de.nordakademie.wpk.team2.car2go.core.businessobjects.ICar;
import de.nordakademie.wpk.team2.car2go.ui.Activator;

public class Car2goView extends ViewPart {

	public static final String ID = "de.nordakademie.wpk.team2.car2go.ui.views.Car2goView"; //$NON-NLS-1$
	private Tree carTree;
	private Combo comboView;
	private final String TANKSTAND = "Tankstand";
	private final String GEOGRAFIE = "Geografie";
	private String username;

	public Car2goView() {
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));

		Label lblSichtAuswhlen = new Label(container, SWT.NONE);
		lblSichtAuswhlen.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblSichtAuswhlen.setText("Sicht ausw\u00E4hlen");

		comboView = new Combo(container, SWT.READ_ONLY);
		comboView.setItems(new String[] { TANKSTAND, GEOGRAFIE });
		comboView.select(0);

		comboView.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeView();
			}
		});

		TreeViewer treeViewer = new TreeViewer(container, SWT.BORDER);
		carTree = treeViewer.getTree();
		carTree.setHeaderVisible(true);
		carTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		TreeColumn treeColumnVacant = new TreeColumn(carTree, SWT.LEFT);
		treeColumnVacant.setWidth(100);
		treeColumnVacant.setText("Vakant");

		TreeColumn treeColumnRegistrationNumber = new TreeColumn(carTree,
				SWT.NONE);
		treeColumnRegistrationNumber.setWidth(100);
		treeColumnRegistrationNumber.setText("Kennzeichen");

		TreeColumn treeColumnFuelState = new TreeColumn(carTree, SWT.LEFT);
		treeColumnFuelState.setWidth(100);
		treeColumnFuelState.setText("Tankstand");

		TreeColumn treeColumnExteriorState = new TreeColumn(carTree, SWT.NONE);
		treeColumnExteriorState.setWidth(100);
		treeColumnExteriorState.setText("Au\u00DFenzustand");

		TreeColumn treeColumnInteriorState = new TreeColumn(carTree, SWT.NONE);
		treeColumnInteriorState.setWidth(100);
		treeColumnInteriorState.setText("Innenzustand");

		TreeColumn treeColumnGeoPoint = new TreeColumn(carTree, SWT.NONE);
		treeColumnGeoPoint.setToolTipText("Aktuelle Geokoordinaten");
		treeColumnGeoPoint.setWidth(100);
		treeColumnGeoPoint.setText("Geokoordinaten");
		
		TreeColumn treeColumnLocation = new TreeColumn(carTree, SWT.NONE);
		treeColumnLocation.setToolTipText("Aktueller Standort");
		treeColumnLocation.setWidth(100);
		treeColumnLocation.setText("Standort");


		// get the username
		getUsername();
		
		//treeViewer.setContentProvider(new ArrayContentProvider());
		//treeViewer.setLabelProvider(new CarLabelProvider());
		//treeViewer.setInput(getCars());

		new Label(container, SWT.NONE);

		initializeMenu();
	}

	protected void changeView() {
		if (comboView.getText().equals(GEOGRAFIE)) {

		} else {

		}
	}

	// private
	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		carTree.setFocus();
	}

	/*
	 * return the Cars separated by bookmarked and vacant as a List<TreeNode>
	 * @return The A list of TreeNodes with cars and vacant or bookmarked.
	 */
	private List<TreeNode> getCars() {
		ArrayList<TreeNode> tree = new ArrayList<TreeNode>();

		TreeNode nodeBookmarked = new TreeNode("Gemerkt");
		TreeNode nodeVakant = new TreeNode("Vakant");

		ICarService ics = Activator.getDefault().getCarService();
		Set<ICar> vacantCars = ics.getVacantCars(username);
		Set<ICar> bookmarkedCars = ics.getBookmarkedCars(username);

		Iterator<ICar> vacantIterator = vacantCars.iterator();
		while (vacantIterator.hasNext()) {
			ICar iCar = (ICar) vacantIterator.next();
			
			TreeNode node = new TreeNode(iCar);
			node.setParent(nodeVakant);
		}
		
		Iterator<ICar> bookmarkedIterator = vacantCars.iterator();
		while (bookmarkedIterator.hasNext()) {
			ICar iCar = (ICar) bookmarkedIterator.next();
			
			TreeNode node = new TreeNode(iCar);
			node.setParent(nodeBookmarked);
		}

		return tree;
	}
	
	private void getUsername() {
		// TODO Auto-generated method stub
		username = "TestUser";
	}
	

}
