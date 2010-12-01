package de.nordakademie.wpk.team2.car2go.core.connectors;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import de.nordakademie.wpk.team2.car2go.core.businessobjects.ICar;

public class GoogleMapsLoader {
	// Verwenden der Maps: new Image(Display.getCurrent(), new
	// ByteArrayInputStream(new GoogleMapsLoader().getMapForCar(null)));
	public byte[] getMapForCar(ICar car, int width, int height, int zoom) {
		try {
			InputStream openStream = this.getUrlFromCar(car, width, height, zoom).openStream();

			int tmpRead = 0;
			ByteArrayOutputStream urlDataStream = new ByteArrayOutputStream();

			while ((tmpRead = openStream.read()) != -1) {
				urlDataStream.write(tmpRead);
			}

			return urlDataStream.toByteArray();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// TODO Exception-Handling überdenken
		return null;
	}

	private URL getUrlFromCar(ICar car, int width, int height, int zoom)
			throws MalformedURLException {
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
