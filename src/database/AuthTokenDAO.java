package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import model.AuthToken;

public class AuthTokenDAO extends Setup{

	public AuthTokenDAO(String databaseName) {
		Setup.databaseName = databaseName;
	}
	
	/**
	 * saves an AuthToken to the database
	 * @param token the token to save
	 * @return true if successful 
	 */
	public boolean saveAuthToken(AuthToken token) {
		if (token == null)
			return false;
		Connection connection = Setup.getConnection(databaseName);
		try {
			PreparedStatement prep = connection.prepareStatement("INSERT INTO AuthToken values (?,?,?,?,?,?,?);");
			
			prep.setString(1, token.getValue().toString());
			prep.setInt(2, token.getExTime().getMinute());
			prep.setInt(3, token.getExTime().getHour());
			prep.setInt(4, token.getExDate().getDayOfMonth());
			prep.setInt(5, token.getExDate().getMonthValue());
			prep.setInt(6, token.getExDate().getYear());
			prep.setString(7, token.getUserLink().toString());
			prep.addBatch();
			
			connection.setAutoCommit(false);
			prep.executeBatch();
			connection.setAutoCommit(true);
			
			prep.close();
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	/**
	 * Retrieves an authToken from the database
	 * @param value the value of the token
	 * @return a token object
	 */
	public AuthToken getAuthToken(String value) {
		if (value == null || value.length() < 1)
			return null;
		AuthToken result = null;
		
		Connection connection = Setup.getConnection(databaseName);
		try {
			Statement stat = connection.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM AuthToken WHERE value='" + value + "';");
			if (rs.next()) {
				UUID id = UUID.fromString(rs.getString(1));
				int min = rs.getInt(2);
				int hour = rs.getInt(3);
				int day = rs.getInt(4);
				int month = rs.getInt(5);
				int year = rs.getInt(6);
				UUID userLink = UUID.fromString(rs.getString(7));
				
				LocalDate exDate = LocalDate.of(year, month, day);
				LocalTime exTime = LocalTime.of(hour, min);
				result = new AuthToken(id, exTime, exDate, userLink);
				
				rs.close();
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Deletes an authToken from the database
	 * @param value the value of the token
	 * @return true if successful, false otherwise
	 */
	public boolean deleteAuthToken(String value) {
		if (value == null || value.length() < 1)
			return false;
		Connection connection = Setup.getConnection(databaseName);
		try {
			Statement stat = connection.createStatement();
			stat.executeUpdate("DELETE FROM AuthToken WHERE value ='" + value +"';");
			stat.close();
			connection.close();
			
		} catch (SQLException e) {		
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Deletes an authTOken from the database 
	 * @param token the token object to delete
	 * @return true if successful, false otherwise
	 */
	public boolean deleteAuthToken(AuthToken token) {
		if (token == null || token.getValue() == null)
			return false;
		else
			return deleteAuthToken(token.getValue().toString());
	}
}
