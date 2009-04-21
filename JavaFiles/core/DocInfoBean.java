package core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

/**********************************************************
* File: core.DocInfoBean.java
* Author: Jon Conti-Vock
* Date Created: 4/20/2009
*
* Description: The DocInfoBean class handles creating a new password
* 			   and email for a specific doctor.
*
**********************************************************/

public class DocInfoBean implements ActionBean {
    private ActionBeanContext context;
    @Validate(required=false) private String email;
    @Validate(required=false) private String password;
    @Validate(required=false) private String password2;    

    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
    
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	@DefaultHandler
    public Resolution Submit() {
        return new ForwardResolution("editDoctorInfo.jsp");
    }
    
    @ValidationMethod(on="Submit")
    public void checkDocInfoInputs(ValidationErrors errors) {
    	//In the style of Han's VerifyUserInfo.java     Email verification copied directly.
    	//checks password
	    if (this.password == null) 
	    {
	        errors.add("password", new SimpleError("Please enter a password."));
	    }
	    else if (this.password.length() > 20) 
	    {
	        errors.add("password", new SimpleError("Please make sure the password is less than 20 characters."));
	    }
	    
	    //checks password re-type
	    if (this.password2 == null) 
	    {
	        errors.add("password2", new SimpleError("Please re-enter your password."));
	    }
	    else if (this.password2.length() > 20) 
	    {
	        errors.add("password2", new SimpleError("Please make sure you correctly re-enter your password."));
	    }
	    
	    //checks to make sure they're the same
	    if (this.password != this.password2) {
	    	errors.add("password2", new SimpleError("Please make sure you are correctly re-typing your password."));
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
    }
}