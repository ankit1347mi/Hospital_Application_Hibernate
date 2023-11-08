package com.ty.hospital_app_hibernate.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.hospital_app_hibernate.entity.Address;
import com.ty.hospital_app_hibernate.entity.Branch;
import com.ty.hospital_app_hibernate.entity.Encounter;
import com.ty.hospital_app_hibernate.entity.Hospital;
import com.ty.hospital_app_hibernate.entity.Item;
import com.ty.hospital_app_hibernate.entity.MedOrders;
import com.ty.hospital_app_hibernate.entity.Person;

public class HospitalDao {

	public EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ankit");
		return entityManagerFactory.createEntityManager();
	}

	public Hospital saveHospital(Hospital hospital) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		List<Branch> branchs = hospital.getBranchs();
		entityTransaction.begin();
		Address address = null;
		Person person = null;
		List<MedOrders> medOrders = null;
		List<Encounter> encounters = null;
		for (Branch branch : branchs) {
			address = branch.getAddress();
			encounters = branch.getEncounters();
			entityManager.persist(branch);
		}

		for (Encounter encounter : encounters) {
			medOrders = encounter.getMedOrders();
			person = encounter.getPerson();
			entityManager.persist(encounter);
		}
		List<Item> items = null;
		for (MedOrders mOrders : medOrders) {
			items = mOrders.getItems();
			entityManager.persist(mOrders);
		}
		for (Item item : items) {
			entityManager.persist(item);
		}

		entityManager.persist(person);
		entityManager.persist(address);

		entityManager.persist(hospital);
		entityTransaction.commit();
		return hospital;
	}

	public void saveHospitalOnly(Hospital hospital) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		List<Branch> branchs = hospital.getBranchs();
		entityTransaction.begin();
		for (Branch branch : branchs) {
			entityManager.persist(branch);
		}
		entityManager.persist(hospital);
		entityTransaction.commit();
	}

	public Hospital findHospital(Hospital hospital) {
		EntityManager entityManager = getEntityManager();
		Hospital hospital2 = entityManager.find(Hospital.class, hospital.getId());
		if (hospital2 != null) {
			return hospital2;
		} else {
			return null;
		}
	}

	public Hospital updateHospital(Hospital hospital) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Hospital hospital2 = entityManager.find(Hospital.class, hospital.getId());
		if (hospital2 != null) {
			entityTransaction.begin();
			hospital.setBranchs(hospital2.getBranchs());
			hospital.setId(hospital2.getId());
			entityManager.merge(hospital);
			entityTransaction.commit();
			return hospital2;
		} else {
			return null;
		}

	}

	public void removeHospital(int id) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Hospital hospital = entityManager.find(Hospital.class, id);
		List<Branch> branchs = hospital.getBranchs();
		List<Address> addresses = new ArrayList<Address>();
		List<Person> persons = new ArrayList<Person>();
		List<MedOrders> medOrders = new ArrayList<MedOrders>();
		List<Encounter> encounters = new ArrayList<Encounter>();
		List<Item> items = new ArrayList<Item>();
		for (Branch branch : branchs) {
			addresses.add(branch.getAddress());
			encounters = branch.getEncounters();

		}

		for (Encounter encounter : encounters) {
			medOrders = encounter.getMedOrders();
			persons.add(encounter.getPerson());

		}

		for (MedOrders mOrders : medOrders) {
			items = mOrders.getItems();

		}
		entityTransaction.begin();
		for (Item item : items) {
			entityManager.remove(item);
		}
		for (Person person : persons) {
			entityManager.remove(person);
		}
		for (MedOrders orders : medOrders) {
			entityManager.remove(orders);
		}
		for (Address address : addresses) {
			entityManager.remove(address);
		}
		for (Encounter encounter : encounters) {
			entityManager.remove(encounter);
		}
		for (Branch branch : branchs) {
			entityManager.remove(branch);
		}

		entityTransaction.commit();

	}
}
