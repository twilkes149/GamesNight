package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import model.AuthToken;
import model.Email;
import model.User;

public class UserDAO extends Setup{
	
	public UserDAO(String databaseName) {
		this.databaseName = databaseName;
	}
	
	/**
	 * saves a user to the database
	 * @param user the user to save
	 */
	public boolean saveUser(User user) {
		if (user == null)
			return false;
		Connection connection = Setup.getConnection(databaseName);
		try {
			Statement stat = connection.createStatement();
			PreparedStatement prep = connection.prepareStatement("INSERT into user values(?,?,?,?,?,?);");
			prep.setString(1, user.getID().toString());
			prep.setString(2, user.getFirstName());
			prep.setString(3, user.getLastName());
			prep.setString(4, user.getUserName());
			prep.setString(5, user.getPassword());
			prep.setString(6, user.getEmail().toString());
			prep.addBatch();
			
			connection.setAutoCommit(false);
			prep.executeBatch();
			connection.setAutoCommit(true);
			prep.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Returns a user from the database
	 * @param userName the userName of the user
	 * @return a user
	 */
	public User getUser(String userName) {
		User result = null;
		Connection connection = Setup.getConnection(databaseName);
		try {
			Statement stat = connection.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM user WHERE userName ='" + userName + "';");
			if (rs.next()) {
				UUID id = UUID.fromString(rs.getString(1));
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String un = rs.getString(4);
				String password = rs.getString(5);
				Email email = new Email(rs.getString(6));
				
				result = new User(firstName, lastName, un, password, email, null);//temp fix
				result.setID(id);
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * Returns a user from the database
	 * @param token the authToken of the user
	 * @return a user
	 */
	public User getUser(AuthToken token) {
		User result = null;
		Connection connection = Setup.getConnection(databaseName);
		try {
			Statement stat = connection.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM user WHERE id='" + token.getUserLink().toString() + "';");
			if (rs.next()) {
				UUID id = UUID.fromString(rs.getString(1));
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String un = rs.getString(4);
				String password = rs.getString(5);
				Email email = new Email(rs.getString(6));
				
				result = new User(firstName, lastName, un, password, email, null);//temp fix
				result.setID(id);
				rs.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)  {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * Deletes a user from the database
	 * @param userName the userName of the user
	 * @return true if successful, false otherwise
	 */
	public boolean deleteUser(String userName) {
		Connection connection = Setup.getConnection(databaseName);
		try {
			Statement stat = connection.createStatement();
			
			stat.executeUpdate("DELETE FROM user WHERE userName='" + userName +"';");
			stat.close();
			connection.close();
			
			return true;
		} catch (SQLException e) {
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		UUID id1 = UUID.randomUUID();
		System.out.println(id1.toString());
		UUID id = UUID.fromString(id1.toString());
		System.out.println(id.toString());
	}

}
