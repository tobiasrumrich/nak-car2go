package de.nordakademie.wpk.team2.car2go.core.connectors;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import de.nordakademie.wpk.team2.car2go.interfaces.ICar;

/**
 * 
 * @author Moehring
 *
 */
public class GoogleMapsLoader {
	private static final Logger logger = Logger.getLogger(GoogleMapsLoader.class);
	
	// Verwenden der Maps: new Image(Display.getCurrent(), new
	// ByteArrayInputStream(new GoogleMapsLoader().getMapForCar(null)));
	public byte[] getMapForCar(ICar car, int width, int height, int zoom) {
		logger.info("Loading map from google for car " + car);
		
		try {
			InputStream openStream = this.getUrlFromCar(car, width, height, zoom).openStream();

			int tmpRead = 0;
			ByteArrayOutputStream urlDataStream = new ByteArrayOutputStream();

			while ((tmpRead = openStream.read()) != -1) {
				urlDataStream.write(tmpRead);
			}

			return urlDataStream.toByteArray();
		} catch (MalformedURLException e) {
			logger.error("Error while generating the URL for car " + car);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Error while generating the URL for car " + car);
			e.printStackTrace();
		}

		return null;
	}

	private URL getUrlFromCar(ICar car, int width, int height, int zoom)
			throws MalformedURLException {
		logger.info("Generating Google-URL for car with registraionnumber " + car);
		
		URL url = new URL(
				"http://maps.google.com/maps/api/staticmap?size="
						+ width
						+ "x"
						+ height
						+ "&markers=icon:http://www.car2go.com/default/img/backgrounds/Marker_car2go.png|"
						+ car.getCoordinates().getLatitude() + ","
						+ car.getCoordinates().getLongitude() + "&center="
						+ car.getCoordinates().getLatitude() + ","
						+ car.getCoordinates().getLongitude() + "&zoom=" + zoom
						+ "&sensor=false");
		return url;
	}
}
