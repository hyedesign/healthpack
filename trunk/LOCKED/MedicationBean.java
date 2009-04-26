/**********************************************************
* File: core.MedicationBean.java
* Author: Taylor Evans
* Date Created: 4/19/2009
*
* Description: The MedicationBean class works with 
*              editMedication.jsp to verify form inputs
*
* Edited  : 4/20/2009 by Taylor Evans
* Changes : added header comment
* 
* Edited  : 4/24/2009 by Jon Conti-Vock
* Changes : formatted to follow coding standards
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

public class MedicationBean implements ActionBean {
    private ActionBeanContext context;
    @Validate(required=true, maxlength=30) private String medicationName;
    @Validate(required=true, maxlength=2) private double expirationMonth;
    @Validate(required=true, maxlength=2) private double expirationDay;
    @Validate(required=true, maxlength=4) private double expirationYear;
    @Validate(required=true, maxlength=2) private double refillMonth;
    @Validate(required=true, maxlength=2) private double refillDay;
    @Validate(required=true, maxlength=4) private double refillYear;
    @Validate(required=false, maxlength=255) private String description;

    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }

    public String getMedicationName() { return medicationName; }
    public void setMedicationName(String medicationName) { this.medicationName = medicationName; }
    
    public double getExpirationMonth() {
		return expirationMonth;
	}
	public void setExpirationMonth(double expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	public double getExpirationDay() {
		return expirationDay;
	}
	public void setExpirationDay(double expirationDay) {
		this.expirationDay = expirationDay;
	}
	public double getExpirationYear() {
		return expirationYear;
	}
	public void setExpirationYear(double expirationYear) {
		this.expirationYear = expirationYear;
	}
	public double getRefillMonth() {
		return refillMonth;
	}
	public void setRefillMonth(double refillMonth) {
		this.refillMonth = refillMonth;
	}
	public double getRefillDay() {
		return refillDay;
	}
	public void setRefillDay(double refillDay) {
		this.refillDay = refillDay;
	}
	public double getRefillYear() {
		return refillYear;
	}
	public void setRefillYear(double refillYear) {
		this.refillYear = refillYear;
	}
	public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
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
    }
    
    @DefaultHandler
    public Resolution submit() {
        return new ForwardResolution("patientHome.jsp");
    }
    
    
}