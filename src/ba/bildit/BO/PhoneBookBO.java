package ba.bildit.BO;

import ba.bildit.DTO.Persons;
import ba.bildit.DTO.Users;

public class PhoneBookBO {


	public boolean isUserNull(Users user) {
		if (user == null) {
			return false;
		} else {
			return true;
		}

	}

	public boolean isPasswordValid(String password) {
		if (password.length() < 8) {
			System.out.println("Please enter at least eight characters.");

			return false;
		}
		return true;
	}

	public boolean isPasswordCorrect(String pass, Users user) {

		if (pass.equals(user.getPassword())) {
			return true;
		} else {
		}
		System.out.println("Password is incorrect,please try again.");
		return false;

	}

	public boolean isPhoneNumberValid(String phoneNmber, Persons person) {
		for (int i = 0; i < person.getPhoneNumber().length(); i++) {
			if (!Character.isDigit(person.getPhoneNumber().charAt(i))) {
				System.out.println("This field can contain only digits.");
				return false;
			}
		}
		return true;
	}

}
