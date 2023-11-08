package com.ty.hospital_app_hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.hospital_app_hibernate.entity.Address;
import com.ty.hospital_app_hibernate.entity.Item;

public class ItemDao {

	public EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ankit");
		return entityManagerFactory.createEntityManager();
	}

	public Item addItem(Item item) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(item);
		entityTransaction.commit();
		return item;
	}

	public Item findItemById(Item item) {
		EntityManager entityManager = getEntityManager();
		Item item2 = entityManager.find(Item.class, item.getId());
		if (item2 != null) {
			return item2;
		}else {
			return null;
		}
	}

	public void removeItem(Item item) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Item item2 = entityManager.find(Item.class, item.getId());
		if (item2 != null) {
			entityTransaction.begin();
			entityManager.remove(item2);
			entityTransaction.commit();
		}
	}

	public Item updateItem(Item item) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Item item2 = entityManager.find(Item.class, item.getId());
		if (item2 != null) {
			entityTransaction.begin();
			item.setId(item2.getId());
			entityManager.merge(item);
			entityTransaction.commit();
			return item;
		}else {
			return null;
		}
	}
}
