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
* Edited  : 4/19/2009 by Alex Bassett
* Changes : Converted to ActionBean for use with Stripes
* 			Added Getter and Setter Methods
* 			Added form validation methods
*
**********************************************************/
package core;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.*;

import java.sql.*;

public class User implements ActionBean {
	
	// Declare class variables
	private ActionBeanContext context;
	private DBAccess dba;
	private boolean loaded;
	// user data
	private int userId;
	@Validate(required=true, minlength=4, maxlength=30) private String userName;
	@Validate(required=true, minlength=4, maxlength=30) private String userPassword;
	@Validate(required=true, maxlength=50) private String userEmail;
	@Validate(required=true, maxlength=15) private String userPhone;
	@Validate(required=true, maxlength=255) private String userDescription;
	@Validate(required=true, maxlength=30) private String userFirstName;
	@Validate(required=true, maxlength=30) private String userLastName;
	@Validate(required=true) private boolean userIsDoctor;
	
	// constructor
	public User () {
		dba = new DBAccess();
		loaded = false;
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
	// overridden from ActionBean
    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
	
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
	
	
	/* Validation (Stripes) Methods and Handlers */
	
	/**
	 * Stops SQL injection of the Login form by flagging fields
	 * that contain any special characters
	 *
	 * @param s the new value for myString
	 * @author Alex Bassett
	 */
	@ValidationMethod(on="login")
	public void noSpecialCharacters(ValidationErrors errors) {
	    if (hasSpecialCharacters(this.userName))
	        errors.add("userName", new SimpleError("These characters are not allowed: <> () {} [] \\ / | = + * @ $ # ^ : ; "));
	    if (hasSpecialCharacters(this.userPassword))
	    	errors.add("userPassword", new SimpleError("These characters are not allowed: <> () {} [] \\ / | = + * @ $ # ^ : ; "));
	}

	/**
	 * Event handler for the login form. Calls SQL function
	 * that loads user from database
	 *
	 * @return Resolution forwarded back to login page when
	 * login was unsuccessful and to patient list when login
	 * was succussful
	 * @author Alex Bassett
	 */
	@HandlesEvent("login")
	@DefaultHandler
	public Resolution login() {	
		if (lookupUser(this.userName, this.userPassword)) {
			loaded = true;
			return new ForwardResolution("patientList.jsp");
		}
		return new ForwardResolution("login.jsp");
	}
    
	/**
	 * Base level function that returns true when the given
	 * input string contains a character that could be used for
	 * a SQL injection attack
	 *
	 * @param s the user created string to be checked
	 * @return true when the string contains special
	 * characters and false if it does not
	 * @author Alex Bassett
	 */
	private boolean hasSpecialCharacters(String s) {
		if (s != s.replaceAll("([^A-Za-z0-9.,!?~`'\"% _-]+)", "")) return true;
		return false;
	}
	
	
	
	/* SQL Access Methods */
	
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