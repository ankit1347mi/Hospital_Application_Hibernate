package com.ty.hospital_app_hibernate.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Encounter {
	@Id
	private int id;

	private String name;
	@ManyToOne
	private Person person;
	@OneToMany
	private List<MedOrders> medOrders;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<MedOrders> getMedOrders() {
		return medOrders;
	}

	public void setMedOrders(List<MedOrders> medOrders) {
		this.medOrders = medOrders;
	}

	@Override
	public String toString() {
		return "Encounter [id=" + id + ", name=" + name + ", person=" + person + ", medOrders=" + medOrders + "]";
	}
	
	
}
