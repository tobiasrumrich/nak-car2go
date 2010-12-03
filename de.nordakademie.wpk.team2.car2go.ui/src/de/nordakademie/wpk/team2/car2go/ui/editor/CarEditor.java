package de.nordakademie.wpk.team2.car2go.ui.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.swtdesigner.ResourceManager;

public class CarEditor extends EditorPart {

	public static final String ID = "de.nordakademie.wpk.todolist.ui.editor.CarEditor"; //$NON-NLS-1$
	public static final int PROP_SAVED = 42;

	private Text txtRegistrationNumber;
	private Text txtFuelState;
	private Text txtExteriorState;
	private Text txtInteriorState;
	private Text txtCoordinates;
	private Text txtLocation;
	private Text txtDescription;
	private boolean dirty;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(2, false));

		Label lblRegistrationNumber = new Label(parent, SWT.NONE);
		lblRegistrationNumber.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblRegistrationNumber.setText("Kennzeichen");

		txtRegistrationNumber = new Text(parent, SWT.BORDER);
		GridData gd_txtRegistrationNumber = new GridData(SWT.LEFT, SWT.CENTER,
				true, false, 1, 1);
		gd_txtRegistrationNumber.minimumWidth = 200;
		txtRegistrationNumber.setLayoutData(gd_txtRegistrationNumber);

		Label lblFuelState = new Label(parent, SWT.NONE);
		lblFuelState.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblFuelState.setText("Tankstand");

		txtFuelState = new Text(parent, SWT.BORDER);
		GridData gd_txtFuelState = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_txtFuelState.minimumWidth = 200;
		txtFuelState.setLayoutData(gd_txtFuelState);

		Label lblExteriorState = new Label(parent, SWT.NONE);
		lblExteriorState.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblExteriorState.setText("Au\u00DFenzustand");

		txtExteriorState = new Text(parent, SWT.BORDER);
		GridData gd_txtExteriorState = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_txtExteriorState.minimumWidth = 200;
		txtExteriorState.setLayoutData(gd_txtExteriorState);

		Label lblInteriorState = new Label(parent, SWT.NONE);
		lblInteriorState.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblInteriorState.setText("Innenzustand");

		txtInteriorState = new Text(parent, SWT.BORDER);
		GridData gd_txtInteriorState = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_txtInteriorState.minimumWidth = 200;
		txtInteriorState.setLayoutData(gd_txtInteriorState);

		Label lblCoordinates = new Label(parent, SWT.NONE);
		lblCoordinates.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblCoordinates.setText("Aktuelle Geokoordinate");

		txtCoordinates = new Text(parent, SWT.BORDER);
		GridData gd_txtGeoPoint = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_txtGeoPoint.minimumWidth = 200;
		txtCoordinates.setLayoutData(gd_txtGeoPoint);

		Label lblLocation = new Label(parent, SWT.NONE);
		lblLocation.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblLocation.setText("Aktueller Standort");

		txtLocation = new Text(parent, SWT.BORDER);
		GridData gd_txtLocation = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_txtLocation.minimumWidth = 200;
		txtLocation.setLayoutData(gd_txtLocation);

		Label lblVacant = new Label(parent, SWT.NONE);
		lblVacant.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblVacant.setText("Vakant");

		Label label = new Label(parent, SWT.NONE);
		label.setImage(ResourceManager
				.getPluginImage("de.nordakademie.wpk.team2.car2go.ui",
						"resources/icons/no.png"));

		Label lblDescription = new Label(parent, SWT.NONE);
		lblDescription.setText("Bemerkung");
		new Label(parent, SWT.NONE);

		txtDescription = new Text(parent, SWT.BORDER | SWT.MULTI);
		txtDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 2, 1));

		// setup widgets
		setupWidgetsWithData();
		setupDirtyHandling();
	}

	private void setupDirtyHandling() {
		ModifyListener modifList = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				markDirty();
			}
		};
		txtDescription.addModifyListener(modifList);
	}

	private void markDirty() {
		dirty = true;
		firePropertyChange(PROP_DIRTY);
	}

	private void setupWidgetsWithData() {
		CarEditorInput editorInput = (CarEditorInput) getEditorInput();
		txtRegistrationNumber.setText(editorInput.getCar()
				.getRegistrationNumber());
		txtFuelState.setText(String
				.valueOf(editorInput.getCar().getFuelState()));
		txtExteriorState.setText(String.valueOf(editorInput.getCar()
				.getExteriorState()));
		txtInteriorState.setText(String.valueOf(editorInput.getCar()
				.getInteriorState()));
		txtCoordinates.setText(String.valueOf(editorInput.getCar()
				.getCoordinates().getLongitude())
				+ String.valueOf(editorInput.getCar().getCoordinates()
						.getLongitude()));
		txtLocation.setText(editorInput.getCar().getLocation());
		txtDescription.setText(editorInput.getCar().getDescription());
	}

	@Override
	public void setFocus() {
		txtDescription.setFocus();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save As operation
		monitor.beginTask("Speicher Beschreibung", IProgressMonitor.UNKNOWN);
		dirty = false;
		setupDomainWithData();
		firePropertyChange(PROP_DIRTY);
		firePropertyChange(PROP_SAVED);
		monitor.done();
	}

	private void setupDomainWithData() {
		CarEditorInput editorInput = (CarEditorInput) getEditorInput();

		// Save to the domain
		editorInput.getCar().setDescription(txtDescription.getText());
		// TODO Save to the Server
	}

	@Override
	public void doSaveAs() {

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// Initialize the editor part
		setSite(site);
		setInput(input);

	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

}
