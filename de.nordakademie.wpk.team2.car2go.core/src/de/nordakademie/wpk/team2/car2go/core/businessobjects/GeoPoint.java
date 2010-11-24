package de.nordakademie.wpk.team2.car2go.core.businessobjects;

public class GeoPoint {
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
