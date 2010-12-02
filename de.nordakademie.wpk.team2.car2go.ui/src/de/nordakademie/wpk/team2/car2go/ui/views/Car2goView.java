package de.nordakademie.wpk.team2.car2go.ui.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
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

public class Car2goView extends ViewPart {

	public static final String ID = "de.nordakademie.wpk.team2.car2go.ui.views.Car2goView"; //$NON-NLS-1$
	private final String TANKSTAND = "Tankstand";
	private final String GEOGRAFIE = "Geografie";
	public static final String BOOKMARKED = "Gemerkt";
	public static final String VACANT = "Vakant";

	private Tree carTree;
	private Combo comboView;
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
			/**
			 * redraws the carTree with the selected View
			 */
			public void widgetSelected(SelectionEvent e) {
				carTree.redraw();
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

		//TODO Sortierung und so
		/*
        SelectionAdapter adapter = new SelectionAdapter() {
            private SORTTYPE sorttype = SORTTYPE.ASC;
            public void widgetSelected(SelectionEvent e) {
                assert e.widget instanceof TreeColumn : "Widget is not a TreeColumn";
                TreeColumn column = (TreeColumn) e.widget;
                tree.setSortColumn(column);
                if (sorttype == SORTTYPE.ASC) {
                    sorttype = SORTTYPE.DESC;
                    tree.setSortDirection(SWT.DOWN);
                }
                else {
                    sorttype = SORTTYPE.ASC;
                    tree.setSortDirection(SWT.UP);
                }
                tv.setComparator(new PersonTreeComparator(sorttype));
            }
        };
		*/
		
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

		// get the username from the user
		getUsername();

		treeViewer.setContentProvider(new TreeNodeContentProvider());
		treeViewer.setLabelProvider(new CarLabelProvider());
		treeViewer.setInput(getCars());
		treeViewer.expandAll();

		new Label(container, SWT.NONE);

		initializeMenu();
	}

	// private
	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		// TODO menu manager
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		carTree.setFocus();
	}

	/**
	 * return the Cars separated by bookmarked and vacant as a List<TreeNode>
	 * 
	 * @return The A list of TreeNodes with cars and vacant or bookmarked.
	 */
	private TreeNode[] getCars() {
		//ArrayList<TreeNode> tree = new ArrayList<TreeNode>();

		// TODO Gemerkt und Vakant als konstante

		TreeNode[] tree = new TreeNode[2];
		
		TreeNode nodeBookmarked = new TreeNode(BOOKMARKED);
		nodeBookmarked.setParent(null);
		tree[0] = nodeBookmarked;
		TreeNode nodeVakant = new TreeNode(VACANT);
		nodeVakant.setParent(null);
		tree[1] = nodeVakant;
		
		
		System.out.println(comboView.getText());
		
		
		/*
		ICarService ics = Activator.getDefault().getCarService();
		Set<ICar> vacantCars = ics.getVacantCars(username);
		Set<ICar> bookmarkedCars = ics.getBookmarkedCars(username);

		// TODO Iterator in eigene Methode kapseln
		Iterator<ICar> vacantIterator = vacantCars.iterator();
		while (vacantIterator.hasNext()) {
			ICar iCar = (ICar) vacantIterator.next();

			TreeNode node = new TreeNode(iCar);
			node.setParent(nodeVakant);

			// TODO Geografie oder Tankstand als child einfügen
			if (comboView.getText().equals(GEOGRAFIE)) {

			} else {

			}
		}

		Iterator<ICar> bookmarkedIterator = bookmarkedCars.iterator();
		while (bookmarkedIterator.hasNext()) {
			ICar iCar = (ICar) bookmarkedIterator.next();

			TreeNode node = new TreeNode(iCar);
			node.setParent(nodeBookmarked);

			// TODO Geografie oder Tankstand als child einfügen
			if (comboView.getText().equals(GEOGRAFIE)) {

			} else {

			}
		}
		*/
		return tree;
	}

	/**
	 * Invokes a Dialog to get the users user name to identify his bookmarked
	 * cars
	 */
	private void getUsername() {
		// TODO Auto-generated method stub
		username = "TestUser";
	}

}
