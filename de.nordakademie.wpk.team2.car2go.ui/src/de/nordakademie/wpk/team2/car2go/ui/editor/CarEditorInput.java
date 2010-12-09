package de.nordakademie.wpk.team2.car2go.ui.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import de.nordakademie.wpk.team2.car2go.core.interfaces.ICar;


/**
 * CarEditorInput provides the Car for the CarEditor.
 * 
 * @author: Alexander Westen, Matthias Lüders
 */
public class CarEditorInput implements IEditorInput {

	private ICar car;

	public CarEditorInput(ICar car) {
		super();
		this.car = car;
	}

	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return car.getRegistrationNumber();
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return car.getRegistrationNumber();
	}

	public ICar getCar() {
		return car;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof CarEditorInput) {
			CarEditorInput otherInput = (CarEditorInput) object;
			return otherInput.getCar().getRegistrationNumber()
					.equals(car.getRegistrationNumber());
		}
		return false;
	}

}
