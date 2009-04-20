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

public class VerifyUserInfo implements ActionBean
{
	private ActionBeanContext context;
	@Validate(required=false) private String firstName;
	@Validate(required=false) private String lastName;
	@Validate(required=false) private String email;
	@Validate(required=false) private String textarea;
	@Validate(required=false) private String password;
	@Validate(required=false) private String password2;
	
	public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
	
    /* getters and setters for instance variables */
    public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getTextarea() { return textarea; }
	public void setTextarea(String textarea) { this.textarea = textarea; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getPassword2() { return password2; }
	public void setPassword2(String password2) { this.password2 = password2; }
	
	@DefaultHandler
    public Resolution submit() 
	{
		/*Update database with new info below*/
		/* UPDATE users
		 * SET userfirstname="firstName", userlastname="lastName", useremail="email", userdescription="textarea", userpassword="password"
		 * WHERE userid = session.getAttribute("id");
		 */
        return new ForwardResolution("verifyUserInfo.jsp");
    }
	
	/**
	 * Verifies the user inputs
	 * @param errors
	 */
	@ValidationMethod(on="submit")
	public void checksUserInfo(ValidationErrors errors) 
	{	
		//checks first name
	    if (this.firstName == null) 
	    {
	        errors.add("firstName", new SimpleError("Please enter a first name."));
	    }
	    else if (this.firstName.length() > 20) 
	    {
	        errors.add("firstName", new SimpleError("Please make sure first name is less than 20 characters."));
	    }
	    
	    //checks last name
	    if (this.lastName == null) 
	    {
	        errors.add("lastName", new SimpleError("Please enter a last name."));
	    }
	    else if (this.lastName.length() > 20) 
	    {
	        errors.add("lasttName", new SimpleError("Please make sure first name is less than 20 characters."));
	    }
	    
	    //checks email
	    if(this.email == null)
	    {
	    	errors.add("email", new SimpleError("Please enter an email."));
	    }
	    else
	    {
	    	String ptrStr = "(.*)@(.*)\\.(.*)";
	    	Pattern p = Pattern.compile(ptrStr);
	    	Matcher m = p.matcher(this.email);
	    	if(!m.find()) 
	    	{
	    		errors.add("email", new SimpleError("Please enter a email in the following format: XX@XX.XX ."));
	    	}
	    	else if (this.email.length() > 20)
	    	{
	    		errors.add("email", new SimpleError("Please make sure email is less than 20 characters."));
	    	}
	    }
	    
	    //checks password
	    if(this.password == null || this.password2 == null)
	    {
	    	errors.add("email", new SimpleError("Please make sure both password fields are entered."));
	    }
	    else if(!this.password.equals(this.password2))
		{
			errors.add("password", new SimpleError("Please make sure both passwords are identical."));
		}
	}
}
