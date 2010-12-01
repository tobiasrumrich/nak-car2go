package de.nordakademie.wpk.team2.car2go.core.businessobjects;

public interface ICar {
	/**
	 * Returns the description as offered by the REST service or the value that has been set using setDescription()
	 */
	public String getDescription();
	/**
	 * Returns the description of the car as a string
	 * @return String the registration number of the car
	 */
	public String getRegistrationNumber();
	
	/**
	 * Returns the fuel state of the car in liters. The values may be rounded.
	 * @return int the fuel state
	 */
	public int getFuelState();
	
	/**
	 * Returns the state of the cars interior as an EState Enum
	 * @return EState state of the interior
	 */
	public EState getInteriorState();
	
	/**
	 * Returns the state of the cars exterior as an EState Enum
	 * @return EState state of the exterior
	 */
	public EState getExteriorState();
	
	/**
	 * Returs true if the car is vacant, and false if not
	 * @return Boolean 
	 */
	public Boolean getVacantState();
	
	/**
	 * Returns an adress that is close to the place where the car is parked.
	 * @return String address
	 */
	public String getLocation();
	
	/**
	 * Sets the description of the car. If used this overrides the description provided by the REST service
	 * @param description
	 */
	public void setDescription(String description);
	
	/**
	 * Returns the exact GPS position of the car as GeoPoint
	 * @return GeoPoint with the cars position
	 */
	public GeoPoint getCoordinates();
}
