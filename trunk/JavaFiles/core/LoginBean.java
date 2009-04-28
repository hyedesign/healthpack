/**********************************************************
* File: core.LoginBean.java
* Author: Alex Bassett
* Date Created: 4/13/2009
*
* Description: This ActionBean handles the form validation
* 				for the HealthPack login page
*
* Edited  : 4/27/09 by Alex Bassett
* Changes : Added HPActionBeanContext to handle setting
* 			userid in the session
*
**********************************************************/
package core;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.*;

public class LoginBean implements ActionBean {

	// Declare class variables
	private HPActionBeanContext context;
	
	// form fields
	@Validate(required=true, minlength=4, maxlength=30) private String userName;
	@Validate(required=true, minlength=4, maxlength=30) private String userPassword;
	private int userId;
	
	/* Getters and Setters*/
	// overridden from ActionBean
    public HPActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = (HPActionBeanContext)context; }
    
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

/* Validation (Stripes) Methods and Handlers */
	
	/**
	 * Event handler for the login form. Calls SQL function
	 * that loads user from database, and saves their id
	 * and whether or not they are a doctor to the session
	 *
	 * @return Resolution forwarded back to login page when
	 * login was unsuccessful and to patient list when login
	 * was successful
	 * @author Alex Bassett
	 */
	@DefaultHandler
	public Resolution submit() {
		this.userId = UserSQL.loginUser(this.userName, this.userPassword);
		
		// check that this user exists
		// if not, go back to login page
		if (userId == UserSQL.NO_MATCHING_USER) return new ForwardResolution("login.jsp");
		// user exists, save to session and forward to user homepage
		else {
			context.setUserId(userId);
			context.setUserIsDoctor(UserSQL.isDoctor(userId));
			return new ForwardResolution("userHomepage.jsp");
		}
	}
	
	/**
	 * Stops SQL injection of the Login form by flagging fields
	 * that contain any special characters
	 *
	 * @param s the new value for myString
	 * @author Alex Bassett
	 */
	@ValidationMethod(on="submit")
	public void noSpecialCharacters(ValidationErrors errors) {
	    if (hasSpecialCharacters(this.userName))
	        errors.add("userName", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
	    if (hasSpecialCharacters(this.userPassword))
	    	errors.add("userPassword", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
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

}
