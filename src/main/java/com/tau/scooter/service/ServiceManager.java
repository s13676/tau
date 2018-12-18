package com.tau.scooter.service;

import java.util.List;

import com.tau.scooter.domain.Person;
import com.tau.scooter.domain.Scooter;

public interface ServiceManager {
	void addScooter(Scooter scooter);
	List<Scooter> getScooters();
	Scooter findScooterById(Long id);
	List<Scooter> findScootersByColorLike(String color);
	void deleteScooter(Scooter scooter);
	void updateScooter(Scooter scooter);

	void addScooterPerson(Long personId, Long scooterId);
	void removeScooterPerson(Long personId, Long scooterId);

	void addPerson(Person person);
	List<Person> getPersons();
	Person findPersonById(Long id);
	void deletePerson(Person Person);
	void updatePerson(Person Person);
}
