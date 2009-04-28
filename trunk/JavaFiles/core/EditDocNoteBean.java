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
import net.sourceforge.stripes.validation.Validate;


public class EditDocNoteBean implements ActionBean {
    private ActionBeanContext context;
    //@Validate(required=false) private int userID;
    //@Validate(required=false) private int patientID;
    @Validate(required=false, maxlength=255) private String description;

    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }

//    public int getAllergyID() {	return allergyID;	}
//	public void setAllergyID(int allergyID) {this.allergyID = allergyID; }
	
	//public int getPatientID() {	return patientID;	}
	//public void setPatientID(int patientID) {	this.patientID = patientID;	}
    
	public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    @DefaultHandler
    public Resolution submit() {
    	int doctorpatientid = 7;
    	DocNoteSQL temp = new DocNoteSQL();
    	boolean loaded = temp.lookupDocNote(doctorpatientid);
    	if(loaded == true){
//    		userid = temp.getUserID();         taken from session
//    		patientid = temp.getPatientID();   taken from session
    		description = temp.getDescription();}
        return new ForwardResolution("editDocNote.jsp");
    }
}