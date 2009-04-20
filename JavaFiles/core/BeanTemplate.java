/**********************************************************
* Jam Packed Inventions
* HealthPackv0.3
* File: core.BeanTemplate.java
* Author: Alex Bassett
* Date Created: 4/19/2009
*
* Description: Template class to demonstrate standards
*
* Edited  : 4/19/2009 by Alex Bassett
* Changes : None Really, but this is where you
* 			log changes and additions
*
**********************************************************/
package core;

import net.sourceforge.stripes.action.*;			 //needed for all that fun stripes stuff
import net.sourceforge.stripes.validation.*;  //validates in case you hadn't guessed already

import java.sql.*; 						   			 //needed for SQL stuff

public class BeanTemplate implements ActionBean {
	
	// Declare class variables
	private ActionBeanContext context;	//necessary for bean classes
	private DBAccess dba;				//necessary for connecting to database
	
	// Stripes autovalidating this field,
	// notice that it still has custom validation below
	@Validate(required=true, maxlength=30) private String myString;
	
	// Constructor
	public BeanTemplate() {
		context = null;
		dba = null;
		myString = "";
	}

	// Overridden functions from the ActionBean interface
    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
    
    
    /* Getters and Setters*/
    // no comment blocks for these
	public String getMyString() {
		return myString;
	}
	public void setMyString(String s) {
		this.myString = s;
	}
    
	
    /* Validation (Stripes) Methods and Handlers */
    
	/**
	 * Validates myString input
	 *
	 * @param s the new value for myString
	 * @author Alex Bassett
	 */
	@ValidationMethod(on="submitMyString")
	public void descriptiveName(ValidationErrors errors) {
	    if (this.myString.indexOf("<") != -1 ||
	    	this.myString.indexOf(">") != -1) {
	        errors.add("myString", new SimpleError("These characters are not allowed: < >"));
	    }
	}
	
	/**
	 * Does something when user clicks "Submit"
	 * Maybe it calls a SQL access function?
	 *
	 * @return forwards its output to some JSP page
	 * @author Alex Bassett
	 */
	@HandlesEvent("Submit") // must match the 'name' attribute of your stripes form
    @DefaultHandler // tells stripes to use this method when the user presses [Enter]
    public Resolution submit() {
         /* Do some stuff with your input data,
          * maybe you want to call a SQL function here */
        return new ForwardResolution("WhereverYouWant.jsp");
    }
	
    /* SQL Access Methods */
    // See SQL section of programming reference

}
