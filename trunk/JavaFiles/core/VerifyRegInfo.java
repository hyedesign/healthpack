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

public class VerifyRegInfo implements ActionBean
{
	private ActionBeanContext context;
	@Validate(required=false) private String userName;
	@Validate(required=false) private String password;
	@Validate(required=false) private String password2;
	@Validate(required=false) private String firstName;
	@Validate(required=false) private String lastName;
	@Validate(required=false) private String email;
	@Validate(required=false) private String email2;
	@Validate(required=false) private String phone;
	@Validate(required=false) private boolean doctor;
	
	//getters and setters
	public ActionBeanContext getContext() { 
		return context; 
	}
    public void setContext(ActionBeanContext context) { 
    	this.context = context; 
    }
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isDoctor() {
		return doctor;
	}
	public void setDoc(boolean doctor) {
		this.doctor = doctor;
	}
    
	@DefaultHandler
    public Resolution submit() 
	{
		/*Update database with new info below*/
		/* 
		 */
        return new ForwardResolution("verifyRegInfo.jsp");
    }
	
	@ValidationMethod(on="submit")
	public void checksRegInfo(ValidationErrors errors) 
	{	
		//checks user name
		if (this.userName == null) 
	    {
	        errors.add("userName", new SimpleError("Please enter a user name."));
	    }
	    else if (this.userName.length() > 20) 
	    {
	        errors.add("userName", new SimpleError("Please make sure user name is less than 20 characters."));
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
	    
	    //checks first name
	    if (this.firstName == null) 
	    {
	        errors.add("firstName", new SimpleError("Please enter a first name."));
	    }
	    else if(hasNumber(this.firstName))
	    {
	        errors.add("firstName", new SimpleError("Please make sure there are no numbers in your first name."));
	    	
	    }
	    else if (this.firstName.length() > 20) 
    	{
    		errors.add("firstName", new SimpleError("Please make sure first name is less than 20 characters."));
    	}
	    
	    //checks first name
	    if (this.lastName == null) 
	    {
	        errors.add("lastName", new SimpleError("Please enter a last name."));
	    }
	    else if(hasNumber(this.lastName))
	    {
	        errors.add("lastName", new SimpleError("Please make sure there are no numbers in your last name."));
	    	
	    }
	    else if (this.lastName.length() > 20) 
    	{
    		errors.add("firstName", new SimpleError("Please make sure last name is less than 20 characters."));
    	}
	    
	    //checks email
	    if(this.email == null)
	    {
	    	errors.add("email", new SimpleError("Please enter an email."));
	    }
	    else
	    {
	    	if(!this.email.equals(this.email2))
			{
				errors.add("email", new SimpleError("Please make sure both emails are identical."));
			}
	    	else if(checksEmail(this.email)) 
	    	{
	    		errors.add("email", new SimpleError("Please enter a email in the following format: XX@XX.XX ."));
	    	}
	    	else if (this.email.length() > 20)
	    	{
	    		errors.add("email", new SimpleError("Please make sure email is less than 20 characters."));
	    	}
	    }
	    
	    //checks phone
	    if(this.phone == null)
	    {
	    	errors.add("phone", new SimpleError("Please enter a phone number."));
	    }
	    else if(checksPhone(this.phone))
	    {
	    	errors.add("phone", new SimpleError("Please make sure the phone number is either 10 digits or 10 digits with two hyphens."));
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
	
	private boolean checksPhone(String s){
		String ptrStr = "(\\d-)?(\\d{3}-)?\\d{3}-\\d{4}";
    	Pattern p = Pattern.compile(ptrStr);
    	Matcher m = p.matcher(s);
    	boolean bool = false;
    	if(!m.find()) {
    		bool = true;
    	}
		return bool;
	}
}
