package com.ty.hospital_app_hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.hospital_app_hibernate.entity.Branch;
import com.ty.hospital_app_hibernate.entity.Encounter;
import com.ty.hospital_app_hibernate.entity.MedOrders;

public class EncounterDao {

	public EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ankit");
		return entityManagerFactory.createEntityManager();
	}

	public Encounter saveEncounter(Encounter encounter, int branchid) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Branch branch = entityManager.find(Branch.class, branchid);
		if (branch != null) {
			List<Encounter> enlist = branch.getEncounters();
			entityTransaction.begin();
			entityManager.persist(encounter);
			entityManager.persist(encounter.getPerson());
			List<MedOrders> list = encounter.getMedOrders();
			for (MedOrders medOrders : list) {
				entityManager.persist(medOrders);
			}
			enlist.add(encounter);
			branch.setEncounters(enlist);
			entityManager.merge(branch);
			entityTransaction.commit();
		}

		return encounter;
	}

	public Encounter findEncounterById(Encounter encounter) {
		EntityManager entityManager = getEntityManager();
		Encounter encounter2 = entityManager.find(Encounter.class, encounter.getId());
		if (encounter2 != null) {
			return encounter2;
		} else {
			return null;
		}

	}

	public Encounter updateEncounter(Encounter encounter) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Encounter encounter2 = entityManager.find(Encounter.class, encounter.getId());
		if (encounter2 != null) {
			entityTransaction.begin();
			encounter.setMedOrders(encounter2.getMedOrders());
			encounter.setPerson(encounter2.getPerson());
			encounter.setId(encounter2.getId());
			entityManager.merge(encounter);
			entityTransaction.commit();
			return encounter;
		} else {
			return null;
		}
	}

	public void removeEncounter(Encounter encounter) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Encounter encounter2 = entityManager.find(Encounter.class, encounter.getId());
		if (encounter2 != null) {
			entityTransaction.begin();
			List<MedOrders> list = encounter.getMedOrders();
			for (MedOrders medOrders : list) {
				entityManager.remove(medOrders);
			}
			entityManager.remove(encounter.getPerson());
			entityManager.remove(encounter);
			entityTransaction.commit();
		}

	}
}
