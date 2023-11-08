package com.ty.hospital_app_hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.hospital_app_hibernate.entity.Address;
import com.ty.hospital_app_hibernate.entity.Branch;

public class AddressDao {

	public EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ankit");
		return entityManagerFactory.createEntityManager();
	}

	public Address addAddress(Address address, int branch_id) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Branch branch = entityManager.find(Branch.class, branch_id);
		if (branch != null) {
			branch.setAddress(address);
			entityTransaction.begin();
			entityManager.persist(address);
			entityManager.merge(branch);
			entityTransaction.commit();
		}

		return address;
	}

	public Address findAddressById(Branch branch) {
		EntityManager entityManager = getEntityManager();
		Branch branch2 = entityManager.find(Branch.class, branch.getId());

		if (branch2 != null) {
			Address address = branch2.getAddress();
			return address;
		} else {
			return null;
		}
	}

	public void removeAddress(Address address) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Address address2 = entityManager.find(Address.class, address.getId());
		if (address2 != null) {
			entityTransaction.begin();
			entityManager.remove(address2);
			entityTransaction.commit();
		}
	}

	public Address updateAddress(Address address) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Address address2 = entityManager.find(Address.class, address.getId());
		if (address2 != null) {
			entityTransaction.begin();
			address.setId(address2.getId());
			address.setCountry(address2.getCountry());
			entityManager.merge(address);
			entityTransaction.commit();
			return address;
		} else {
			return null;
		}
	}
}
