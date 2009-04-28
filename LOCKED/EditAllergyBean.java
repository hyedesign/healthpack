/**********************************************************
* File: core.EditAllergyBean.java
* Author: Taylor Evans
* Date Created: 4/26/2009
*
* Description: The EditAllergyBean class works with 
*              editAllergies.jsp to verify form inputs
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
import net.sourceforge.stripes.validation.Validate;


public class EditAllergyBean implements ActionBean {
    private ActionBeanContext context;
    //@Validate(required=false) private int allergyID;
    //@Validate(required=false) private int patientID;
    @Validate(required=false, maxlength=30) private String allergyName;
    @Validate(required=false, maxlength=255) private String description;

    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }

//    public int getAllergyID() {	return allergyID;	}
//	public void setAllergyID(int allergyID) {this.allergyID = allergyID; }
	
	//public int getPatientID() {	return patientID;	}
	//public void setPatientID(int patientID) {	this.patientID = patientID;	}
	
	public String getAllergyName() { return allergyName; }
    public void setAllergyName(String allergyName) { this.allergyName = allergyName; }
    
	public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    @DefaultHandler
    public Resolution submit() {
    	int allergyID = 4;
    	AllergiesSQL temp = new AllergiesSQL();
    	boolean loaded = temp.lookupAllergy(allergyID);
    	if(loaded == true)
    		allergyName = temp.getAllergyName();
    		description = temp.getDescription();
        return new ForwardResolution("editAllergies.jsp");
    }
}
