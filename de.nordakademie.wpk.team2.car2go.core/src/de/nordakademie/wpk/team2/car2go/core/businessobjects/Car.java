package de.nordakademie.wpk.team2.car2go.core.businessobjects;

import de.nordakademie.wpk.team2.car2go.core.interfaces.EState;
import de.nordakademie.wpk.team2.car2go.core.interfaces.ICar;

/**
 * Is the implementation of ICar and represents a car of the car2go company
 * 
 * @author Moehring, Rumrich
 * 
 */
public class Car implements ICar {
	private static final long serialVersionUID = 1L;
	private String comment;
	private String registrationNumber;
	private int fuelState;
	private EState interiorState;
	private EState exteriorState;
	private Boolean vacantState;
	private String location;
	private GeoPoint coordinates;

	public Car() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (registrationNumber == null) {
			if (other.registrationNumber != null)
				return false;
		} else if (!registrationNumber
				.equalsIgnoreCase(other.registrationNumber))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.nordakademie.wpk.team2.car2go.core.interfaces.ICar#getComment()
	 */
	@Override
	public String getComment() {
		return comment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.nordakademie.wpk.team2.car2go.core.interfaces.ICar#getCoordinates()
	 */
	@Override
	public GeoPoint getCoordinates() {
		return coordinates;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.nordakademie.wpk.team2.car2go.core.interfaces.ICar#getExteriorState()
	 */
	@Override
	public EState getExteriorState() {
		return exteriorState;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.nordakademie.wpk.team2.car2go.core.interfaces.ICar#getFuelState()
	 */
	@Override
	public int getFuelState() {
		return fuelState;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.nordakademie.wpk.team2.car2go.core.interfaces.ICar#getInteriorState()
	 */
	@Override
	public EState getInteriorState() {
		return interiorState;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.nordakademie.wpk.team2.car2go.core.interfaces.ICar#getLocation()
	 */
	@Override
	public String getLocation() {
		return location;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.nordakademie.wpk.team2.car2go.core.interfaces.ICar#getRegistrationNumber
	 * ()
	 */
	@Override
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.nordakademie.wpk.team2.car2go.core.interfaces.ICar#getVacantState()
	 */
	@Override
	public Boolean getVacantState() {
		return vacantState;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((registrationNumber == null) ? 0 : registrationNumber
						.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.nordakademie.wpk.team2.car2go.core.interfaces.ICar#setComment(java
	 * .lang.String)
	 */
	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Sets the location coordinates of the car
	 * 
	 * @param coordinates
	 *            GeoPoint with the position of the car
	 */
	public void setCoordinates(GeoPoint coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * Sets the exterior state of the car
	 * 
	 * @param exteriorState
	 *            EState with the exterior state of the car
	 */
	public void setExteriorState(EState exteriorState) {
		this.exteriorState = exteriorState;
	}

	/**
	 * Sets the fuel state of the car
	 * 
	 * @param fuelState
	 *            int with the fuel state in liters
	 */
	public void setFuelState(int fuelState) {
		this.fuelState = fuelState;
	}

	/**
	 * Sets the interior state of the car
	 * 
	 * @param interiorState
	 *            EState with the interior state of the car
	 */
	public void setInteriorState(EState interiorState) {
		this.interiorState = interiorState;
	}

	/**
	 * Sets the location of the car
	 * 
	 * @param location
	 *            String with the location (nearby address) of the car
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Sets the registration number of the car
	 * 
	 * @param registrationNumber
	 *            String with the new registration number
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.nordakademie.wpk.team2.car2go.core.interfaces.ICar#setVacantState(
	 * java.lang.Boolean)
	 */
	@Override
	public void setVacantState(Boolean vacantState) {
		this.vacantState = vacantState;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Car [description=" + comment + ", registrationNumber="
				+ registrationNumber + ", fuelState=" + fuelState
				+ ", interiorState=" + interiorState + ", exteriorState="
				+ exteriorState + ", vacantState=" + vacantState
				+ ", location=" + location + ", coordinates=" + coordinates
				+ "]";
	}

}
