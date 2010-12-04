package de.nordakademie.wpk.team2.car2go.core.businessobjects;

import de.nordakademie.wpk.team2.car2go.interfaces.ICar;

/**
 * 
 * @author Rumrich, Moehring
 *
 */
public class Car implements ICar {
	private String description;
	private String registrationNumber;
	private int fuelState;
	private EState interiorState;
	private EState exteriorState;
	private Boolean vacantState;
	private String location;
	private GeoPoint coordinates;
	
	public GeoPoint getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(GeoPoint coordinates) {
		this.coordinates = coordinates;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	public int getFuelState() {
		return fuelState;
	}
	
	public void setFuelState(int fuelState) {
		this.fuelState = fuelState;
	}
	
	public EState getInteriorState() {
		return interiorState;
	}
	
	public void setInteriorState(EState interiorState) {
		this.interiorState = interiorState;
	}
	
	public EState getExteriorState() {
		return exteriorState;
	}
	
	public void setExteriorState(EState exteriorState) {
		this.exteriorState = exteriorState;
	}
	
	public Boolean getVacantState() {
		return vacantState;
	}
	
	public void setVacantState(Boolean vacantState) {
		this.vacantState = vacantState;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "Car [description=" + description + ", registrationNumber="
				+ registrationNumber + ", fuelState=" + fuelState
				+ ", interiorState=" + interiorState + ", exteriorState="
				+ exteriorState + ", vacantState=" + vacantState
				+ ", location=" + location + ", coordinates=" + coordinates
				+ "]";
	}

	/* (non-Javadoc)
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

	/* (non-Javadoc)
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
		} else if (!registrationNumber.equals(other.registrationNumber))
			return false;
		return true;
	}
	
	
	
	
}
