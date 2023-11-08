package com.ty.hospital_app_hibernate.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.ty.hospital_app_hibernate.dao.AddressDao;
import com.ty.hospital_app_hibernate.dao.BranchDao;
import com.ty.hospital_app_hibernate.dao.EncounterDao;
import com.ty.hospital_app_hibernate.dao.HospitalDao;
import com.ty.hospital_app_hibernate.dao.ItemDao;
import com.ty.hospital_app_hibernate.dao.MedOrdersDao;
import com.ty.hospital_app_hibernate.dao.PersonDao;
import com.ty.hospital_app_hibernate.entity.Address;
import com.ty.hospital_app_hibernate.entity.Branch;
import com.ty.hospital_app_hibernate.entity.Encounter;
import com.ty.hospital_app_hibernate.entity.Hospital;
import com.ty.hospital_app_hibernate.entity.Item;
import com.ty.hospital_app_hibernate.entity.MedOrders;
import com.ty.hospital_app_hibernate.entity.Person;

public class Driver {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		HospitalDao hospitalDao = new HospitalDao();
		BranchDao branchDao = new BranchDao();
		AddressDao addressDao = new AddressDao();
		PersonDao personDao = new PersonDao();
		ItemDao itemDao = new ItemDao();
		EncounterDao encounterDao = new EncounterDao();
		MedOrdersDao medOrdersDao = new MedOrdersDao();
		for (;;) {
			System.out.println("choose an option");
			System.out.println(
					"1.Hospital Section\n2.Branch Section\n3.Address Section\n4.Items Section\n5.Person Section\n6.Encounter Section\n7.MedOrder Section");
			int key = scanner.nextInt();
			switch (key) {
			case 1:
				System.out
						.println("1.Add Hospital All Details\n2.Add Hospital\n3.Find Hospital\n4.Update Hospital Name");
				int option = scanner.nextInt();
				switch (option) {
				// ------------------------------------------------------------------------------
				case 1:
					Hospital hospital = new Hospital();
					System.out.println("Enter the Hospital Id");
					hospital.setId(scanner.nextInt());
					System.out.println("Enter the Hospital Name");
					hospital.setName(scanner.next());

					Branch branch = new Branch();
					System.out.println("Enter Branch Id ");
					branch.setId(scanner.nextInt());
					System.out.println("Enter the Branch Name");
					branch.setName(scanner.next());
					System.out.println("Enter the Branch location");
					branch.setLocation(scanner.next());

					Address address = new Address();
					System.out.println("Enter Address Id ");
					address.setId(scanner.nextInt());
					System.out.println("Enter the Address locality");
					address.setLocality(scanner.next());
					System.out.println("Enter the Address city");
					address.setCity(scanner.next());
					System.out.println("Enter the Address country");
					address.setCountry(scanner.next());
					System.out.println("Enter the Address pincode");
					address.setPincode(scanner.nextInt());

					Encounter encounter = new Encounter();
					System.out.println("Enter encounter Id ");
					encounter.setId(scanner.nextInt());
					System.out.println("Enter the encounter Name");
					encounter.setName(scanner.next());

					MedOrders medOrders = new MedOrders();
					System.out.println("Enter medOrders Id ");
					medOrders.setOrder_id(scanner.nextInt());
					System.out.println("Enter the medOrders Name");
					medOrders.setOrder_name(scanner.next());
					System.out.println("Enter the medOrders Date");
					medOrders.setFullDate(scanner.next());

					Item item = new Item();
					System.out.println("Enter Item Id ");
					item.setId(scanner.nextInt());
					System.out.println("Enter the Item Name");
					item.setName(scanner.next());
					System.out.println("Enter Item quantity ");
					item.setQuantity(scanner.nextInt());
					System.out.println("Enter the Item Name");
					item.setCost(scanner.nextDouble());

					Person person = new Person();
					System.out.println("Enter person id");
					person.setId(scanner.nextInt());
					System.out.println("Enter person Name");
					person.setName(scanner.next());
					System.out.println("Enter person Email");
					person.setEmail(scanner.next());
					System.out.println("Enter person age");
					person.setId(scanner.nextInt());

					List<Item> items = new ArrayList<Item>();
					items.add(item);

					medOrders.setItems(items);

					List<MedOrders> medOrders2 = new ArrayList<MedOrders>();
					medOrders2.add(medOrders);

					encounter.setMedOrders(medOrders2);
					encounter.setPerson(person);

					List<Encounter> encounters = new ArrayList<Encounter>();
					encounters.add(encounter);

					branch.setEncounters(encounters);
					branch.setAddress(address);

					List<Branch> branchs = new ArrayList<Branch>();
					branchs.add(branch);

					hospital.setBranchs(branchs);

					hospitalDao.saveHospital(hospital);

					break;

				case 2:
					Hospital hospital1 = new Hospital();
					System.out.println("Enter the Hospital Id");
					hospital1.setId(scanner.nextInt());
					System.out.println("Enter the Hospital Name");
					hospital1.setName(scanner.next());
					System.out.println("enter the no of branch you want to add");
					int size = scanner.nextInt();
					List<Branch> branchs2 = new ArrayList<Branch>();
					for (int i = 1; i <= size; i++) {
						Branch branch1 = new Branch();
						System.out.println("Enter Branch Id ");
						branch1.setId(scanner.nextInt());
						System.out.println("Enter the Branch Name");
						branch1.setName(scanner.next());
						System.out.println("Enter the Branch location");
						branch1.setLocation(scanner.next());

						branchs2.add(branch1);

					}
					hospital1.setBranchs(branchs2);
					hospitalDao.saveHospitalOnly(hospital1);
					System.out.println("inserted");
					break;

				case 3:
					Hospital hospital11 = new Hospital();
					System.out.println("Enter the Hospital Id you want to fetch Details");
					hospital11.setId(scanner.nextInt());

					Hospital out = hospitalDao.findHospital(hospital11);
					System.out.println("======================================");
					System.out.println("Hospital Id: " + out.getId());
					System.out.println("the Hospital Name: " + out.getName());
					System.out.println("======================================");
					List<Branch> branchs3 = out.getBranchs();
					for (Branch branch2 : branchs3) {
						System.out.println("======================================");
						System.out.println("Branch Id: " + branch2.getId());
						System.out.println("the Branch Name: " + branch2.getName());
						System.out.println("the Branch location: " + branch2.getLocation());

					}
					System.out.println("======================================");

					break;

				case 4:
					Hospital hospital111 = new Hospital();
					System.out.println("Enter the Hospital Id you want to Update Details");
					hospital111.setId(scanner.nextInt());
					System.out.println("Enter the Hospital Name");
					hospital111.setName(scanner.next());

					hospitalDao.updateHospital(hospital111);
					System.out.println("Update Successfully");

					break;

				case 5:
					System.out.println("enter hospital id to remove");
					int id = scanner.nextInt();

					hospitalDao.removeHospital(id);
					System.out.println("Removed Successfully");
					break;
				default:
					System.out.println("Wrong Input");
					System.out.println("======================================");
					break;
				}
				// ------------------------------------------------------------------------------
			case 2:
				System.out.println("1.Add Branch\n2.Find Branch\n3.Update Branch\n4.Delete Branch By id");
				int option1 = scanner.nextInt();
				switch (option1) {
				case 1:
					System.out.println("Enter the hospital Id you want to add Branch");
					int hid = scanner.nextInt();

					Branch branch1 = new Branch();
					System.out.println("Enter Branch Id ");
					branch1.setId(scanner.nextInt());
					System.out.println("Enter the Branch Name");
					branch1.setName(scanner.next());
					System.out.println("Enter the Branch location");
					branch1.setLocation(scanner.next());

					Address address2 = new Address();
					System.out.println("Enter Address Id :");
					address2.setId(scanner.nextInt());
					System.out.println("Enter Address locality :");
					address2.setLocality(scanner.next());
					System.out.println("Enter Address City :");
					address2.setCity(scanner.next());
					System.out.println("Enter Address country :");
					address2.setCountry(scanner.next());
					System.out.println("Enter Address pincode :");
					address2.setPincode(scanner.nextInt());

					Encounter encounter1 = new Encounter();
					System.out.println("Enter Address Id :");
					encounter1.setId(scanner.nextInt());
					System.out.println("Enter Address Id :");
					encounter1.setName(scanner.next());

					List<Encounter> encounters2 = new ArrayList<Encounter>();
					encounters2.add(encounter1);

					branch1.setAddress(address2);
					branch1.setEncounters(encounters2);

					branchDao.saveBranch(branch1, hid);
					System.out.println("Inserted");

					break;

				case 2:
					Branch branch = new Branch();
					System.out.println("Enter the Branch Id To Fetch Details");
					branch.setId(scanner.nextInt());

					Branch branch2 = branchDao.findBranch(branch);
					System.out.println("======================================");
					System.out.println("Branch Id: " + branch2.getId());
					System.out.println("the Branch Name: " + branch2.getName());
					System.out.println("the Branch location: " + branch2.getLocation());
					System.out.println("======================================");
					System.out.println(branch2.getAddress());
					List<Encounter> encounters = branch2.getEncounters();
					System.out.println(encounters);

					System.out.println("======================================");
					break;

				case 3:
					Branch branch11 = new Branch();
					System.out.println("Enter the Branch Id you want to Update Details");
					branch11.setId(scanner.nextInt());
					System.out.println("Enter the Branch Name");
					branch11.setName(scanner.next());

					branchDao.updateBranch(branch11);
					System.out.println("Update Successfully");

					break;

				case 4:
					System.out.println("Enter hospital Id");
					int id = scanner.nextInt();
					Branch branch111 = new Branch();
					System.out.println("Enter the Branch Id you want to Delete Details");
					branch111.setId(scanner.nextInt());

					branchDao.removeBranch(branch111, id);
					System.out.println("Deleted");

					break;

				default:
					break;
				}
				// ------------------------------------------------------------------------------
			case 3:
				System.out.println("1.Find Address By Branch Id\n2.Update Address\n3.Add Address for existing branch");
				int option2 = scanner.nextInt();

				switch (option2) {
				case 1:
					Branch branch = new Branch();
					System.out.println("Enter the branch Id you want to Fetch details");
					branch.setId(scanner.nextInt());

					System.out.println(addressDao.findAddressById(branch));
					break;

				case 2:
					Address address = new Address();
					System.out.println("Enter the Address id to Update");
					address.setId(scanner.nextInt());
					System.out.println("Enter the Address locality");
					address.setLocality(scanner.next());
					System.out.println("Enter the Address City ");
					address.setCity(scanner.next());
					System.out.println("Enter the Address pincode");
					address.setPincode(scanner.nextInt());

					Address address2 = addressDao.updateAddress(address);
					System.out.println(address2);
					break;

				case 3:
					System.out.println("Enter Branch Id you want to Add Address");
					int branch_id = scanner.nextInt();
					Address address1 = new Address();
					System.out.println("Enter the Address id to Update");
					address1.setId(scanner.nextInt());
					System.out.println("Enter the Address locality");
					address1.setLocality(scanner.next());
					System.out.println("Enter the Address City ");
					address1.setCity(scanner.next());
					System.out.println("Enter the Address Country ");
					address1.setCountry(scanner.next());
					System.out.println("Enter the Address pincode");
					address1.setPincode(scanner.nextInt());

					Address address21 = addressDao.addAddress(address1, branch_id);
					System.out.println(address21);
					break;

				default:
					break;
				}
				// ------------------------------------------------------------------------------
			case 4:
				System.out.println("1.Add Item \n2.find Item \n3.Update Item");
				int option3 = scanner.nextInt();
				switch (option3) {
				case 1:
					Item item = new Item();
					System.out.println("Enter Item ID");
					item.setId(scanner.nextInt());
					System.out.println("Enter Item Name");
					item.setName(scanner.next());
					System.out.println("Enter Item Age");
					item.setQuantity(scanner.nextInt());
					System.out.println("Enter Item Email");
					item.setCost(scanner.nextDouble());

					System.out.println("Item Inserted: " + itemDao.addItem(item));
					System.out.println();
					System.out.println("=======================================");
					break;

				case 2:
					Item item1 = new Item();
					System.out.println("Enter Person ID to Fetch Details");
					item1.setId(scanner.nextInt());

					System.out.println("Item Details: " + itemDao.findItemById(item1));
					System.out.println("=======================================");
					break;

				case 3:
					Item item11 = new Item();
					System.out.println("Enter Item ID");
					item11.setId(scanner.nextInt());
					System.out.println("Enter Item Name");
					item11.setName(scanner.next());
					System.out.println("Enter Item Age");
					item11.setQuantity(scanner.nextInt());
					System.out.println("Enter Item Email");
					item11.setCost(scanner.nextDouble());

					System.out.println("Item Inserted: " + itemDao.addItem(item11));
					System.out.println();
					System.out.println("=======================================");
					break;

				default:
					break;
				}
				break;
			// ------------------------------------------------------------------------------
			case 5:
				System.out.println("1.Add Person \n2.find Person \n3.Update Person\n4.Delete person");
				int option4 = scanner.nextInt();
				switch (option4) {
				case 1:
					Person person = new Person();
					System.out.println("Enter Person ID");
					person.setId(scanner.nextInt());
					System.out.println("Enter Person Name");
					person.setName(scanner.next());
					System.out.println("Enter Person Age");
					person.setAge(scanner.nextInt());
					System.out.println("Enter Person Email");
					person.setEmail(scanner.next());

					System.out.println("Person Inserted: " + personDao.addPerson(person));
					System.out.println("=======================================");
					break;

				case 2:
					Person person1 = new Person();
					System.out.println("Enter Person ID to Fetch Details");
					person1.setId(scanner.nextInt());

					System.out.println("Person Details: " + personDao.findById(person1));
					System.out.println("=======================================");
					break;

				case 3:
					Person person11 = new Person();
					System.out.println("Enter Person ID to update Details");
					person11.setId(scanner.nextInt());
					System.out.println("Enter Person Name");
					person11.setName(scanner.next());
					System.out.println("Enter Person Age");
					person11.setAge(scanner.nextInt());
					System.out.println("Enter Person Email");
					person11.setEmail(scanner.next());

					System.out.println("Person Updated: " + personDao.updatePerson(person11));
					System.out.println("=======================================");
					break;

				case 4:
					Person person111 = new Person();
					System.out.println("Enter Person ID to Delete Details");
					person111.setId(scanner.nextInt());
					personDao.removePerson(person111);
					System.out.println("Person Deleted ");
					System.out.println("=======================================");
					break;

				default:
					break;
				}
				break;
			// ------------------------------------------------------------------------------
			case 6:
				System.out.println("1.Add Encounter \n2.find Encounter \n3.Update Encounter\n4.Delete Encounter");
				int option5 = scanner.nextInt();
				switch (option5) {
				case 1:
					System.out.println("Enter the Branch Id for which you are creating Encounter");
					int bid = scanner.nextInt();
					List<MedOrders> orders = new ArrayList<MedOrders>();
					Encounter encounter = new Encounter();
					System.out.println("Enter the Encounter ID");
					encounter.setId(scanner.nextInt());
					System.out.println("Enter the Encounter name");
					encounter.setName(scanner.next());

					Person person = new Person();
					System.out.println("Enter Person ID");
					person.setId(scanner.nextInt());
					System.out.println("Enter Person Name");
					person.setName(scanner.next());
					System.out.println("Enter Person Age");
					person.setAge(scanner.nextInt());
					System.out.println("Enter Person Email");
					person.setEmail(scanner.next());

					encounter.setPerson(person);

					System.out.println("Mention number of medorder to add");
					int num = scanner.nextInt();
					for (int i = 0; i < num; i++) {
						MedOrders medOrders = new MedOrders();
						System.out.println("Enter Order ID");
						medOrders.setOrder_id(scanner.nextInt());
						System.out.println("Enter Order Name");
						medOrders.setOrder_name(scanner.next());
						System.out.println("Enter Order FullDate");
						medOrders.setFullDate(scanner.next());

						orders.add(medOrders);
					}
					encounter.setMedOrders(orders);

					encounterDao.saveEncounter(encounter, bid);
					System.out.println("Data Inserted");
					break;

				case 2:
					Encounter encounter1 = new Encounter();
					System.out.println("Enter Encounter ID to Fetch Details");
					encounter1.setId(scanner.nextInt());

					System.out.println("Encounter Details: " + encounterDao.findEncounterById(encounter1));
					System.out.println("=======================================");
					break;

				case 3:
					Encounter encounter11 = new Encounter();
					System.out.println("Enter the Encounter ID to update");
					encounter11.setId(scanner.nextInt());
					System.out.println("Enter the Encounter name");
					encounter11.setName(scanner.next());

					System.out.println(encounterDao.updateEncounter(encounter11));
					System.out.println("Update Successfully");
					System.out.println("==========================================");
					break;

				case 4:
					Encounter encounter111 = new Encounter();
					System.out.println("Enter Encounter ID to Delete Details");
					encounter111.setId(scanner.nextInt());
					encounterDao.findEncounterById(encounter111);
					System.out.println("Encounter Deleted ");
					System.out.println("=======================================");
					break;

				default:
					break;
				}
				break;
			// ------------------------------------------------------------------------------
			case 7:
				System.out.println("1.Add MedOrders \n2.find MedOrders \n3.Update Medorder\n4.Delete Medorder");
				int option6 = scanner.nextInt();
				switch (option6) {
				case 1:
					System.out.println("Enter the Encounter Id for which you are creating Encounter");
					int bid = scanner.nextInt();
					List<Item> items = new ArrayList<Item>();
					MedOrders medOrders = new MedOrders();
					System.out.println("Enter Order ID");
					medOrders.setOrder_id(scanner.nextInt());
					System.out.println("Enter Order Name");
					medOrders.setOrder_name(scanner.next());
					System.out.println("Enter Order FullDate");
					medOrders.setFullDate(scanner.next());

					System.out.println("Mention number of Items to add");
					int num = scanner.nextInt();
					for (int i = 0; i < num; i++) {
						Item item = new Item();
						System.out.println("Enter Items ID");
						item.setId(scanner.nextInt());
						System.out.println("Enter Items Name");
						item.setName(scanner.next());
						System.out.println("Enter Items Quantity");
						item.setQuantity(scanner.nextInt());
						System.out.println("Enter Items Cost");
						item.setCost(scanner.nextDouble());

						items.add(item);
					}
					medOrders.setItems(items);

					medOrdersDao.saveMedOrders(medOrders, bid);
					System.out.println("Data Inserted");
					System.out.println("==========================================");

					break;

				case 2:
					MedOrders medOrders1 = new MedOrders();
					System.out.println("Enter MedOrders ID to Fetch Details");
					medOrders1.setOrder_id(scanner.nextInt());

					System.out.println("MedOrders Details: " + medOrdersDao.findMedOrdersById(medOrders1));
					System.out.println("=======================================");
					break;

				case 3:
					MedOrders medOrders11 = new MedOrders();
					System.out.println("Enter Order ID to update ");
					medOrders11.setOrder_id(scanner.nextInt());
					System.out.println("Enter Order Name");
					medOrders11.setOrder_name(scanner.next());
					System.out.println("Enter Order FullDate");
					medOrders11.setFullDate(scanner.next());

					System.out.println("Update MedOrders Details:" + medOrdersDao.updateMedOrders(medOrders11));
					System.out.println("=======================================");
					break;

				case 4:
					MedOrders medOrders111 = new MedOrders();
					System.out.println("Enter MedOrders ID to Delete Details");
					medOrders111.setOrder_id(scanner.nextInt());

					medOrdersDao.findMedOrdersById(medOrders111);
					System.out.println("MedOrders Deleted");
					System.out.println("=======================================");
					break;

				default:
					System.out.println("Wrong input");
					return;
				}

				break;

			default:
				System.out.println("Wrong input");
				return;
			}
		}
	}
}
