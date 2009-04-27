/**********************************************************
* File: core.VerifyUserInfo.java
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
	private ActionBeanContext context;
	@Validate(required=true, maxlength=20) private String firstName;
	@Validate(required=true, maxlength=20) private String lastName;
	@Validate(required=true, maxlength=20) private String email;
	@Validate(required=true, maxlength=10) private String phone;
	@Validate(required=false) private String description;
	@Validate(required=true, maxlength=20) private String password;
	@Validate(required=true, maxlength=20) private String password2;
	
	public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
	
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
		new UserInfoSQL(this.firstName, this.lastName, this.email,
				this.phone, this.description, this.password, this.password2).updateUserInfo(3);

        return new ForwardResolution("verifyUserInfo.jsp");
    }
	
	/**
	 * Verifies the user inputs
	 * @param errors
	 */
	@ValidationMethod(on="submit")
	public void checksUserInfo(ValidationErrors errors) 
	{	    
		if(hasNumber(this.firstName))
	    {
	        errors.add("firstName", 
	        		new SimpleError("Please make sure there " +
	        				"are no numbers in your first name."));
	    }
		
		if(hasNumber(this.lastName))
	    {
	        errors.add("lastName", 
	        		new SimpleError("Please make sure there " +
	        				"are no numbers in your last name."));
	    }
	    
		if(checksEmail(this.email)) 
    	{
    		errors.add("email", 
    				new SimpleError("Please enter a email in the following format: XX@XX.XX ."));
    	}
	    
	    //checks password
		if(!this.password.equals(this.password2))
		{
			errors.add("password", new SimpleError("Please make sure both passwords are identical."));
		}
		
		if(isPhone(this.phone))
		{
			errors.add("phone", new SimpleError("Integers only."));
		}
	}
	
	private boolean hasNumber(String s) {
		for (int j = 0;j < s.length();j++) {
			if (Character.isDigit(s.charAt(j))) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isPhone(String s) {
		String ptrStr = "\\d{10}";
    	Pattern p = Pattern.compile(ptrStr);
    	Matcher m = p.matcher(s);
    	boolean bool = false;
    	if(!m.find()) {
    		bool = true;
    	}
		return bool;
	}
	
	private boolean checksEmail(String s) {
		String ptrStr = "(.*)@(.*)\\.(.*)";
    	Pattern p = Pattern.compile(ptrStr);
    	Matcher m = p.matcher(s);
    	boolean bool = false;
    	if(!m.find()) {
    		bool = true;
    	}
		return bool;
	}
}
