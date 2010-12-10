package de.nordakademie.wpk.team2.car2go.core.businessobjects;

import de.nordakademie.wpk.team2.car2go.core.interfaces.IGeoPoint;

/**
 * 
 * @author Rumrich, Moehring
 *
 */
public class GeoPoint implements IGeoPoint {
	private static final long serialVersionUID = 1L;
	private float longitude;
	private float latitude;
	
	public GeoPoint(float longitude, float latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public float getLongitude() {
		return longitude;
	}
	
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	public float getLatitude() {
		return latitude;
	}
	
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
}
