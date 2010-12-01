package de.nordakademie.wpk.team2.car2go.interfaces;

import java.util.Set;

public interface IUpdateableCarStorageOwner {
	public void pushCarStorageUdate(Set<ICar> carsFromApi);
}
