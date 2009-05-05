/**********************************************************
* File: core.EditAllergyBean.java
* Author: Jon Conti-Vock
* Date Created: 4/26/2009
*
* Description: The EditDocNoteBean class works with 
*              editDocNote.jsp to verify form inputs
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

public class EditDocNoteBean implements ActionBean {
    private HPActionBeanContext context;
    private int patientID;
    @Validate(required=false, maxlength=255) private String description;

    // getters and setters
    public HPActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = (HPActionBeanContext) context; }
	
	public int getPatientID() {	return patientID;	}
	public void setPatientID(int patientID) {	this.patientID = patientID;	}
    
	public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @DefaultHandler
    public Resolution submit() {
    	patientID = context.getPatientId();
    	DocNoteSQL temp = new DocNoteSQL();
    	boolean loaded = temp.lookupDocNote(patientID);
    	if(loaded == true){
    		description = temp.getDescription();}
        return new ForwardResolution("editDocNote.jsp");
    }
}