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

public class CarEditor extends EditorPart {
	private Text txtRegistrationNumber;
	private Text txtFuelState;
	private Text txtExteriorState;
	private Text txtInteriorState;
	private Text txtGeoPoint;
	private Text txtLocation;
	private Text text_8;

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
		txtRegistrationNumber.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblFuelState = new Label(parent, SWT.NONE);
		lblFuelState.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuelState.setText("Tankstand");
		
		txtFuelState = new Text(parent, SWT.BORDER);
		txtFuelState.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblExteriorState = new Label(parent, SWT.NONE);
		lblExteriorState.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblExteriorState.setText("Au\u00DFenzustand");
		
		txtExteriorState = new Text(parent, SWT.BORDER);
		txtExteriorState.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblInteriorState = new Label(parent, SWT.NONE);
		lblInteriorState.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblInteriorState.setText("Innenzustand");
		
		txtInteriorState = new Text(parent, SWT.BORDER);
		txtInteriorState.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblGeoPoint = new Label(parent, SWT.NONE);
		lblGeoPoint.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblGeoPoint.setText("Aktuelle Geokoordinate");
		
		txtGeoPoint = new Text(parent, SWT.BORDER);
		txtGeoPoint.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblLocation = new Label(parent, SWT.NONE);
		lblLocation.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLocation.setText("Aktueller Standort");
		
		txtLocation = new Text(parent, SWT.BORDER);
		txtLocation.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblVacant = new Label(parent, SWT.NONE);
		lblVacant.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblVacant.setText("Vakant");
		new Label(parent, SWT.NONE);
		
		Label label_7 = new Label(parent, SWT.NONE);
		label_7.setText("New Label");
		new Label(parent, SWT.NONE);
		
		Label label_8 = new Label(parent, SWT.NONE);
		label_8.setText("New Label");
		new Label(parent, SWT.NONE);
		
		text_8 = new Text(parent, SWT.BORDER);
		text_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		// TODO Auto-generated method stub

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
