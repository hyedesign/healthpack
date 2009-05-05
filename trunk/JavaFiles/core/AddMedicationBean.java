/**********************************************************
* File: core.AddMedicationBean.java
* Author: Taylor Evans
* Date Created: 4/19/2009
*
* Description: The AddMedicationBean class works with 
*              editMedication.jsp to verify form inputs
*
* Edited  : 4/20/2009 by Taylor Evans
* Changes : added header comment
* 
* Edited  : 4/24/2009 by Jon Conti-Vock
* Changes : formatted to follow coding standards
* 
* Edited  : 4/26/2009 by Jon Conti-Vock
* Changes : changed to AddMedicationBean and added the date/calendar
* 			creation
* 
**********************************************************/

package core;

import java.sql.Date;
import java.util.Calendar;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

public class AddMedicationBean implements ActionBean {
    private HPActionBeanContext context;
	private Date Expire;
	private Date Refill;
    private int medicationId;
    @Validate(required=true, maxlength=30) private String medicationName;
    @Validate(required=true, maxlength=2) private int expirationMonth;
    @Validate(required=true, maxlength=2) private int expirationDay;
    @Validate(required=true, maxlength=4) private int expirationYear;
    @Validate(required=true, maxlength=2) private int refillMonth;
    @Validate(required=true, maxlength=2) private int refillDay;
    @Validate(required=true, maxlength=4) private int refillYear;
    @Validate(required=false, maxlength=255) private String description;

    public HPActionBeanContext getContext() { return this.context; }
    public void setContext(ActionBeanContext context) { this.context = (HPActionBeanContext) context; }

    
    // getters and setters
	public int getMedicationId() {
		return medicationId;
	}
	public void setMedicationId(int medicationId) {
		this.medicationId = medicationId;
	}
	public Date getExpire() {
		return Expire;
	}
	public void setExpire(Date expire) {
		Expire = expire;
	}
	public Date getRefill() {
		return Refill;
	}
	public void setRefill(Date refill) {
		Refill = refill;
	}
	public String getMedicationName() {
		return medicationName;
	}
	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}
	public int getExpirationMonth() {
		return expirationMonth;
	}
	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	public int getExpirationDay() {
		return expirationDay;
	}
	public void setExpirationDay(int expirationDay) {
		this.expirationDay = expirationDay;
	}
	public int getExpirationYear() {
		return expirationYear;
	}
	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}
	public int getRefillMonth() {
		return refillMonth;
	}
	public void setRefillMonth(int refillMonth) {
		this.refillMonth = refillMonth;
	}
	public int getRefillDay() {
		return refillDay;
	}
	public void setRefillDay(int refillDay) {
		this.refillDay = refillDay;
	}
	public int getRefillYear() {
		return refillYear;
	}
	public void setRefillYear(int refillYear) {
		this.refillYear = refillYear;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ValidationMethod(on="submit")
    public void dateFormat(ValidationErrors errors) 
    {  	if (hasSpecialCharacters(medicationName))
    		errors.add("description", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
    	if (hasSpecialCharacters(description))
    		errors.add("description", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
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
    
    @DefaultHandler
    public Resolution submit() {
    	int patientid = this.context.getPatientId();
   		Calendar cal1 = Calendar.getInstance();
   		cal1.set(this.expirationYear, this.expirationMonth-1, this.expirationDay);
   		this.Expire = new Date(cal1.getTime().getTime());
   		Calendar cal2 = Calendar.getInstance();
   		cal2.set(this.refillYear, this.refillMonth-1, this.refillDay);
   		this.Refill = new Date(cal2.getTime().getTime());
   		if (medicationId == 0){
   			MedicationSQL.addNewMedication( patientid, this.medicationName, this.Expire, this.Refill, this.description);
   		}
   		else{
   			MedicationSQL.updateMedication(medicationId, patientid, this.medicationName, this.Expire, this.Refill, this.description);
   		}
   		return new ForwardResolution("patientList.jsp");
    }
    
    
}