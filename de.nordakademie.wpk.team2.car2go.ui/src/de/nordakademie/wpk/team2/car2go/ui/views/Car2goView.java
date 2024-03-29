package de.nordakademie.wpk.team2.car2go.ui.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.part.ViewPart;

import com.swtdesigner.ResourceManager;

import de.nordakademie.wpk.team2.car2go.core.interfaces.ICar;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICarService;
import de.nordakademie.wpk.team2.car2go.ui.Activator;
import de.nordakademie.wpk.team2.car2go.ui.dialog.UsernameDialog;
import de.nordakademie.wpk.team2.car2go.ui.providers.CarLabelProvider;

/**
 * Car2goView h�lt alle Verf�gbaren und Gebookmarkten Cars
 * 
 * @author: Alexander Westen, Matthias L�ders
 */
public class Car2goView extends ViewPart {

	public static final String ID = "de.nordakademie.wpk.team2.car2go.ui.views.Car2goView"; //$NON-NLS-1$
	private final String TANKSTAND = "Tankstand";
	private final String GEOGRAFIE = "Geografie";
	public static final String BOOKMARKED = "Gemerkt";
	public static final String VACANT = "Vakant";

	private Tree carTree;
	private Combo comboView;
	private UserBean user = new UserBean();
	private TreeViewer treeViewer;
	private Label lblUsername;
	private Label lblSignIn;

	public Car2goView() {
		setPartName("de.nordakademie.wpk.team2.car2go.ui.views.Car2goView");
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(5, false));

		Label lblSichtAuswhlen = new Label(container, SWT.NONE);
		lblSichtAuswhlen.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblSichtAuswhlen.setText("Sicht ausw\u00E4hlen:");

		SelectionAdapter refreshListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				refresh();
			}
		};

		comboView = new Combo(container, SWT.READ_ONLY);
		comboView.setItems(new String[] { TANKSTAND, GEOGRAFIE });
		comboView.select(0);
		comboView.addSelectionListener(refreshListener);

		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(refreshListener);
		button.setImage(ResourceManager.getPluginImage(
				"de.nordakademie.wpk.team2.car2go.ui",
				"resources/icons/refresh.png"));
		button.setToolTipText("Aktualisieren");

		lblUsername = new Label(container, SWT.NONE);
		lblUsername.setText("nicht angemeldet");
		lblUsername.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true,
				false, 1, 1));

		lblSignIn = new Label(container, SWT.NONE);
		lblSignIn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));

		treeViewer = new TreeViewer(container, SWT.BORDER);
		carTree = treeViewer.getTree();
		carTree.setLinesVisible(true);
		carTree.setHeaderVisible(true);
		carTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));

		TreeColumn treeColumnRegistrationNumber = new TreeColumn(carTree,
				SWT.NONE);
		treeColumnRegistrationNumber.setWidth(250);
		treeColumnRegistrationNumber.setText("Kennzeichen");

		TreeColumn treeColumnFuelState = new TreeColumn(carTree, SWT.LEFT);
		treeColumnFuelState.setWidth(80);
		treeColumnFuelState.setText("Tankstand");

		TreeColumn treeColumnExteriorState = new TreeColumn(carTree, SWT.NONE);
		treeColumnExteriorState.setWidth(110);
		treeColumnExteriorState.setText("Au\u00DFenzustand");

		TreeColumn treeColumnInteriorState = new TreeColumn(carTree, SWT.NONE);
		treeColumnInteriorState.setWidth(100);
		treeColumnInteriorState.setText("Innenzustand");

		TreeColumn treeColumnGeoPoint = new TreeColumn(carTree, SWT.NONE);
		treeColumnGeoPoint.setToolTipText("Aktuelle Geokoordinaten");
		treeColumnGeoPoint.setWidth(120);
		treeColumnGeoPoint.setText("Geokoordinaten");

		TreeColumn treeColumnLocation = new TreeColumn(carTree, SWT.NONE);
		treeColumnLocation.setToolTipText("Aktueller Standort");
		treeColumnLocation.setWidth(200);
		treeColumnLocation.setText("Standort");

		treeViewer.setContentProvider(new TreeNodeContentProvider());
		treeViewer.setLabelProvider(new CarLabelProvider());

		// Create the ViewerComparator, to compare ICars and NodeBeans .
		treeViewer.setComparator(new ViewerComparator() {
			@Override
			public int compare(Viewer viewer, Object e1, Object e2) {
				if (e1 instanceof TreeNode && e2 instanceof TreeNode) {
					if (((TreeNode) e1).getValue() instanceof ICar
							&& ((TreeNode) e2).getValue() instanceof ICar) {
						ICar c1 = (ICar) ((TreeNode) e1).getValue();
						ICar c2 = (ICar) ((TreeNode) e2).getValue();
						if (c1 != null && c2 != null) {
							return (c1.getRegistrationNumber().compareTo(c2
									.getRegistrationNumber()));
						}
					} else if (((TreeNode) e1).getValue() instanceof NodeBean
							&& ((TreeNode) e2).getValue() instanceof NodeBean) {
						NodeBean n1 = (NodeBean) ((TreeNode) e1).getValue();
						NodeBean n2 = (NodeBean) ((TreeNode) e2).getValue();
						if (n1 != null && n2 != null) {
							return (n1.getNodeText().compareTo(n2.getNodeText()));
						}
					}
				}
				return super.compare(viewer, e1, e2);
			}
		});
		treeViewer.setComparer(null);

		// initialize context menu
		initializeContextMenu();
		// get the username from the user
		getUsernameDialog();
		// refresh the treeView data
		refresh();
	}

	/**
	 * Refresh the TreeView rebuild the users expanded tree state.
	 */
	public void refresh() {
		Object[] expandedElements = treeViewer.getExpandedElements();
		treeViewer.setInput(getCars());
		treeViewer.setExpandedElements(expandedElements);
		treeViewer.refresh();
	}

	@Override
	public void setFocus() {
		carTree.setFocus();
	}

	/**
	 * Invokes a Error Message Box with a fancy headline.
	 * 
	 * @param message
	 *            Error message
	 */
	public void errorMessage(String message) {
		MessageBox messageBox = new MessageBox(this.getSite().getShell(),
				SWT.OK | SWT.ICON_ERROR);
		messageBox.setText("Ups... das sollte nicht passieren!");
		messageBox.setMessage(message);
		messageBox.open();
	}

	/**
	 * Returns the UserBean
	 * 
	 * @return UserBean
	 */
	public UserBean getUser() {
		return user;
	}

	/**
	 * Initialize the ContextMenu
	 */
	private void initializeContextMenu() {
		MenuManager contextMenuManager = new MenuManager();
		getViewSite().registerContextMenu(contextMenuManager, treeViewer);
		getViewSite().setSelectionProvider(treeViewer);
		Menu contextMenu = contextMenuManager.createContextMenu(treeViewer
				.getTree());
		carTree.setMenu(contextMenu);
	}

	/**
	 * This method returns the Cars separated by bookmarked and vacant as an
	 * array of TreeNodes
	 * 
	 * @return The A list of TreeNodes with cars and vacant or bookmarked.
	 */
	private TreeNode[] getCars() {
		Set<ICar> vacantCars;
		TreeNode[] rootTreeNode = new TreeNode[2];
		ArrayList<TreeNode> vacantChilds = new ArrayList<TreeNode>();
		ArrayList<TreeNode> bookmarkedChilds = new ArrayList<TreeNode>();
		// dummy tree for ArrayList Cast
		TreeNode[] treeNodeType = new TreeNode[0];

		NodeBean bookmarked;
		if (user.isSignIn()) {
			bookmarked = new NodeBean(BOOKMARKED,
					"resources/icons/tree/bookmarked.png");
		} else {
			bookmarked = new NodeBean(BOOKMARKED,
					"resources/icons/tree/bookmarkedGray.png");
		}
		NodeBean vacant = new NodeBean(VACANT,
				"resources/icons/tree/vacant.png");

		TreeNode nodeBookmarked = new TreeNode(bookmarked);
		nodeBookmarked.setParent(null);
		rootTreeNode[0] = nodeBookmarked;
		TreeNode nodeVacant = new TreeNode(vacant);
		nodeVacant.setParent(null);
		rootTreeNode[1] = nodeVacant;

		// Get the connection to the CarService
		ICarService ics = Activator.getDefault().getCarService();

		// Get the vacant cars
		try {
			vacantCars = ics.getVacantCars();
			groupCars(vacantCars, nodeVacant, comboView.getText());
		} catch (Exception e) {
			TreeNode noVacantNode = new TreeNode(new NodeBean(
					"Keine Daten verf�gbar", "resources/icons/tree/bomb.png"));
			vacantChilds.add(noVacantNode);
			nodeVacant.setChildren(vacantChilds.toArray(treeNodeType));
			errorMessage("Der Server ist nicht erreichbar.");
		}

		// Get the bookmarked cars if the user is signed in
		if (user.isSignIn()) {
			try {
				Set<ICar> bookmarkedCars = ics.getBookmarkedCars(getUser()
						.getUsername());
				groupCars(bookmarkedCars, nodeBookmarked, comboView.getText());
			} catch (Exception e) {
				TreeNode noBookmarkedNode = new TreeNode(new NodeBean(
						"Keine Daten verf�gbar",
						"resources/icons/tree/bomb.png"));
				bookmarkedChilds.add(noBookmarkedNode);
				nodeBookmarked.setChildren(bookmarkedChilds
						.toArray(treeNodeType));
			}
		} else {
			TreeNode nodeNoBookmarks = new TreeNode(new NodeBean(
					"Nicht angemeldet", "resources/icons/tree/bomb.png"));
			bookmarkedChilds.add(nodeNoBookmarks);
			nodeBookmarked.setChildren(bookmarkedChilds.toArray(treeNodeType));
		}
		return rootTreeNode;
	}

	/**
	 * Groups the cars by FuelState or Location
	 * 
	 * @param cars
	 *            Set of cars
	 * @param parent
	 *            Parent TreeNode of the cars
	 * @param viewType
	 *            User selected group type
	 */
	private void groupCars(Set<ICar> cars, TreeNode parent, String viewType) {
		if (viewType.equals(GEOGRAFIE)) {
			groupCarsByLocation(cars, parent);
		} else {
			groupCarsByFuel(cars, parent);
		}
	}

	/**
	 * Groups the cars by their location in the tree view.
	 * 
	 * @param cars
	 *            Set of cars
	 * @param parent
	 *            TreeNode of the cars
	 */
	private void groupCarsByLocation(Set<ICar> cars, TreeNode parent) {
		HashMap<String, ArrayList<TreeNode>> locations = new HashMap<String, ArrayList<TreeNode>>();
		ArrayList<TreeNode> locationNodes = new ArrayList<TreeNode>();
		TreeNode[] treeNodeType = new TreeNode[0];

		// Map all cars to a location
		Iterator<ICar> carIterator = cars.iterator();
		while (carIterator.hasNext()) {
			ICar iCar = (ICar) carIterator.next();
			TreeNode carNode = new TreeNode(iCar);

			if (!locations.containsKey(iCar.getLocation())) {
				locations.put(iCar.getLocation(), new ArrayList<TreeNode>());
			}
			locations.get(iCar.getLocation()).add(carNode);
		}

		// create a node for each location and add the matching cars
		for (Iterator<String> locationIterator = locations.keySet().iterator(); locationIterator
				.hasNext();) {
			String locationString = (String) locationIterator.next();
			// neue location node erstellen
			TreeNode locationNode = new TreeNode(new NodeBean(locationString));
			// childs der location mappen
			locationNode.setChildren(locations.get(locationString).toArray(
					treeNodeType));
			// node zu den locationNodes hinzuf�gen
			locationNodes.add(locationNode);
		}

		// map all locations to the parent node
		parent.setChildren(locationNodes.toArray(treeNodeType));
	}

	/**
	 * Groups the cars by fuel State in the tree view.
	 * 
	 * @param cars
	 *            Set of cars
	 * @param parent
	 *            TreeNode of the cars
	 */
	private void groupCarsByFuel(Set<ICar> cars, TreeNode parent) {
		TreeNode[] parentChilds = new TreeNode[4];
		TreeNode[] treeNodeType = new TreeNode[0];

		// Build the goups
		TreeNode nodeFuelState44 = new TreeNode(new NodeBean("4/4",
				"resources/icons/tree/100percent.png", parent));
		TreeNode nodeFuelState34 = new TreeNode(new NodeBean("3/4",
				"resources/icons/tree/75percent.png", parent));
		TreeNode nodeFuelState24 = new TreeNode(new NodeBean("2/4",
				"resources/icons/tree/50percent.png", parent));
		TreeNode nodeFuelState14 = new TreeNode(new NodeBean("1/4",
				"resources/icons/tree/25percent.png", parent));

		parentChilds[0] = nodeFuelState44;
		parentChilds[1] = nodeFuelState34;
		parentChilds[2] = nodeFuelState24;
		parentChilds[3] = nodeFuelState14;

		parent.setChildren(parentChilds);

		ArrayList<TreeNode> childs44 = new ArrayList<TreeNode>();
		ArrayList<TreeNode> childs34 = new ArrayList<TreeNode>();
		ArrayList<TreeNode> childs24 = new ArrayList<TreeNode>();
		ArrayList<TreeNode> childs14 = new ArrayList<TreeNode>();

		// Group the cars
		Iterator<ICar> vacantIterator = cars.iterator();
		while (vacantIterator.hasNext()) {
			ICar iCar = (ICar) vacantIterator.next();
			TreeNode node = new TreeNode(iCar);

			if (iCar.getFuelState() > 75) {
				childs44.add(node);
			} else if (iCar.getFuelState() > 50) {
				childs34.add(node);
			} else if (iCar.getFuelState() > 25) {
				childs24.add(node);
			} else {
				childs14.add(node);
			}
		}

		nodeFuelState44.setChildren(childs44.toArray(treeNodeType));
		nodeFuelState34.setChildren(childs34.toArray(treeNodeType));
		nodeFuelState24.setChildren(childs24.toArray(treeNodeType));
		nodeFuelState14.setChildren(childs14.toArray(treeNodeType));
	}

	/**
	 * Invokes a Dialog to get the users user name to identify his bookmarked
	 * cars and set the SignIn Icons.
	 */
	private void getUsernameDialog() {
		UsernameDialog dialog = new UsernameDialog(this.getSite().getShell(),
				getUser());
		int returnCode = dialog.open();

		if (getUser().getUsername() == "" || getUser().getUsername() == null
				|| returnCode == 1) {
			getUser().setSignIn(false);
			lblUsername.setText("Nicht angemeldet");
			lblSignIn.setImage(ResourceManager.getPluginImage(
					"de.nordakademie.wpk.team2.car2go.ui",
					"resources/icons/redBall.png"));
		} else {
			getUser().setSignIn(true);
			lblUsername.setText("Angemeldet als: " + getUser().getUsername());
			lblSignIn.setImage(ResourceManager.getPluginImage(
					"de.nordakademie.wpk.team2.car2go.ui",
					"resources/icons/greenBall.png"));
		}

		if (user.isSignIn()) {
			System.setProperty(
					"de.nordakademie.wpk.team2.car2go.isUserSignedIn", "true");
		} else {
			System.setProperty(
					"de.nordakademie.wpk.team2.car2go.isUserSignedIn", "false");
		}
	}
}
