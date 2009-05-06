/**********************************************************
* File: core.UserInfoBean.java
* Author: Han Dong
* Date Created: 4/19/2009
*
* Description: Verifies user info and checks if its valid
*
* Edited  : 
* Changes : 
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

public class UserInfoBean implements ActionBean
{
	private HPActionBeanContext context;
	@Validate(required=true, maxlength=30) private String firstName;
	@Validate(required=true, maxlength=30) private String lastName;
	@Validate(required=true, maxlength=50) private String email;
	@Validate(required=false, minlength=10, maxlength=15) private String phone;
	@Validate(required=false, maxlength=255) private String description;
	@Validate(required=true, minlength=4, maxlength=30) private String password;
	@Validate(required=true, minlength=4, maxlength=30) private String password2;
	
	public HPActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = (HPActionBeanContext)context; }
	
    /* getters and setters for instance variables */
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
	
	@DefaultHandler
    public Resolution submit() 
	{
		UserSQL.updateUserInfo(this.firstName, this.lastName, this.email,
				this.phone, this.description, this.password, this.password2, context.getUserId());

        return new ForwardResolution("userHomepage.jsp");
    }
	
    public Resolution cancel() 
	{
        return new ForwardResolution("userHomepage.jsp");
    }
	
	/**
	 * Verifies the user inputs
	 * @param errors
	 */
	@ValidationMethod(on="submit")
	public void checksUserInfo(ValidationErrors errors) 
	{	    
		//required fields
		this.firstName = this.firstName.replaceAll("\\'", "''");
		this.lastName = this.lastName.replaceAll("\\'", "''");
		
		if(this.description == null)
		{
		}
		else
		{
			this.description = this.description.replaceAll("\\'", "''");
			if(hasSpecialCharacters(this.description))
				errors.add("description", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * $ # ^ : ; "));
		}
		
		if(this.phone == null)
		{
		}
		else
		{
			this.phone = this.phone.replaceAll("\\'", "''");
			if(hasSpecialCharacters(this.phone))
				errors.add("phone", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * $ # ^ : ; "));
		}
		
		// Check for flagged characters
	    if (hasSpecialCharacters(this.firstName) || hasSpecialCharacters(this.lastName) ||
	    		hasSpecialCharacters(this.email) || hasSpecialCharacters(this.password) || 
	    		hasSpecialCharacters(this.password2))
	        errors.addGlobalError(new SimpleError("These characters are not allowed: <> () [] \\ / | = + * $ # ^ : ; "));
	    
	    // checks email address
		if(notValidEmail(this.email)) 
    	{
    		errors.add("email", 
    				new SimpleError("Please enter a email in the following format: X@X.X ."));
    	}
	    
	    //checks password
		if(!this.password.equals(this.password2))
		{
			errors.add("password", new SimpleError("Please make sure both passwords are identical."));
		}
		
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
		if (s != null)
			if (s != s.replaceAll("([^A-Za-z0-9.,@!?~`'\"% _-]+)", ""))
				return true;
		return false;
	}
}
