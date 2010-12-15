package de.nordakademie.wpk.team2.car2go.core.xmldata;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import de.nordakademie.wpk.team2.car2go.core.businessobjects.Car;
import de.nordakademie.wpk.team2.car2go.core.businessobjects.GeoPoint;
import de.nordakademie.wpk.team2.car2go.core.interfaces.EState;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICar;

/**
 * The CarLoader fetches the KML Document from the car2go web interface and
 * provides a List<ICar> of the cars retrieved from the web interface
 * 
 * @author Rumrich, Moehring
 * 
 */
public class CarLoader {
	private static final Logger logger = Logger.getLogger(CarLoader.class);

	/**
	 * Returns all ICar objects that have been created from calling the car2go
	 * web interface
	 * 
	 * @return Set<ICar> contains all ICar objects that have been created from
	 *         calling the car2go web interface
	 */
	public Set<ICar> getCarsFromApi() {
		logger.info("Getting vacant cars from current internetlist");

		Set<ICar> cars = new HashSet<ICar>();

		try {
			Kml kml = this.getXmlObjects();

			logger.info("Converting XML-Data-Structure into Model-Data-Structure.");
			for (Placemark placemark : kml.getDocument().getPlacemark()) {
				Car car = new Car();
				car.setRegistrationNumber(placemark.getName());

				for (Data data : placemark.getExtendedData().getData()) {
					if (data.getName().equalsIgnoreCase("interior")) {
						car.setInteriorState(EState.valueOf(data.getValue()));
					} else if (data.getName().equalsIgnoreCase("exterior")) {
						car.setExteriorState(EState.valueOf(data.getValue()));
					} else if (data.getName().equalsIgnoreCase("fuel")) {
						car.setFuelState(Integer.valueOf(data.getValue()));
					}
				}

				String[] pointSplit = placemark.getPoint().getCoordinates()
						.toString().split(",");
				GeoPoint geoPoint = new GeoPoint(Float.valueOf(pointSplit[0]),
						Float.valueOf(pointSplit[1]));
				car.setCoordinates(geoPoint);

				car.setLocation(placemark.getDescription().split("<br/>")[0]);
				car.setVacantState(true);

				cars.add(car);
			}
		} catch (JAXBException e) {
			logger.info("Unable to fetch KML.");
			e.printStackTrace();
		}

		return cars;
	}

	/**
	 * Retrieves the KML document from the car2go web API and returns an (KML) object tree
	 * @return KML object tree of the KML document returned by the web interface
	 * @throws JAXBException
	 */
	private Kml getXmlObjects() throws JAXBException {
		logger.info("Converting XML-List to Object-List.");

		JAXBContext context;
		context = JAXBContext.newInstance(Kml.class);

		Unmarshaller um = context.createUnmarshaller();
		Kml kml = null;

		try {
			kml = (Kml) um.unmarshal(new URL(
					"http://www.car2go.com/api/V1.0/ulm/vacant"));
		} catch (MalformedURLException e) {
			logger.info("Unable to call Car2Go API! Please contact your local system administrator.");
			e.printStackTrace();
		}

		return kml;
	}
}
