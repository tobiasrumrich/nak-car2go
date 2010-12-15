package de.nordakademie.wpk.team2.car2go.core.interfaces;

import java.io.Serializable;

/**
 * Interface for an GeoPoint which represents a Geo-Coordinate
 * 
 * @author Moehring, Rumrich
 * 
 */
public interface IGeoPoint extends Serializable {
	/**
	 * Returns the longitude
	 * 
	 * @return float with the longitude
	 */
	public float getLongitude();

	/**
	 * Returns the latitude
	 * 
	 * @return float with the latitude
	 */
	public float getLatitude();
}
