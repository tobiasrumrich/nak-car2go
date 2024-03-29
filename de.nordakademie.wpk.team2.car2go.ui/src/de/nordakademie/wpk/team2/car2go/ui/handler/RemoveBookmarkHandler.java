package de.nordakademie.wpk.team2.car2go.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.ui.handlers.HandlerUtil;

import de.nordakademie.wpk.team2.car2go.core.exception.IllegalRegistrationNumberException;
import de.nordakademie.wpk.team2.car2go.core.exception.IllegalUsernameException;
import de.nordakademie.wpk.team2.car2go.core.exception.RegistrationNumberNotFoundException;
import de.nordakademie.wpk.team2.car2go.core.exception.UsernameNotFoundException;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICar;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICarService;
import de.nordakademie.wpk.team2.car2go.ui.Activator;
import de.nordakademie.wpk.team2.car2go.ui.views.Car2goView;

/**
 * RemoveBookmarkHandler
 * 
 * @author: Alexander Westen, Matthias L�ders
 */
public class RemoveBookmarkHandler extends AbstractHandler {

	/**
	 * This method removes the bookmark from an selected car for the registered
	 * user
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection currentSelection = (IStructuredSelection) HandlerUtil
				.getCurrentSelection(event);
		if (!currentSelection.isEmpty()) {
			TreeNode node = (TreeNode) currentSelection.getFirstElement();

			if (!(node.getValue() instanceof ICar)) {
				System.out.println("RemoveBookmarkHandler: No car selected!");
				return null;
			}
			ICar car = (ICar) node.getValue();
			System.out.println("Selected Car:" + car.getRegistrationNumber());

			// view reference
			Car2goView view = (Car2goView) HandlerUtil.getActiveSite(event)
					.getPage().findView(Car2goView.ID);

			if (!view.getUser().isSignIn()) {
				view.errorMessage("Sie m�ssen angemeldet sein.");
				return null;
			}

			// get the ICarService
			ICarService ics = Activator.getDefault().getCarService();

			// try to remove the bookmark
			try {
				ics.removeFromBookmark(car.getRegistrationNumber(), view
						.getUser().getUsername());
			} catch (RegistrationNumberNotFoundException e) {
			} catch (IllegalRegistrationNumberException e) {
				view.errorMessage("Ung�ltiges Nummernschild");
			} catch (IllegalUsernameException e) {
				view.errorMessage("Ung�ltiger Username");
			} catch (UsernameNotFoundException e) {
				view.errorMessage("Ung�ltiger Username");
			} catch (Exception e) {
				view.errorMessage("Der Server ist nicht erreichbar.");
			}
			view.refresh();
		} else {
			System.out.println("Nothing selected");
		}
		return null;
	}
}
