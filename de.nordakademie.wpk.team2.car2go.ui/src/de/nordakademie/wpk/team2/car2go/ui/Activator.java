package de.nordakademie.wpk.team2.car2go.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import de.nordakademie.wpk.team2.car2go.core.interfaces.ICarService;
import de.nordakademie.wpk.team2.car2go.ui.exceptions.ServiceNotAvailableException;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "de.nordakademie.wpk.team2.car2go.ui"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Receive the CarService interface from the Server
	 * 
	 * @throws ServiceNotAvailableException
	 * 
	 * @retun reference to the CarServer
	 */
	public ICarService getCarService() throws ServiceNotAvailableException {

		try {
			ServiceReference serviceReference = this.getBundle()
					.getBundleContext()
					.getServiceReference(ICarService.class.getName());

			ICarService ics = (ICarService) this.getBundle().getBundleContext()
					.getService(serviceReference);
			if (ics == null) {
				throw new ServiceNotAvailableException();
			}
			return ics;

		} catch (Exception e) {
			throw new ServiceNotAvailableException();
		}
	}
}
