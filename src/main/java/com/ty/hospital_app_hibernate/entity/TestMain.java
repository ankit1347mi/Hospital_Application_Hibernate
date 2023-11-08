package com.ty.hospital_app_hibernate.entity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestMain {

	public static void main(String[] args) {
		String querry = "SELECT b FROM Branch b WHERE b.encounters.id=31";
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ankit");
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery(querry);
		List res = query.getResultList();
		if (res != null) {
			System.out.println(res.get(0));
		}

	}

}
