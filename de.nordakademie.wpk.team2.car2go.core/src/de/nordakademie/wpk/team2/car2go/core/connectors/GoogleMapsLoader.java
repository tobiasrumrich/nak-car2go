package de.nordakademie.wpk.team2.car2go.core.connectors;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import de.nordakademie.wpk.team2.car2go.core.exception.MapRetrievalException;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICar;

/**
 * This class is an connexion to Google Maps, that allows to retrieve an map
 * extract from Google Maps for the current position of an ICar.
 * 
 * 
 * @author Moehring, Rumrich
 * 
 */
public class GoogleMapsLoader {
	private static final Logger logger = Logger
			.getLogger(GoogleMapsLoader.class);

	/**
	 * Returns a Bitmap with an map extract for the current position of the ICar provided
	 * 
	 * @param car is the ICar object
	 * @param width is the width of the map extract
	 * @param height is the height of the map extract
	 * @param zoom is the zoom factor of the map extract
	 * @return byte[] is the requested map extract as a bitmap
	 * @throws MapRetrievalException is thrown if the loader was unable to retrieve the map extract from Google Maps
	 */
	public byte[] getMapForCar(ICar car, int width, int height, int zoom)
			throws MapRetrievalException {
		logger.info("Loading map from google for car " + car);

		if (car == null || width <= 10 || height <= 10 || zoom <= 0) {
			throw new MapRetrievalException();
		}

		try {
			InputStream openStream = this.getUrlFromCar(car, width, height,
					zoom).openStream();

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

		throw new MapRetrievalException();
	}

	/**
	 * Returns the URL that can be used to retrieve a specific map extract from Google Maps
	 * 
	 * @param car is the ICar object
	 * @param width is the width of the map extract
	 * @param height is the height of the map extract
	 * @param zoom is the zoom factor of the map extract
	 * @return URL is the URL that can be used to retrieve the map extract image from Google
	 * @throws MalformedURLException if the URL cannot be built
	 */
	private URL getUrlFromCar(ICar car, int width, int height, int zoom)
			throws MalformedURLException {
		logger.info("Generating Google-URL for car with registraionnumber "
				+ car);

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
