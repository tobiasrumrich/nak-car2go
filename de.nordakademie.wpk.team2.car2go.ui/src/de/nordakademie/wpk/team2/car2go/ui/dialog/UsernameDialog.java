package de.nordakademie.wpk.team2.car2go.ui.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.ResourceManager;

import de.nordakademie.wpk.team2.car2go.ui.views.UserBean;

public class UsernameDialog extends TitleAreaDialog {
	private Text txtUsername;
	private UserBean user;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public UsernameDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.BORDER);
		setHelpAvailable(false);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 *            - parent Composite
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Bitte geben Sie Ihren Benutzernamen ein, um auf ihre gemerkten Car2Go's zugreifen zu k\u00F6nnen.");
		parent.setToolTipText("");
		setTitleImage(ResourceManager.getPluginImage(
				"de.nordakademie.wpk.team2.car2go.ui",
				"resources/icons/car2go-100x100.png"));
		setTitle("Car2Go Anmeldung");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		GridData gd_container = new GridData(GridData.FILL_BOTH);
		gd_container.heightHint = 52;
		container.setLayoutData(gd_container);

		Label lblUsername = new Label(container, SWT.NONE);
		lblUsername.setText("Benutzername:");

		txtUsername = new Text(container, SWT.BORDER);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text.minimumWidth = 200;
		txtUsername.setLayoutData(gd_text);
		txtUsername.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				user.setUsername(txtUsername.getText());
			}
		});

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		okButton.setText("Anmelden");
		
		Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		cancelButton.setText("Ohne Anmeldung");
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 210);
	}

	public void setUserBean(UserBean user) {
		this.user = user;
	}
}
