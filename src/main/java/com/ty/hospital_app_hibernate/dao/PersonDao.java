package com.ty.hospital_app_hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.hospital_app_hibernate.entity.Encounter;
import com.ty.hospital_app_hibernate.entity.MedOrders;
import com.ty.hospital_app_hibernate.entity.Person;

public class PersonDao {

	public EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ankit");
		return entityManagerFactory.createEntityManager();
	}

	public Person addPerson(Person person) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(person);
		entityTransaction.commit();
		return person;
	}

	public Person findById(Person person) {
		EntityManager entityManager = getEntityManager();
		Person person2 = entityManager.find(Person.class, person.getId());
		if (person2 != null) {
			return person2;
		} else {
			return null;
		}
	}

	public void removePerson(Person person) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Query query = entityManager.createQuery("Select e from Encounter e");
		List<MedOrders> medOrders = null;

		List<Encounter> encounter = query.getResultList();
		entityTransaction.begin();
		for (Encounter encounter2 : encounter) {
			if (encounter2.getPerson().getId() == person.getId()) {
				medOrders = encounter2.getMedOrders();
				for (MedOrders medOrder : medOrders) {
					medOrders.remove(medOrder);

				}

				encounter2.setMedOrders(medOrders);
				encounter.remove(encounter2);
			} else {
				encounter.add(encounter2);
			}

		}
		for (Encounter encounter2 : encounter) {
			entityManager.merge(encounter2);
		}

		entityManager.remove(person);
		entityTransaction.commit();

	}

	public Person updatePerson(Person person) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Person person2 = entityManager.find(Person.class, person.getId());
		if (person2 != null) {
			entityTransaction.begin();
			person.setId(person2.getId());
			entityManager.merge(person);
			entityTransaction.commit();
			return person;
		} else {
			return null;
		}
	}
}
