package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

/**
 * A class that sets up the database
 * @author tucker
 *
 */
public class Setup {
	public static String databaseName;
	
	public Setup()
	{
		try {
			Class.forName("org.sqlite.JDBC");
		}
		catch (ClassNotFoundException e) {
			System.err.println("class not found");
			System.err.print(e.getMessage());
		}
	}
	
	/**
	 * Initializes the database, creates tables if they don't exist
	 * @param name the name of the database
	 */
	public static boolean init(String name) {
		databaseName = name;
		return createTables();
	}
	
	/**
	 * Creates a connection to a database and returns the connection
	 * @param name the name of the database to connect to
	 * @return a connection to the above named database
	 */
	public static Connection getConnection(String name) {
		databaseName = name;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName + ".db");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * Creates the tables for the database if they don't already exist
	 */
	private static boolean createTables() {
		Connection connection = getConnection(databaseName);
		try {
			Statement stat = connection.createStatement();
			
			//creating the tables
			stat.executeUpdate("create table if not exists user (id KEY TEXT NOT NULL," +
			"firstName TEXT NOT NULL, lastName TEXT NOT NULL, userName KEY TEXT NOT NULL, password TEXT NOT NULL," +
					"email TEXT NOT NULL);");
			
			stat.executeUpdate("create table if not exists authToken(value TEXT NOT NULL, minute NUMBER NOT NULL," +
					"hour NUMBER NOT NULL, day NUMBER NOT NULL, month NUMBER NOT NULL, year NUMBER NOT NULL," +
					"userLink KEY TEXT NOT NULL);");
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	public static boolean clear() {
		Connection connection = getConnection(databaseName);
		try {
			Statement stat = connection.createStatement();
			stat.executeUpdate("drop table if exists user;");
			stat.executeUpdate("drop table if exists authToken;");
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * a simple test method
	 * @param args
	 */
	public static void main(String[] args) {
		UUID id = UUID.randomUUID();
		System.out.println(id.toString());
		
	}

}
