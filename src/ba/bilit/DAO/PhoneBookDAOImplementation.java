package ba.bilit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ba.bildit.DTO.Persons;
import ba.bildit.DTO.Users;

public class PhoneBookDAOImplementation implements PhoneBookDAOInterface {
	Connection connection = ManagerConnection.getInstance().getConnection();

	// Users user = new Users();

	@Override
	public boolean createUser(Users user) throws SQLException {

		String query = "INSERT INTO users (firstName,lastName,userspassword) VALUES (?,?,?)";
		try (Connection connection = ManagerConnection.getInstance().getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getPassword());

			preparedStatement.executeUpdate();
			System.out.println("Now you are one of users.");

			return true;

		}

	}

	@Override
	public ArrayList<Persons> getPersons() throws SQLException {

		ArrayList<Persons> listperson = new ArrayList<>();

		String query = "SELECT * FROM persons";
		ResultSet rs = null;
		try (Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(query);

			while (rs.next()) {

				Persons persons = new Persons(rs.getInt("personsId"), rs.getString("firstName"),
						rs.getString("lastName"), rs.getString("phoneNumber"));

				listperson.add(persons);
			}

		}

		return listperson;
	}

	@Override
	public Persons getPerson(int personsID) throws SQLException {
		Persons person = new Persons();
		String query = "SELECT * FROM persons WHERE personsId = ?";

		ResultSet rs = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, personsID);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				person = new Persons(rs.getInt("personsId"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("phoneNumber"));
			}

		}
		rs.close();

		return person;

	}

	@Override
	public Persons getPerson(String lastName, List<Persons> list) throws SQLException {
		Persons person = null;

		String query = "SELECT * FROM persons WHERE lastName = ?";

		ResultSet rs = null;
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, lastName);
		rs = preparedStatement.executeQuery();

		if (rs.next()) {
			person = new Persons(rs.getInt("personsId"), rs.getString("firstName"), rs.getString("lastName"),
					rs.getString("phoneNumber"));

		}
		rs.close();

		return person;
	}

	@Override
	public boolean updatePerson(Persons person, int userID) throws SQLException {
		String query = "UPDATE persons SET firstName = ?, lastName= ?, phoneNumber = ? WHERE personsId = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setString(1, person.getFirstName());
			preparedStatement.setString(2, person.getLastName());
			preparedStatement.setString(3, person.getPhoneNumber());
			preparedStatement.setInt(4, person.getPersonsID());
			if (preparedStatement.executeUpdate() == 1) {
				return true;
			} else {
				return false;
			}

		}

	}

	@Override
	public boolean deletePerson(Persons person) throws SQLException {
		String query = "DELETE FROM persons WHERE personsId = ?";
		try (PreparedStatement preparedSatement = connection.prepareStatement(query);) {
			preparedSatement.setInt(1, person.getPersonsID());

			if (preparedSatement.executeUpdate() == 1) {
				return true;
			} else {
				return false;

			}

		}

	}

	@Override
	public boolean savePerson(Persons person, int ID) throws SQLException {
		String query = "INSERT INTO persons (firstName,lastName,phoneNumber,user) VALUES (?,?,?,?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setString(1, person.getFirstName());
			preparedStatement.setString(2, person.getLastName());
			preparedStatement.setString(3, person.getPhoneNumber());
			preparedStatement.setInt(4, ID);

			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}

		}
		return false;
	}

	public boolean saveUser(Users user) throws SQLException {
		String query = "INSERT INTO users (firstName,lastName,userspassword) VALUES (?,?,?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getPassword());

			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}

		}
		return false;
	}

	@Override
	public Users getUser(int userID) throws SQLException {
		Users user = null;
		String query = "SELECT * FROM users WHERE id = ?";

		ResultSet rs = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, userID);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				user = new Users(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("userspassword"));
			}

		}
		rs.close();

		return user;
	}

	@Override
	public boolean checkUserLogin(String lastName, String userspassword) throws SQLException {
		// Users user = null;
		String query = "SELECT * FROM users WHERE lastName = ? AND userspassword=?";

		ResultSet rs = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, lastName);
			preparedStatement.setString(2, userspassword);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				rs.close();
				return false;
			}
		}

	}

	@Override
	public int getUserID(String lastname, String password) throws SQLException {
		// Users user = null;
		String query = "SELECT * FROM users WHERE lastName = ? AND userspassword=?";

		ResultSet rs = null;
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, lastname);
		preparedStatement.setString(2, password);
		rs = preparedStatement.executeQuery();
		rs.next();

		return rs.getInt("id");

	}

	@Override
	public List<Persons> getPersonsByUser(int userID) throws SQLException {
		List<Persons> persons = new ArrayList<>();
		String query = "SELECT * FROM persons WHERE user = ?";

		ResultSet rs = null;
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, userID);

		rs = preparedStatement.executeQuery();

		while (rs.next()) {
			Persons person = new Persons(rs.getInt("personsId"), rs.getString("lastName"), rs.getString("firstName"),
					rs.getString("phoneNumber"));
			persons.add(person);
		}

		return persons;
	}

}
