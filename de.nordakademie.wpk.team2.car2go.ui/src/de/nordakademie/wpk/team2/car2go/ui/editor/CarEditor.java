package de.nordakademie.wpk.team2.car2go.ui.editor;

import java.io.ByteArrayInputStream;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.swtdesigner.ResourceManager;

import de.nordakademie.wpk.team2.car2go.core.exception.IllegalCommentException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalRegistrationNumberException;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICarService;
import de.nordakademie.wpk.team2.car2go.ui.Activator;
import de.nordakademie.wpk.team2.car2go.ui.exceptions.ServiceNotAvailableException;

/**
 * The CarEditor displays all information for the selected car and offers the
 * possibility to save comments for Cars.
 * 
 * @author: Alexander Westen, Matthias Lüders
 */
public class CarEditor extends EditorPart {

	public static final String ID = "de.nordakademie.wpk.team2.car2go.ui.editor.CarEditor"; //$NON-NLS-1$
	public static final int PROP_SAVED = 42;

	private Text txtRegistrationNumber;
	private Text txtFuelState;
	private Text txtExteriorState;
	private Text txtInteriorState;
	private Text txtCoordinates;
	private Text txtLocation;
	private Text txtDescription;
	private boolean dirty;
	private Label lblGoogleMapsPosition;
	private Label lblVacantImage;

	// TODO RegistrationNumber als Überschrift setzen

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(3, false));

		Label lblRegistrationNumber = new Label(parent, SWT.NONE);
		lblRegistrationNumber.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblRegistrationNumber.setText("Kennzeichen");

		txtRegistrationNumber = new Text(parent, SWT.BORDER);
		txtRegistrationNumber.setEditable(false);
		GridData gd_txtRegistrationNumber = new GridData(SWT.LEFT, SWT.CENTER,
				true, false, 1, 1);
		gd_txtRegistrationNumber.minimumWidth = 200;
		txtRegistrationNumber.setLayoutData(gd_txtRegistrationNumber);

		lblGoogleMapsPosition = new Label(parent, SWT.NONE);
		lblGoogleMapsPosition.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				true, false, 1, 6));
		lblGoogleMapsPosition.setText("GoogleMaps Bild");

		Label lblFuelState = new Label(parent, SWT.NONE);
		lblFuelState.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblFuelState.setText("Tankstand");

		txtFuelState = new Text(parent, SWT.BORDER);
		txtFuelState.setEditable(false);
		GridData gd_txtFuelState = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_txtFuelState.minimumWidth = 200;
		txtFuelState.setLayoutData(gd_txtFuelState);

		Label lblExteriorState = new Label(parent, SWT.NONE);
		lblExteriorState.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblExteriorState.setText("Au\u00DFenzustand");

		txtExteriorState = new Text(parent, SWT.BORDER);
		txtExteriorState.setEditable(false);
		GridData gd_txtExteriorState = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_txtExteriorState.minimumWidth = 200;
		txtExteriorState.setLayoutData(gd_txtExteriorState);

		Label lblInteriorState = new Label(parent, SWT.NONE);
		lblInteriorState.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblInteriorState.setText("Innenzustand");

		txtInteriorState = new Text(parent, SWT.BORDER);
		txtInteriorState.setEditable(false);
		GridData gd_txtInteriorState = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_txtInteriorState.minimumWidth = 200;
		txtInteriorState.setLayoutData(gd_txtInteriorState);

		Label lblCoordinates = new Label(parent, SWT.NONE);
		lblCoordinates.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblCoordinates.setText("Geokoordinate");

		txtCoordinates = new Text(parent, SWT.BORDER);
		txtCoordinates.setEditable(false);
		GridData gd_txtGeoPoint = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_txtGeoPoint.minimumWidth = 200;
		txtCoordinates.setLayoutData(gd_txtGeoPoint);

		Label lblVacant = new Label(parent, SWT.NONE);
		lblVacant.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblVacant.setText("Vakant");

		lblVacantImage = new Label(parent, SWT.NONE);
		lblVacantImage.setImage(ResourceManager.getPluginImage(
				"de.nordakademie.wpk.team2.car2go.ui",
				"resources/icons/redBall.png"));

		Label lblLocation = new Label(parent, SWT.NONE);
		lblLocation.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblLocation.setText("Standort");

		txtLocation = new Text(parent, SWT.BORDER);
		txtLocation.setEditable(false);
		GridData gd_txtLocation = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 2, 1);
		gd_txtLocation.minimumWidth = 200;
		txtLocation.setLayoutData(gd_txtLocation);

		Label lblDescription = new Label(parent, SWT.NONE);
		lblDescription.setText("Bemerkung");

		txtDescription = new Text(parent, SWT.BORDER | SWT.MULTI);
		txtDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 3, 1));

		// setup widgets
		setupWidgetsWithData();
		setupDirtyHandling();
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// Initialize the editor part
		setSite(site);
		setInput(input);
	}

	/**
	 * DirtyHandling for the Widget
	 */
	private void setupDirtyHandling() {
		ModifyListener modifList = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				markDirty();
			}
		};
		txtDescription.addModifyListener(modifList);
	}

	/**
	 * Marks the Widget as dirty
	 */
	private void markDirty() {
		dirty = true;
		firePropertyChange(PROP_DIRTY);
	}

	/**
	 * Setup the Widget with the ICar data
	 */
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
				+ " / "
				+ String.valueOf(editorInput.getCar().getCoordinates()
						.getLongitude()));
		txtLocation.setText(editorInput.getCar().getLocation());
		if (editorInput.getCar().getComment() != null) {
			txtDescription.setText(editorInput.getCar().getComment());
		}

		// display the vacant state as a picture
		if (editorInput.getCar().getVacantState()) {
			lblVacantImage.setImage(ResourceManager.getPluginImage(
					"de.nordakademie.wpk.team2.car2go.ui",
					"resources/icons/greenBall.png"));
		} else {
			lblVacantImage.setImage(ResourceManager.getPluginImage(
					"de.nordakademie.wpk.team2.car2go.ui",
					"resources/icons/redBall.png"));
		}

		// convert the ByteArray to a awesome google maps picture
		try {
			byte[] byteArray = Activator
					.getDefault()
					.getCarService()
					.getMapForCar(editorInput.getCar().getRegistrationNumber(),
							160, 180, 14);
			Image image = new Image(Display.getCurrent(),
					new ByteArrayInputStream(byteArray));
			lblGoogleMapsPosition.setImage(image);
		} catch (Exception e) {
			lblGoogleMapsPosition.setText("Position nicht verfügbar");
		}
	}

	@Override
	public void setFocus() {
		txtDescription.setFocus();
	}

	/**
	 * Save progress etc.
	 */
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

	/**
	 * Setup the domain with the data.
	 */
	private void setupDomainWithData() {
		CarEditorInput editorInput = (CarEditorInput) getEditorInput();

		// Save to the domain
		editorInput.getCar().setComment(txtDescription.getText());
		ICarService ics;

		// Get the service
		try {
			ics = Activator.getDefault().getCarService();
		} catch (ServiceNotAvailableException e) {
			errorMessage(e.getLocalizedMessage());
			return;
		}

		// Save the comment
		try {
			ics.setComment(editorInput.getCar().getRegistrationNumber(),
					txtDescription.getText());
		} catch (RegistrationNumberNotFoundException e) {
			errorMessage(e.getLocalizedMessage());
		} catch (IllegalRegistrationNumberException e) {
			errorMessage(e.getLocalizedMessage());
		} catch (IllegalCommentException e) {
			errorMessage(e.getLocalizedMessage());
		}
	}

	/**
	 * Display an error message
	 * 
	 * @param message
	 *            the message
	 */
	private void errorMessage(String message) {
		MessageBox messageBox = new MessageBox(this.getSite().getShell(),
				SWT.OK | SWT.ICON_ERROR);
		messageBox.setText("Ups.. das sollte nicht passieren!");
		messageBox.setMessage(message);
		messageBox.open();
	}

	@Override
	public void doSaveAs() {

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
