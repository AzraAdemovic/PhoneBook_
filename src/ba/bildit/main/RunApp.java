package ba.bildit.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ba.bild.UI.Menu;
import ba.bild.UI.PhoneBookUI;
import ba.bildit.BO.PhoneBookBO;
import ba.bildit.DTO.Persons;
import ba.bildit.DTO.Users;
import ba.bilit.DAO.PhoneBookDAOImplementation;

public class RunApp {

	public static void main(String[] args) throws SQLException {
		Scanner input = new Scanner(System.in);
		// Business layer
		PhoneBookBO PBBO = new PhoneBookBO();
		// User Interface layer
		PhoneBookUI PBUI = new PhoneBookUI();
		Menu menu = new Menu();
		ArrayList<Persons> list;
		Persons person;
		Users user = new Users();

		int personId;
		// String password = null;
		// Data Access Layer
		PhoneBookDAOImplementation PBDAO = new PhoneBookDAOImplementation();
		boolean running = true;
		System.out.println("Choose an option:");

		menu.startMenu();
		int num = -1;
		while (num != 0) {

			num = input.nextInt();
			input.nextLine();
			switch (num) {

			case 0:
				num = 0;
				break;
			case 1:

				System.out.println("Enter name:");
				user.setFirstName(input.nextLine());
				System.out.println("Enter last name:");
				user.setLastName(input.nextLine());
				System.out.println("Enter password:");
				String password = input.nextLine();
				user.setPassword(password);
				while (!(PBBO.isPasswordValid(password))) {
					System.out.println("Try again:");
					password = input.nextLine();
				}
				PBDAO.createUser(user);
				menu.startMenu();
				break;
			case 2:
				System.out.println("Enter last name:");
				String lastname = input.nextLine();
				System.out.println("Enter password:");
				password = input.nextLine();
				if (PBDAO.checkUserLogin(lastname, password)) {
					while (running) {

						menu.showOptions();
						int option = input.nextInt();
						switch (option) {

						case 1:

							int ID = PBDAO.getUserID(lastname, password);
							user = PBDAO.getUser(ID);
							// System.out.println(user);

							person = PBUI.createPerson();
							if (PBBO.isPhoneNumberValid(person.getPhoneNumber(), person)) {
								PBDAO.savePerson(person, ID);
							} else {
								System.out.println("Contact can not be saved. Please try again.");
							}

							break;
						case 2:
							int id = PBDAO.getUserID(lastname, password);
							System.out.println("You can edit this list of persons:\n");
							List<Persons> contactList = PBDAO.getPersonsByUser(id);
							for (int i = 0; i < contactList.size(); i++) {
								System.out.println(contactList.get(i).toString());
							}

							System.out.print("Please enter person's id you'd like to update: ");
							personId = input.nextInt();
							if (PBBO.checkIfIdIsValid(personId, PBDAO.getPersonsByUser(id))) {
								try {
									person = PBDAO.getPerson(personId);

									if (PBDAO.updatePerson(PBUI.editPerson(personId), id)) {
										System.out.println("Updated.");
									} else {
										System.out.println("Failed.");
									}

								} catch (SQLException e) {

									e.printStackTrace();
								}
							} else {
								System.out.println("Protected.");
							}
							break;
						case 3:
							System.out.println("Please enter persons id : ");
							int personsID = input.nextInt();
							PBUI.deletePerson(personsID);

							break;
						case 4:
							PBUI.doSearch();
							break;
						case 5:
							try {
								list = PBDAO.getPersons();
								PBUI.printAllContacts(list);
							} catch (SQLException e) {
								e.printStackTrace();
							}

							break;

						case 6:
							running = false;
							menu.startMenu();
							break;
						default:
							System.out.println("Choose one of following options.");
							break;
						}
					}
				} else {
					num = -1;
					System.out.println("User not found!");
					menu.startMenu();
				}
				break;

			default:
				System.out.println("Select one of options!");
				menu.startMenu();
			}
		}
		input.close();
	}
}
