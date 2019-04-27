package ba.bild.UI;

public class Menu {
	public void showOptions() {
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("Welcome to phonebook\n  please choose an option:");
		System.out.println("1. Add(save)contact to your phonebook.\n"
				+ "2. Edit existing contact in your phonebook.\n"
				+ "3. Delete contact.\n"
				+ "4. Search phonebook by last name\n"
				+ "5. Printout all contacts.\n"
				+ "6. Exit");
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

}
	public void startMenu()  {
		
		System.out.println("XXXXXXXXXXXX");
		System.out.println("1. Sign up \n"
				         + "2. Sign in \n"
				         + "0. Exit");
		System.out.println("XXXXXXXXXXXX");
	}
}