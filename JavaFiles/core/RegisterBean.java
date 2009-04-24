/**********************************************************
* File: core.RegisterBean.java
* Author: Alex Bassett
* Date Created: 4/13/2009
*
* Description: Class verifies user input data on the
* 				registration form. It also checks to ensure
* 				that the user hasen't selected a username that's
* 				already in use.
*
**********************************************************/
package core;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

public class RegisterBean implements ActionBean {

	// Declare class variables
	private ActionBeanContext context;
	
	@Validate(required=true, minlength=4, maxlength=30) private String userName;
	@Validate(required=true, minlength=4, maxlength=30) private String userPassword;
	@Validate(required=true, minlength=4, maxlength=30) private String userPassword2;
	@Validate(required=true, maxlength=50) private String userEmail;
	@Validate(required=true, maxlength=50) private String userEmail2;
	@Validate(required=false, maxlength=15) private String userPhone;
	@Validate(required=true, maxlength=30) private String userFirstName;
	@Validate(required=true, maxlength=30) private String userLastName;
	@Validate(required=true) private boolean userIsDoctor;
	
	/* Getters and Setters*/
	// overridden from ActionBean
    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
	
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
	public String getUserPassword2() {
		return userPassword2;
	}
	public void setUserPassword2(String userPassword2) {
		this.userPassword2 = userPassword2;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserEmail2() {
		return userEmail2;
	}
	public void setUserEmail2(String userEmail2) {
		this.userEmail2 = userEmail2;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
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
	 * Event handler for the registration form. Calls a
	 * SQL function that adds the new user to the database.
	 *
	 * @return Resolution forwarded to regSuccess when th user
	 * is registered
	 * @author Alex Bassett
	 */
	@DefaultHandler
	public Resolution submit() {
		//check that the username isn't taken
		if (UserSQL.checkUserName(this.userName)) 
			return new ForwardResolution ("register.jsp");
		
		//add registration info to database
		UserSQL.registerNewUser(this.userName, this.userPassword,
				this.userEmail, this.userPhone, this.userFirstName,
				this.userLastName, this.userIsDoctor);
		return new ForwardResolution("regSuccess.jsp");
	}
	
	@ValidationMethod(on="submit")
	public void noSpecialCharacters(ValidationErrors errors) {
		
		//check that verified fields match each other
		if (userPassword != userPassword2) {
			errors.add("userPassword", new SimpleError("Your passwords do not match"));
			errors.add("userPassword2", new SimpleError("Your passwords do not match"));
		}
		if (userEmail != userEmail2) {
			errors.add("userEmail", new SimpleError("Your email addresses do not match"));
			errors.add("userEmail2", new SimpleError("Your email addresses dot not match"));
		}
		
		// Check for flagged characters
	    if (hasSpecialCharacters(this.userName))
	        errors.add("userName", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
	    if (hasSpecialCharacters(this.userPassword))
	    	errors.add("userPassword", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
	    if (hasSpecialCharacters(this.userEmail))
	    	errors.add("userEmail", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
	    if (hasSpecialCharacters(this.userPhone))
	    	errors.add("userPhone", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
	    if (hasSpecialCharacters(this.userFirstName))
	    	errors.add("userFirstName", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
	    if (hasSpecialCharacters(this.userLastName))
	    	errors.add("userLastName", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
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
