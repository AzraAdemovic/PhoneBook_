package ba.bilit.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ba.bildit.DTO.Persons;
import ba.bildit.DTO.Users;

public interface PhoneBookDAOInterface {

	public boolean createUser(Users user) throws SQLException;

	public ArrayList<Persons> getPersons() throws SQLException;

	public Users getUser(int userID) throws SQLException;

	public Persons getPerson(String lastName, List<Persons> list) throws SQLException;

	public boolean updatePerson(Persons person, int userID) throws SQLException;

	public boolean deletePerson(Persons person) throws SQLException;

	public boolean savePerson(Persons person, int ID) throws SQLException;

	public Persons getPerson(int personsID) throws SQLException;

	public boolean checkUserLogin(String firstName, String password) throws SQLException;

	int getUserID(String lastname, String password) throws SQLException;

	public List<Persons> getPersonsByUser(int userID) throws SQLException;

}
