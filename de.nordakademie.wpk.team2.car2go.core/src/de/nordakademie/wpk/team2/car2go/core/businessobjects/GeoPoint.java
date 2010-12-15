package de.nordakademie.wpk.team2.car2go.core.businessobjects;

import de.nordakademie.wpk.team2.car2go.core.interfaces.IGeoPoint;

/**
 * Is the implementation of IGeoPoint and represents a Geocoordinate
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.nordakademie.wpk.team2.car2go.core.interfaces.IGeoPoint#getLatitude()
	 */
	@Override
	public float getLatitude() {
		return latitude;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.nordakademie.wpk.team2.car2go.core.interfaces.IGeoPoint#getLongitude()
	 */
	@Override
	public float getLongitude() {
		return longitude;
	}

	/**
	 * Sets the latitude
	 * 
	 * @param latitude
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	/**
	 * Sets the longitude
	 * 
	 * @param longitude
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
}
