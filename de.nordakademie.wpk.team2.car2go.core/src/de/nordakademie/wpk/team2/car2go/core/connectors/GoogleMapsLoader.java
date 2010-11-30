package de.nordakademie.wpk.team2.car2go.core.connectors;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import de.nordakademie.wpk.team2.car2go.core.businessobjects.ICar;

public class GoogleMapsLoader {
	// Verwenden der Maps: new Image(Display.getCurrent(), new ByteArrayInputStream(new GoogleMapsLoader().getMapForCar(null)));
	public byte[] getMapForCar(ICar car) {
		try {
			URL url = new URL("http://maps.google.com/maps/api/staticmap?center=Berkeley,CA&zoom=14&size=400x400&sensor=false");
			InputStream openStream = url.openStream();
			
			int tmpRead = 0;
			ByteArrayOutputStream urlDataStream = new ByteArrayOutputStream();
			
			while((tmpRead = openStream.read()) != -1) {
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
}
