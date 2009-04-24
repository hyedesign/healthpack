/**********************************************************
* File: core.UserSQL.java
* Author: Alex Bassett
* Date Created: 4/13/2009
*
* Description: The UserSQL class handles loading and updating
*              the user table in the HealthPack database.
*
**********************************************************/
package core;

import java.sql.*;

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
            System.err.println ("Method login() performed bad SQL call");
            System.err.println (e.toString());
			dba_s.disconnect();
            return NO_MATCHING_USER;
		}
	}

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
			System.err.println("Bad ResultSet call from User.loadUserData()");
            System.err.println (e.toString());
			return false;
		}
	}
}