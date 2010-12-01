package de.nordakademie.wpk.team2.car2go.ui.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import com.swtdesigner.ResourceManager;

public class UsernameDialog extends TitleAreaDialog {
	private Text text;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public UsernameDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.BORDER);
		setHelpAvailable(false);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitleImage(ResourceManager.getPluginImage("de.nordakademie.wpk.team2.car2go.ui", "resources/icons/car2go-100x100.png"));
		setTitle("Bitte geben Sie Ihren Benutzernamen an");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		GridData gd_container = new GridData(GridData.FILL_BOTH);
		gd_container.heightHint = 52;
		container.setLayoutData(gd_container);
		
		Label lblUsername = new Label(container, SWT.NONE);
		lblUsername.setText("Benutzername:");
		
		text = new Text(container, SWT.BORDER);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text.minimumWidth = 200;
		text.setLayoutData(gd_text);

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 210);
	}
}
