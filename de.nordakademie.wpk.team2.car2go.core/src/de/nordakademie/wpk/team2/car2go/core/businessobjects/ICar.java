package de.nordakademie.wpk.team2.car2go.core.businessobjects;

public interface ICar {
	public String getDescription();
	public String getRegistrationNumber();
	public int getFuelState();
	public EState getInteriorState();
	public EState getExteriorState();
	public Boolean getVacantState();
	public String getLocation();
	public void setDescription(String description);
	public GeoPoint getCoordinates();
}
