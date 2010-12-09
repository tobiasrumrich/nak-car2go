package de.nordakademie.wpk.team2.car2go.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import de.nordakademie.wpk.team2.car2go.core.interfaces.ICar;
import de.nordakademie.wpk.team2.car2go.ui.editor.CarEditor;
import de.nordakademie.wpk.team2.car2go.ui.editor.CarEditorInput;
import de.nordakademie.wpk.team2.car2go.ui.views.Car2goView;

/**
 * CarEditHandler
 * 
 * @author: Alexander Westen, Matthias Lüders
 */
public class CarEditHandler extends AbstractHandler {

	/**
	 * This method calls the CarEditor for the selected car.
	 * 
	 * @Author: Alexander Westen, Matthias Lüders
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection currentSelection = (IStructuredSelection) HandlerUtil
				.getCurrentSelection(event);
		if (!currentSelection.isEmpty()) {
			if (!(currentSelection.getFirstElement() instanceof ICar)) {
				System.out.println("CarEditHandler: No car selected!");
				return null;
			}
			ICar car = (ICar) currentSelection.getFirstElement();
			System.out.println("Selected Car:" + car.getRegistrationNumber());
			CarEditorInput carEditorInput = new CarEditorInput(car);
			try {
				final IEditorPart openEditor = HandlerUtil.getActiveSite(event)
						.getPage().openEditor(carEditorInput, CarEditor.ID);
				openEditor.addPropertyListener(new IPropertyListener() {

					@Override
					public void propertyChanged(Object source, int propId) {
						if (propId == CarEditor.PROP_SAVED) {
							Car2goView view = (Car2goView) openEditor.getSite()
									.getPage().findView(Car2goView.ID);
							if (view != null) {
								view.refresh();
							}
						}
					}
				});
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Nothing selected");
		}
		return null;
	}

}
