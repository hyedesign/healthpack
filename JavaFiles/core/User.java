/**********************************************************
* File: core.User.java
* Author: Alex Bassett
* Date Created: 4/13/2009
*
* Description: The User class handles loading and updating
*              the user table in the HealthPack database.
*
* Edited  : 4/16/2009 by Alex Bassett
* Changes : added comments,
* 			added lookupUser(int id)
*
**********************************************************/
package core;

import java.sql.*;

public class User {
	
	// variable connects to the database
	private DBAccess dba;
	// declare user data
	public int userId;
	public String userName;
	public String userPassword;
	public boolean userIsDoctor;
	public String userEmail;
	public String userPhone;
	public String userDescription;
	public String userFirstName;
	public String userLastName;
	
	// constructor
	public User () {
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
	
	/**
	 * Connects to the 'users' table in the mySQL database using
	 * DBAccess and attempts to load the user data specified by the
	 * username and password. 
	 * 
	 * @param username the name of the user to look up
	 * @param password the password associated with that user
	 * @return true when the user specified can be loaded from the
	 * database, false when the user doesn't exist or the username and 
	 * password do not match
	 * @author Alex Bassett
	 */
	public boolean lookupUser(String username, String password) {
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			ResultSet results = statement.executeQuery ("SELECT * FROM users WHERE username='"+username+
							"' AND userpassword='"+password+"'");
			
			// attempt to access first row and load user
			if (results.next()) {	
				userId = results.getInt("userid");
				userName = results.getString("username");
				userPassword = results.getString("userpassword");
				userEmail = results.getString("useremail");
				userPhone = results.getString("userphone");
				userDescription = results.getString("userdescription");
				userFirstName = results.getString("userfirstname");
				userLastName = results.getString("userlastname");
				// convert int into boolean
				if (results.getInt("userisdoctor") == 0) 
					userIsDoctor = false;
				else userIsDoctor = true;
			}
			// user not found, close connections and exit
			else {
				results.close();
				statement.close();
				dba.disconnect();
				return false;
			}
			results.close();
			statement.close();
		} catch (SQLException e) {
            System.err.println ("Method login() performed bad SQL call");
            System.err.println (e.toString());
            return false;
		}
		// close connection and exit
		dba.disconnect();
		return true;
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
			
			// attempt to access first row and load user
			if (results.next()) {	
				userId = results.getInt("userid");
				userName = results.getString("username");
				userPassword = results.getString("userpassword");
				userEmail = results.getString("useremail");
				userPhone = results.getString("userphone");
				userDescription = results.getString("userdescription");
				userFirstName = results.getString("userfirstname");
				userLastName = results.getString("userlastname");
				// convert int into boolean
				if (results.getInt("userisdoctor") == 0) 
					userIsDoctor = false;
				else userIsDoctor = true;
			}
			// user not found, close connections and exit
			else {
				results.close();
				statement.close();
				dba.disconnect();
				return false;
			}
			results.close();
			statement.close();
		} catch (SQLException e) {
            System.err.println ("Method login() performed bad SQL call");
            System.err.println (e.toString());
            return false;
		}
		// close connection and exit
		dba.disconnect();
		return true;
	}
}