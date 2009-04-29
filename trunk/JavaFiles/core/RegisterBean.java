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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	@Validate(required=false, maxlength=30) private String userFirstName;
	@Validate(required=false, maxlength=30) private String userLastName;
	@Validate(required=true) private boolean userIsDoctor;
	
	//constructor
	public RegisterBean () {
		this.userName = "";
		this.userPassword = "";
		this.userPassword2 = "";
		this.userEmail = "";
		this.userEmail2 = "";
		this.userPhone = "";
		this.userFirstName = "";
		this.userLastName = "";
		this.userIsDoctor = false;
	}
	
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
		if (userPhone.equals(null)) userPhone = "";
		this.userPhone = userPhone;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		if (userFirstName.equals(null)) userFirstName = "";
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		if (userLastName.equals(null)) userLastName = "";
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
	 * @return Resolution forwarded to regSuccess when the user
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
	public void validateRegInfo(ValidationErrors errors) {
		
		//check that verified fields match each other
		if (!userPassword.equals(userPassword2))
			errors.addGlobalError(new SimpleError("Your passwords do not match"));
		if (!userEmail.equals(userEmail2))
			errors.addGlobalError(new SimpleError("Your email addresses do not match"));
		
		//check for valid email
		if(notValidEmail(this.userEmail) || notValidEmail(this.userEmail2))
			errors.addGlobalError(new SimpleError("Please enter a valid email address"));
		
		// Check for flagged characters
	    if (hasSpecialCharacters(this.userName) || hasSpecialCharacters(this.userPassword) ||
	    		hasSpecialCharacters(this.userEmail) || hasSpecialCharacters(this.userPhone) ||
	    		hasSpecialCharacters(this.userFirstName) || hasSpecialCharacters(this.userLastName))
	        errors.addGlobalError(new SimpleError("These characters are not allowed: <> () [] \\ / | = + * $ # ^ : ; "));
	}
	
	private boolean notValidEmail(String s) {
		String ptrStr = "(.*)@(.*)\\.(.*)";
    	Pattern p = Pattern.compile(ptrStr);
    	Matcher m = p.matcher(s);
    	boolean bool = false;
    	if(!m.find()) {
    		bool = true;
    	}
		return bool;
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
		if (s != s.replaceAll("([^A-Za-z0-9.,@!?~`'\"% _-]+)", "")) return true;
		return false;
	}
    
}
