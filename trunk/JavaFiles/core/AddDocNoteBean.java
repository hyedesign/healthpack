/**********************************************************
* File: core.AddDocNoteBean.java
* Author: Jon Conti-Vock
* Date Created: 4/27/2009
*
* Description: The AllergiesBean class works with 
*              addAllergies.jsp to verify form inputs
*
**********************************************************/

package core;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;


public class AddDocNoteBean implements ActionBean {
    private HPActionBeanContext context;
    
    @Validate(required=false, maxlength=255) private String description;

    // getters and setters
    public HPActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = (HPActionBeanContext) context; }
    
	public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    @DefaultHandler
    public Resolution submit() {

    	DocNoteSQL.updateDocNote(context.getPatientId(), description);
    		
        return new ForwardResolution("patientList.jsp");
    }
    
    @ValidationMethod(on="submit")
    public void checkSpecialChars(ValidationErrors errors) 
    {
    	if (description == null)
    	{
    	}
    	else
    	{	
    		description = description.replaceAll("\\'", "''");
    		if (hasSpecialCharacters(description))
    	    	errors.add("description", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
    	}
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