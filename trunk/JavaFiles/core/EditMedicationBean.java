/**********************************************************
* File: core.EditMedicationBean.java
* Author: Jon Conti-Vock
* Date Created: 4/26/2009
*
* Description: The EditMedicationBean class works with 
*              editMedications.jsp to verify form inputs
*
*
**********************************************************/

package core;

import java.sql.Date;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;


public class EditMedicationBean implements ActionBean {
    private HPActionBeanContext context;
    private int medicationId;
    @Validate(required=false, maxlength=255) private String medicationName = "";
    @Validate(required=false, maxlength=2) private int expirationDay;
    @Validate(required=false, maxlength=2) private int expirationMonth;
    @Validate(required=false, maxlength=4) private int expirationYear;
    @Validate(required=false, maxlength=2) private int refillDay;
    @Validate(required=false, maxlength=2) private int refillMonth;
    @Validate(required=false, maxlength=4) private int refillYear;
    @Validate(required=false, maxlength=255) private String description = 	"";
    
    public HPActionBeanContext getContext() { return this.context; }
    public void setContext(ActionBeanContext context) { this.context = (HPActionBeanContext) context; }
    
    public void setMedicationId(int medicationId) {
		this.medicationId = medicationId;
	}
	public int getMedicationId() {
		return medicationId;
	}
	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}
	public String getMedicationName() {
		return medicationName;
	}
	
	public int getExpirationDay() {
		return expirationDay;
	}
	public void setExpirationDay(int expirationDay) {
		this.expirationDay = expirationDay;
	}
	public int getExpirationMonth() {
		return expirationMonth;
	}
	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	public int getExpirationYear() {
		return expirationYear;
	}
	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}
	public int getRefillDay() {
		return refillDay;
	}
	public void setRefillDay(int refillDay) {
		this.refillDay = refillDay;
	}
	public int getRefillMonth() {
		return refillMonth;
	}
	public void setRefillMonth(int refillMonth) {
		this.refillMonth = refillMonth;
	}
	public int getRefillYear() {
		return refillYear;
	}
	public void setRefillYear(int refillYear) {
		this.refillYear = refillYear;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}

	@DefaultHandler
    public Resolution submit() {
    	MedicationSQL temp = new MedicationSQL();
    	boolean loaded = temp.lookupMedication(medicationId);
    	if(loaded == true)
    	{	medicationName = temp.getMedicationName();
    	
    		//expire date
    	
    		Date expirationdate = temp.getMedicationExpirationDate();
    		String s = expirationdate.toString();
    		int index = s.indexOf('-');
    		expirationYear = Integer.parseInt(s.substring(0, index));
    		s = s.substring(index+1, s.length());
    		index = s.indexOf('-');
    		expirationMonth = Integer.parseInt(s.substring(0,index));
    		expirationDay = Integer.parseInt(s.substring(index + 1, s.length()));
    		
    		//refill date
    		
    		Date refilldate = temp.getMedicationRefillDate();
    		String t = refilldate.toString();
    		int index2 = t.indexOf('-');
    		refillYear = Integer.parseInt(t.substring(0, index2));
    		t = t.substring(index2+1, t.length());
    		index2 = t.indexOf('-');
    		refillMonth = Integer.parseInt(t.substring(0,index2));
    		refillDay = Integer.parseInt(t.substring(index2 + 1, t.length()));
    		
    		setDescription(temp.getMedicationDescription());
    		description = temp.getMedicationDescription();
    		if(description.equals("null")) { description = ""; }
    	}	
    	return new ForwardResolution("editMedications.jsp");
        
    }
	
	 /**
     * deletes the medication specified by the medication id
     * from the mySQL database
     * 
     * @author Han Dong
     */
    public Resolution delete()
    {
    	MedicationSQL.deleteMedication(this.medicationId);
    	return new ForwardResolution("patientList.jsp");
    }
	
	
}