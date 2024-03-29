package de.nordakademie.wpk.team2.car2go.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.ui.handlers.HandlerUtil;

import de.nordakademie.wpk.team2.car2go.core.exception.DuplicateBookmarkException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalRegistrationNumberException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalUsernameException;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICar;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICarService;
import de.nordakademie.wpk.team2.car2go.ui.Activator;
import de.nordakademie.wpk.team2.car2go.ui.views.Car2goView;

/**
 * Bookmark Handler for the tree
 * 
 * @author: Alexander Westen, Matthias L�ders
 */
public class AddBookmarkHandler extends AbstractHandler {

	/**
	 * This method bookmarks the selected car for the registered user
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection currentSelection = (IStructuredSelection) HandlerUtil
				.getCurrentSelection(event);
		if (!currentSelection.isEmpty()) {
			TreeNode node = (TreeNode) currentSelection.getFirstElement();

			if (!(node.getValue() instanceof ICar)) {
				System.out.println("AddBookmarkHandler: No car selected!");
				return null;
			}
			ICar car = (ICar) node.getValue();
			System.out.println("Selected Car:" + car.getRegistrationNumber());

			// get a reference to the view
			Car2goView view = (Car2goView) HandlerUtil.getActiveSite(event)
					.getPage().findView(Car2goView.ID);

			// popup an errorMessage when the user is'nt signed in
			if (!view.getUser().isSignIn()) {
				view.errorMessage("Sie m�ssen angemeldet sein.");
				return null;
			}

			// get the ICarService
			ICarService ics = Activator.getDefault().getCarService();

			// try to bookmark
			try {
				ics.addToBookmark(car.getRegistrationNumber(), view.getUser()
						.getUsername());
				view.refresh();
			} catch (RegistrationNumberNotFoundException e) {
				view.errorMessage("Auto wurde nicht auf dem Server gefunden");
			} catch (IllegalRegistrationNumberException e) {
				view.errorMessage("Ung�ltiges Nummernschild");
			} catch (DuplicateBookmarkException e) {
			} catch (IllegalUsernameException e) {
				view.errorMessage("Ung�ltiger Username");
			} catch (Exception e) {
				view.errorMessage("Der Server ist nicht erreichbar.");
			}
		} else {
			System.out.println("Nothing selected");
		}
		return null;
	}
}
