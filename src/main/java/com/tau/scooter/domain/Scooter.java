package com.tau.scooter.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
		@NamedQuery(name = "scooter.all", query = "Select s from Scooter s")
})
public class Scooter {
	private Long id;

	private String model;
	private String brand;
	private int productionYear;
	private String color;
	private List<Person> persons;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}


	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public int getProductionYear() {
		return productionYear;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
	@ElementCollection(targetClass=Person.class)
	public List<Person> getPersons() {
		return persons;
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
}
