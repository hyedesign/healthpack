/**********************************************************
* File: core.UserInfo.java
* Author: Han Dong
* Date Created: 4/28/2009
*
* Description: Verifies if the values that the user enters
*			   are valid and will then proceed to update
*              the database.
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
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

public class UserInfoBean implements ActionBean
{
	//instance variables
	private HPActionBeanContext context;
	private int userID;
	@Validate(required=true, maxlength=20) private String firstName;
	@Validate(required=true, maxlength=20) private String lastName;
	@Validate(required=true, maxlength=20) private String email;
	@Validate(required=true, maxlength=10) private String phone;
	@Validate(required=false, maxlength=255) private String description;
	@Validate(required=true, maxlength=20) private String password;
	@Validate(required=true, maxlength=20) private String password2;
	
	/* getters and setters for instance variables */
	public HPActionBeanContext getContext() 
	{ 
		return this.context; 
	}
    public void setContext(ActionBeanContext context) 
    { 
    	this.context = (HPActionBeanContext) context; 
    }
    public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getPassword2() { return password2; }
	public void setPassword2(String password2) { this.password2 = password2; }
	
	/**
	 * submit - updates the current user and forwards to user home page
	 * @return
	 */
	@DefaultHandler
    public Resolution submit() 
	{
		//calls static UserSQL function with necessary data
		UserSQL.updateUserInfo(this.firstName, this.lastName, this.email, 
				this.phone, this.description, this.password, this.password2, this.userID);
        return new ForwardResolution("userHomepage.jsp");
    }
	
	/**
	 * checksUserInfo - checks all user inputs to make sure they are valid.
	 *                  eg. special characters, escaping apostrophe
	 * @param errors
	 */
	@ValidationMethod(on="submit")
	public void checksUserInfo(ValidationErrors errors) 
	{	    
		//checks first name for numbers, escapes apostrophe, special characters 
		this.firstName = this.firstName.replaceAll("\\'", "''");
		if(hasNumber(this.firstName))
	    {
	        errors.add("firstName", 
	        		new SimpleError("Please make sure there " +
	        				"are no numbers in your first name."));
	    }
		else if(hasSpecialCharacters(this.firstName))
		{
			errors.add("firstName", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
		}
		
		//checks last name for numbers, escapes apostrophe, special characters 
		this.lastName = this.lastName.replaceAll("\\'", "''");
		if(hasNumber(this.lastName))
	    {
	        errors.add("lastName", 
	        		new SimpleError("Please make sure there " +
	        				"are no numbers in your last name."));
	    }
		else if(hasSpecialCharacters(this.lastName))
		{
			errors.add("lastName", new SimpleError("These characters are not allowed in Description: <> () [] \\ / | = + * @ $ # ^ : ; "));
		}
		
		//checks email for correct email form
		if(checksEmail(this.email)) 
    	{
    		errors.add("email", 
    				new SimpleError("Please enter a email in the following format: XX@XX.XX ."));
    	}
		
	    //checks password, equality
		if(!this.password.equals(this.password2))
		{
			errors.add("password", new SimpleError("Please make sure both passwords are identical."));
		}
		
		//checks if only integers exist
		if(isPhone(this.phone))
		{
			errors.add("phone", new SimpleError("Integers only."));
		}
		
		//checks desscription, apostrophe, special characters
		if(this.description == null) {}
		else {
			this.description = this.description.replaceAll("\\'", "''");
			if(hasSpecialCharacters(this.description))
			{	
				errors.add("description", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
			}
		}
	}
	
	/**
	 * hasNumber - checks if numbers exist in the string
	 * @param s
	 * @return
	 */
	private boolean hasNumber(String s) {
		//loops through each element, checking each char for digits
		for (int j = 0;j < s.length();j++) {
			if (Character.isDigit(s.charAt(j))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * isPhone - checks for 10 digit number
	 * @param s
	 * @return
	 */
	private boolean isPhone(String s) {
		//regex that looks for 10 digits
		String ptrStr = "\\d{10}";
    	Pattern p = Pattern.compile(ptrStr);
    	Matcher m = p.matcher(s);
    	boolean bool = false;
    	if(!m.find()) {
    		bool = true;
    	}
		return bool;
	}
	
	/**
	 * checksEmail - checks for correct email format
	 * @param s
	 * @return
	 */
	private boolean checksEmail(String s) {
		//regex looking for email of xx@xx.xx
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
	 * Checks the inputed string for illegal characters
	 * and returns true if any are found. Otherwise it
	 * returns false.
	 * @param s
	 * @return
	 */
	private boolean hasSpecialCharacters(String s) {
		if (s != s.replaceAll("([^A-Za-z0-9.,!?~`'\"% _-]+)", "")) return true;
		return false;
	}
}
