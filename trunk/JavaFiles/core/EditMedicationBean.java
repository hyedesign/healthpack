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
import net.sourceforge.stripes.validation.Validate;


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
	
	//public int getPatientID() {	return patientID;	}
	//public void setPatientID(int patientID) {	this.patientID = patientID;	}
    
    public void setMedicationId(int medicationId) {
		this.medicationId = medicationId;
	}
	public int getMedicationId() {
		return medicationId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getPatientId() {
		return patientId;
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
	
	@ValidationMethod(on="submit")
    public void dateFormat(ValidationErrors errors) 
    {
    	if(expirationMonth < 1 || expirationMonth > 12)
    		errors.add("expirationMonth", new SimpleError("Invalid Expiration Month"));
    	if(expirationDay < 1 || expirationDay > 31)
    		errors.add("expirationDay", new SimpleError("Invalid Expiration Day"));
    	if(refillMonth < 1 || refillMonth > 12)
    		errors.add("refillMonth", new SimpleError("Invalid Refill Month"));
    	if(refillDay < 1 || refillDay > 31)
    		errors.add("refillDay", new SimpleError("Invalid Refill Day"));
    	if(expirationYear < 2009)
    		errors.add("expirationYear", new SimpleError("Invalid Expiration Year"));
    	if(refillYear < 2009)
    		errors.add("refillYear", new SimpleError("Invalid Refill Year"));
    	
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
	
	@DefaultHandler
    public Resolution submit() {
    	MedicationSQL temp = new MedicationSQL();
    	boolean loaded = temp.lookupMedication(medicationID);
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
    	}	
    	return new ForwardResolution("editMedications.jsp");
        
    }
}