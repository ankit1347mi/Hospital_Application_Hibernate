package com.ty.hospital_app_hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import com.ty.hospital_app_hibernate.entity.Address;
import com.ty.hospital_app_hibernate.entity.Branch;
import com.ty.hospital_app_hibernate.entity.Encounter;
import com.ty.hospital_app_hibernate.entity.Hospital;
import com.ty.hospital_app_hibernate.entity.MedOrders;

public class BranchDao {

	public EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ankit");
		return entityManagerFactory.createEntityManager();
	}

	public Branch saveBranch(Branch branch, int id) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Hospital hospital = entityManager.find(Hospital.class, id);
		if (hospital != null) {
			entityTransaction.begin();
			Address address = branch.getAddress();
			entityManager.persist(address);
			List<Encounter> encounters = branch.getEncounters();
			for (Encounter encounter : encounters) {
				entityManager.persist(encounter);

			}
		}
		entityManager.persist(branch);
		entityTransaction.commit();
		return branch;

	}

	public Branch findBranch(Branch branch) {
		EntityManager entityManager = getEntityManager();
		Branch branch2 = entityManager.find(Branch.class, branch.getId());
		if (branch2 != null) {
			return branch2;
		} else {
			return null;
		}
	}

	public Branch updateBranch(Branch branch) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Branch branch2 = entityManager.find(Branch.class, branch.getId());
		if (branch2 != null) {
			entityTransaction.begin();
			branch.setEncounters(branch2.getEncounters());
			branch.setAddress(branch2.getAddress());
			branch.setId(branch2.getId());
			entityManager.merge(branch);
			entityTransaction.commit();
			return branch;
		} else {
			return null;
		}

	}

	public void removeBranch(Branch branch, int id) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Branch branchd = null;
		Hospital hospital = entityManager.find(Hospital.class, id);
		List<Encounter> encounters = null;
		Address address = null;
		if (hospital != null) {
			List<Branch> branchs = hospital.getBranchs();

			for (Branch branch2 : branchs) {
				if (branch2.getId() == branch.getId()) {
					branchd = branch2;
					address = branch2.getAddress();
					encounters = branch2.getEncounters();

					branchs.remove(branch2);
					break;
				}

			}
			hospital.setBranchs(branchs);
			entityTransaction.begin();
			entityManager.merge(hospital);
			for (Encounter encounter : encounters) {
				List<MedOrders> list = encounter.getMedOrders();
				for (MedOrders medOrders : list) {
					entityManager.remove(medOrders);
				}
				entityManager.remove(encounter);

			}
			entityManager.remove(address);

			entityManager.remove(branchd);
			entityTransaction.commit();
		}

	}
}
