/**********************************************************
* File: core.UserSQL.java
* Author: Alex Bassett
* Date Created: 4/13/2009
*
* Description: The UserSQL class handles loading and updating
*              the user table in the HealthPack database.
*
* Edited: 4/28/09 by Alex Bassett
* Changes: Added isDoctor() for checking that a user is a doctor
*          Added updateUserInfo for updating user
*
**********************************************************/
package core;

import java.sql.*;
import java.util.ArrayList;

import core.DBAccess;

public class UserSQL {
	
	// Declare static constants
	public static int NO_MATCHING_USER = -1;
	
	// Declare class variables
	private DBAccess dba;
	// user data
	private int userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userPhone;
	private String userDescription;
	private String userFirstName;
	private String userLastName;
	private boolean userIsDoctor;
	
	// constructor
	public UserSQL () {
		dba = new DBAccess();
		userId = 0;
		userName = null;
		userPassword = null;
		userIsDoctor = false;
		userEmail = null;
		userPhone = null;
		userDescription = null;
		userFirstName = null;
		userLastName = null;
	}
	
	/* Getters and Setters*/
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserDescription() {
		return userDescription;
	}
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public boolean isUserIsDoctor() {
		return userIsDoctor;
	}
	public void setUserIsDoctor(boolean userIsDoctor) {
		this.userIsDoctor = userIsDoctor;
	}
	
	/* SQL Access Methods */

	/**
	 * Connects to the 'users' table in the mySQL database using
	 * DBAccess and attempts to load the user data specified by the
	 * id number. 
	 * 
	 * @param id the userid of the user to look up
	 * @return true when the user specified can be loaded from the
	 * database, false when the user doesn't exist or the username and 
	 * password do not match
	 * @author Alex Bassett
	 */
	public boolean lookupUser(int id) {
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			ResultSet results = statement.executeQuery ("SELECT * FROM users WHERE userid='"+id+"'");
			
			// attempt to load the user from the ResultSet
			boolean successfulLoad = loadUserData(results);
			
			// close connections
			results.close();
			statement.close();
			dba.disconnect();
			//return true if user was found
			return successfulLoad;
			
		} catch (SQLException e) {
            System.err.println ("Method login() performed bad SQL call");
            System.err.println (e.toString());
			dba.disconnect();
            return false;
		}
	}
	
	/**
	 * Takes a ResultSet object and loads the user data from it.
	 * The result set must be the result of a query to the users
	 * table.
	 * 
	 * @param rs the result set that contains a single user's data
	 * @return true when the data is loaded, false when the ResultSet is
	 * empty and there is no data to load
	 * @author Alex Bassett
	 */
	private boolean loadUserData (ResultSet rs) {
		// check that the resultset isn't empty and load the user data
		try {
			if (rs.first()) {
				userId = rs.getInt("userid");
				userName = rs.getString("username");
				userPassword = rs.getString("userpassword");
				userEmail = rs.getString("useremail");
				userPhone = rs.getString("userphone");
				userDescription = rs.getString("userdescription");
				userFirstName = rs.getString("userfirstname");
				userLastName = rs.getString("userlastname");
				// convert int into boolean
				if (rs.getInt("userisdoctor") == 0) 
					userIsDoctor = false;
				else userIsDoctor = true;
				return true;
			}
			// the resultset is empty, no data loaded
			else return false;
		} catch (SQLException e) {
			System.err.println("Bad ResultSet call from UserSQL.loadUserData()");
            System.err.println (e.toString());
			return false;
		}
	}
	
	/* Statics */
	
	/**
	 * Connects to the 'users' table in the mySQL database using
	 * DBAccess and attempts to load the userId for the specified
	 * username and password 
	 * 
	 * @param username the name of the user to look up
	 * @param password the password associated with that user
	 * @return the userId if the user exists or NO_MATCHING_USER
	 * if not
	 * @author Alex Bassett
	 */
	static public int loginUser(String username, String password) {
		DBAccess dba_s = new DBAccess();
		dba_s.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba_s.connection.createStatement ();
			ResultSet results = statement.executeQuery ("SELECT * FROM users WHERE username='"+username+
							"' AND userpassword='"+password+"'");
			
			// attempt to load the userId from the ResultSet
			int loadedId = NO_MATCHING_USER;
			if (results.first())loadedId = results.getInt("userId");
			
			// close connections
			results.close();
			statement.close();
			dba_s.disconnect();
			//return true if user was found
			return loadedId;
			
		} catch (SQLException e) {
            System.err.println ("Method UserSQL.loginUser() performed bad SQL call");
            System.err.println (e.toString());
			dba_s.disconnect();
            return NO_MATCHING_USER;
		}
	}
	
	/**
	 * Connects to the 'users' table in the mySQL database using
	 * DBAccess and attempts to check to see if the user is a doctor.
	 * 
	 * @param id the id of the user being checked
	 * @return true when the user is a doctor and false otherwise
	 * @author Alex Bassett
	 */
	static public boolean isDoctor(int id) {
		DBAccess dba_s = new DBAccess();
		dba_s.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba_s.connection.createStatement ();
			ResultSet results = statement.executeQuery ("SELECT * FROM users WHERE userid="+id);
			
			// attempt to load the userisdoctor field
			int docint = 0;
			boolean isDoctor;
			if (results.first())docint = results.getInt("userisdoctor");
			
			// translate into boolean value
			if (docint == 0) isDoctor = false;
			else isDoctor = true;
			
			// close connections
			results.close();
			statement.close();
			dba_s.disconnect();
			//return true if user is a doctor
			return isDoctor;
			
		} catch (SQLException e) {
            System.err.println ("Method UserSQL.loginUser() performed bad SQL call");
            System.err.println (e.toString());
			dba_s.disconnect();
            return false;
		}
	}
	
	/**
	 * Connects to the database and checks to see if the username
	 * already exists. It returns true when the username is already
	 * taken
	 * 
	 * @param username to check for
	 * @return the userId if the user exists or NO_MATCHING_USER
	 * if not
	 * @author Alex Bassett
	 */
	static public boolean checkUserName(String username) {
		DBAccess dba_s = new DBAccess();
		dba_s.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba_s.connection.createStatement ();
			ResultSet results = statement.executeQuery ("SELECT * FROM users WHERE username='"+username+"'");
			
			// check to see if the user loaded
			if (results.first())return true;
			
			// close connections
			results.close();
			statement.close();
			dba_s.disconnect();
			//return false if username is unique
			return false;
			
		} catch (SQLException e) {
            System.err.println ("Method UserSQL.checkUserName() performed bad SQL call");
            System.err.println (e.toString());
			dba_s.disconnect();
            return true;
		}
	}
	
	/**
	 * Adds new registrations to the database.
	 * 
	 * @param name the entered userName
	 * @param password the entered userPassword
	 * @param email the entered userEmail
	 * @param phone the entered userPhone
	 * @param firstName the entered userFirstName
	 * @param lastName the entered userLastName
	 * @param isDoctor the entered userIsDoctor
	 * 
	 * @author Alex Bassett
	 */
	public static void registerNewUser(String name, String password,
			String email, String phone, String firstName,
			String lastName, boolean isDoctor) {
		DBAccess dba_s = new DBAccess();
		dba_s.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			String isDoc = "0";
			if (isDoctor) isDoc = "1";
			String s = "INSERT INTO users (username, userpassword, useremail," +
					"userphone, userfirstname, userlastname, userisdoctor) " +
					"VALUES ('" + name + "', '" + password + "', '" + email +
					"', '" + phone + "', '" + firstName + "', '" + lastName +
					"', '" + isDoc + "')";
			Statement statement = dba_s.connection.createStatement ();
			statement.executeUpdate (s);
			
			// close connections
			statement.close();
			dba_s.disconnect();
			
		} catch (SQLException e) {
            System.err.println ("Method UserSQL.registerNewUser() performed bad SQL call");
            System.err.println (e.toString());
			dba_s.disconnect();
		}
	}
	
	/**
	 * Returns first and last name of the user id passed
	 * 
	 * @param id
	 *            The id for which name is needed
	 * 
	 * @author Vahan Kristosturyan
	 */
	public static String getUserFirstAndLastName(String id) {
		DBAccess dba_s = new DBAccess();
		dba_s.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			String s = "SELECT userfirstname, userlastname FROM users WHERE userid = "
					+ id + ";";
			Statement statement = dba_s.connection.createStatement();
			ResultSet rs = statement.executeQuery(s);

			String UserName = "Unknown Name";

			if (rs.first())
				UserName = rs.getString("userfirstname") + " "
						+ rs.getString("userlastname");

			// close connections
			statement.close();
			dba_s.disconnect();
			return UserName;

		} catch (SQLException e) {
			System.err
					.println("Method UserSQL.registerNewUser() performed bad SQL call");
			System.err.println(e.toString());
			dba_s.disconnect();
			return "Unknown Name";
		}
	}

	/**
	 * Returns a List of all doctors by their Ids
	 * 
	 * @author Vahan Kristosturyan
	 */
	public static ArrayList<String> getDoctorList() {
		DBAccess dba_s = new DBAccess();
		dba_s.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			String s = "SELECT userid FROM users WHERE userisdoctor = 001;";
			Statement statement = dba_s.connection.createStatement();
			ResultSet rs = statement.executeQuery(s);

			ArrayList<String> listOfDoctors = new ArrayList<String>();

			while (rs.next())
				listOfDoctors.add(rs.getString("userid"));

			// close connections
			statement.close();
			dba_s.disconnect();
			return listOfDoctors;

		} catch (SQLException e) {
			System.err
					.println("Method UserSQL.registerNewUser() performed bad SQL call");
			System.err.println(e.toString());
			dba_s.disconnect();
			return new ArrayList<String>();
		}
	}
	
	/**
	 * updates the user with the latest information
	 * 
	 * @param firstName is the first name of the user
	 * @param lastName is the last name of the user
	 * @param email is the email of the user
	 * @param phone is the phone number of the user
	 * @param description is the description of the user
	 * @param password is the password of the user
	 * @param password2 is the password of the user
	 * @param userID is the user identification
	 * @author Han Dong
	 */
	public static void updateUserInfo(String firstName, String lastName, String email,
			String phone, String description, String password, String password2, int userID)
	{
		DBAccess dba_s = new DBAccess();
		dba_s.connect(); // connect to the database
		try
		{
			// construct and execute the SQL call, updates database
			Statement statement = dba_s.connection.createStatement();
			
			//if description is null, the user description will not be updated
			if(description == null)
			{
				statement.executeUpdate("UPDATE cmsc345.users SET userpassword='"
						+password+"', useremail='"+email+
						"', userphone='"+phone+"', userfirstname='"
						+firstName+"', userlastname='"+lastName+"' WHERE userid='"
						+userID+"'");
			}
			else
			{
				statement.executeUpdate("UPDATE cmsc345.users SET userpassword='"
						+password+"', useremail='"+email+
						"', userphone='"+phone+"', userdescription='" +description+
						"', userfirstname='"+firstName+"', userlastname='"+
						lastName+"' WHERE userid='"
						+userID+"'");
			}
			
			statement.close();
			dba_s.disconnect();
		}
		catch (SQLException e)
		{
			System.err.println (e.toString());
			System.out.println("Failed to update.");
			dba_s.disconnect();
		}
	}
}