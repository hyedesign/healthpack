/**********************************************************
* File: core.AllergiesBean.java
* Author: Taylor Evans
* Date Created: 4/19/2009
*
* Description: The AllergiesBean class works with 
*              addAllergies.jsp to verify form inputs
*
* Edited  : 4/28/2009 by Taylor Evans
* Changes : added comments
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
	//private data members
    private HPActionBeanContext context;
    private int allergyID;
    @Validate(required=true, maxlength=30) private String allergyName;
    @Validate(required=false, maxlength=255) private String description;

    //GETTERS AND SETTERS
    public HPActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = (HPActionBeanContext)context; }
    
    public int getAllergyID() {		return allergyID;	}
	public void setAllergyID(int allergyID) {		this.allergyID = allergyID; }

	public String getAllergyName() { return allergyName; }
    public void setAllergyName(String allergyName) { this.allergyName = allergyName; }
    
	public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    /**
     * adds a new allergy or updates an
     * existing allergy depening on the id
     * @return
     * 
     * Taylor Evans
     */
    @DefaultHandler
    public Resolution submit() {
    	if(allergyID == 0)
    		AllergiesSQL.addAllergies(context.getPatientId(), allergyName, description);
    	else
    	{
    		AllergiesSQL temp = new AllergiesSQL();
    		temp.updateAllergy(allergyID, allergyName, description);
    	}
    		
        return new ForwardResolution("patientList.jsp");
    }
    
    /**
     * Checks the inputed allergyName and the description
     * for illegal characters. If any are found it displays
     * an errors
     * 
     * Taylor Evans
     */
    @ValidationMethod(on="submit")
    public void checkSpecialChars(ValidationErrors errors) 
    {
    	if (hasSpecialCharacters(allergyName))
	    	errors.add("allergyName", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
    	if (hasSpecialCharacters(description))
	    	errors.add("description", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
    }
    
    /**
     * Checks the inputed string for illegal characters
     * and returns true if any are found. Otherwise it
     * returns false.
     * @param s
     * @return
     * 
     * Taylor Evans
     */
    private boolean hasSpecialCharacters(String s) {
		if (s != s.replaceAll("([^A-Za-z0-9.,!?~`'\"% _-]+)", "")) return true;
		return false;
	}
}
