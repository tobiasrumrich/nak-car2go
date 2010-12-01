package de.nordakademie.wpk.team2.car2go.ui.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import com.swtdesigner.ResourceManager;

public class CarEditor extends EditorPart {
	private Text txtRegistrationNumber;
	private Text txtFuelState;
	private Text txtExteriorState;
	private Text txtInteriorState;
	private Text txtGeoPoint;
	private Text txtLocation;
	private Text txtComment;

	public CarEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		
		Label lblRegistrationNumber = new Label(parent, SWT.NONE);
		lblRegistrationNumber.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRegistrationNumber.setText("Kennzeichen");
		
		txtRegistrationNumber = new Text(parent, SWT.BORDER);
		GridData gd_txtRegistrationNumber = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_txtRegistrationNumber.minimumWidth = 200;
		txtRegistrationNumber.setLayoutData(gd_txtRegistrationNumber);
		
		Label lblFuelState = new Label(parent, SWT.NONE);
		lblFuelState.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuelState.setText("Tankstand");
		
		txtFuelState = new Text(parent, SWT.BORDER);
		GridData gd_txtFuelState = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_txtFuelState.minimumWidth = 200;
		txtFuelState.setLayoutData(gd_txtFuelState);
		
		Label lblExteriorState = new Label(parent, SWT.NONE);
		lblExteriorState.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblExteriorState.setText("Au\u00DFenzustand");
		
		txtExteriorState = new Text(parent, SWT.BORDER);
		GridData gd_txtExteriorState = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_txtExteriorState.minimumWidth = 200;
		txtExteriorState.setLayoutData(gd_txtExteriorState);
		
		Label lblInteriorState = new Label(parent, SWT.NONE);
		lblInteriorState.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblInteriorState.setText("Innenzustand");
		
		txtInteriorState = new Text(parent, SWT.BORDER);
		GridData gd_txtInteriorState = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_txtInteriorState.minimumWidth = 200;
		txtInteriorState.setLayoutData(gd_txtInteriorState);
		
		Label lblGeoPoint = new Label(parent, SWT.NONE);
		lblGeoPoint.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblGeoPoint.setText("Aktuelle Geokoordinate");
		
		txtGeoPoint = new Text(parent, SWT.BORDER);
		GridData gd_txtGeoPoint = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_txtGeoPoint.minimumWidth = 200;
		txtGeoPoint.setLayoutData(gd_txtGeoPoint);
		
		Label lblLocation = new Label(parent, SWT.NONE);
		lblLocation.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLocation.setText("Aktueller Standort");
		
		txtLocation = new Text(parent, SWT.BORDER);
		GridData gd_txtLocation = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_txtLocation.minimumWidth = 200;
		txtLocation.setLayoutData(gd_txtLocation);
		
		Label lblVacant = new Label(parent, SWT.NONE);
		lblVacant.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblVacant.setText("Vakant");
		
		Label label = new Label(parent, SWT.NONE);
		label.setImage(ResourceManager.getPluginImage("de.nordakademie.wpk.team2.car2go.ui", "resources/icons/no.png"));
		
		Label lblcomment = new Label(parent, SWT.NONE);
		lblcomment.setText("Bemerkung");
		new Label(parent, SWT.NONE);
		
		txtComment = new Text(parent, SWT.BORDER | SWT.MULTI);
		txtComment.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		// TODO Auto-generated method stub

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
