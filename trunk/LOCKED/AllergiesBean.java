/**********************************************************
* File: core.AllergiesBean.java
* Author: Taylor Evans
* Date Created: 4/19/2009
*
* Description: The AllergiesBean class works with 
*              addAllergies.jsp to verify form inputs
*
* Edited  : 4/20/2009 by Taylor Evans
* Changes : added header comment
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


public class AllergiesBean implements ActionBean {
    private ActionBeanContext context;
    @Validate(required=true, maxlength=30) private String allergyName;
    @Validate(required=false, maxlength=255) private String description;

    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }

    public String getAllergyName() { return allergyName; }
    public void setAllergyName(String allergyName) { this.allergyName = allergyName; }
    
	public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    @DefaultHandler
    public Resolution submit() {
    	int allergyID = 4;
    	if(allergyID == 0)
    	AllergiesSQL.addAllergies(allergyName, description);
    	else
    	{
    		AllergiesSQL temp = new AllergiesSQL();
    		temp.updateAllergy(allergyID, allergyName, description);
    	}
    		
        return new ForwardResolution("patientHome.jsp");
    }
    @ValidationMethod(on="submit")
    public void checkSpecialChars(ValidationErrors errors) 
    {
    	if (hasSpecialCharacters(allergyName))
	    	errors.add("allergyName", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
    	if (hasSpecialCharacters(description))
	    	errors.add("description", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
    }
    private boolean hasSpecialCharacters(String s) {
		if (s != s.replaceAll("([^A-Za-z0-9.,!?~`'\"% _-]+)", "")) return true;
		return false;
	}
}
