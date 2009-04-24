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
* File: core.DocNoteBean.java
* Author: Jon Conti-Vock
* Date Created: 4/19/2009
*
* Description: The DocNoteBean class handles editing and updating
*              the Doctor's Note in the HealthPack database.
*
**********************************************************/

public class DocBean implements ActionBean {
    private ActionBeanContext context;
    @Validate(required=false) private String fname;
    @Validate(required=false) private String lname;
    @Validate(required=true, maxlength=10, minlength=10) private int phone;
    @Validate(required=false) private String email;
    @Validate(required=true, maxlength=255) private String description;
    

    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
    
    public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@DefaultHandler
    public Resolution Submit() {
        return new ForwardResolution("editDoctorInfo.jsp");
    }
    
    @ValidationMethod(on="Submit")
    public void checkDoctorInputs(ValidationErrors errors) {
    	//Most of this is courtesy of Han, copied from VerifyUserInfo.java
    	//checks first name
	    if (this.fname == null) 
	    {
	        errors.add("fName", new SimpleError("Please enter a first name."));
	    }
	    else if (this.fname.length() > 20) 
	    {
	        errors.add("fName", new SimpleError("Please make sure first name is less than 20 characters."));
	    }
	    
	    //checks last name
	    if (this.lname == null) 
	    {
	        errors.add("lName", new SimpleError("Please enter a last name."));
	    }
	    else if (this.lname.length() > 20) 
	    {
	        errors.add("lName", new SimpleError("Please make sure first name is less than 20 characters."));
	    }
	    
	    //checks phone number
	    if((Integer.toString(phone).length() != 10) && (Integer.toString(phone).length() != 12)) {
	    	errors.add("phone", new SimpleError("Please make sure the phone number is either 10 digits or 10 digits with two hyphens."));
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