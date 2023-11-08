package com.ty.hospital_app_hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.hospital_app_hibernate.entity.Encounter;
import com.ty.hospital_app_hibernate.entity.Item;
import com.ty.hospital_app_hibernate.entity.MedOrders;

public class MedOrdersDao {

	public EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ankit");
		return entityManagerFactory.createEntityManager();
	}

	public MedOrders saveMedOrders(MedOrders medOrders, int encounter_id) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Encounter encounter = entityManager.find(Encounter.class, encounter_id);
		if (encounter != null) {
			entityTransaction.begin();
			List<MedOrders> list = encounter.getMedOrders();
			List<Item> items = medOrders.getItems();
			for (Item item : items) {
				entityManager.persist(item);
			}
			entityManager.persist(medOrders);
			list.add(medOrders);
			encounter.setMedOrders(list);
			entityManager.merge(encounter);
			entityTransaction.commit();
		}
		return medOrders;
	}

	public MedOrders findMedOrdersById(MedOrders medOrders) {
		EntityManager entityManager = getEntityManager();
		MedOrders medOrders2 = entityManager.find(MedOrders.class, medOrders.getOrder_id());
		if (medOrders2 != null) {
			return medOrders2;
		} else {
			return null;
		}

	}

	public MedOrders updateMedOrders(MedOrders medOrders) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		MedOrders medOrders2 = entityManager.find(MedOrders.class, medOrders.getOrder_id());
		if (medOrders2 != null) {
			entityTransaction.begin();
			medOrders.setOrder_id(medOrders2.getOrder_id());
			medOrders.setItems(medOrders2.getItems());
			entityManager.merge(medOrders);
			entityTransaction.commit();
			return medOrders;
		} else {
			return null;
		}
	}

	public void removeMedOrders(MedOrders medOrders) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		MedOrders medOrders2 = entityManager.find(MedOrders.class, medOrders.getOrder_id());
		if (medOrders2 != null) {
			entityTransaction.begin();
			List<Item> items = medOrders.getItems();
			for (Item item : items) {
				entityManager.persist(item);
			}
			entityManager.remove(medOrders2);
			entityTransaction.commit();
		}
	}
}
