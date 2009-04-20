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
* Edited  : 4/17/2009 by Alex Bassett
* Changes : added loadUserData()
* 			cleaned up lookupUser()
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