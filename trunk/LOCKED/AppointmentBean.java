package core;

/**********************************************************
* File: core.AppointmentBean.java
* Author: Taylor Evans
* Date Created: 4/19/2009
*
* Description: The AppointmentBean class works with 
*              editAppointment.jsp to verify form inputs
*
* Edited  : 4/20/2009 by Taylor Evans
* Changes : added header comment
*
**********************************************************/

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

public class AppointmentBean implements ActionBean {
    private ActionBeanContext context;

    @Validate(required=true) private double appointmentMonth;
    @Validate(required=true) private double appointmentDay;
    @Validate(required=true) private double appointmentYear;
	@Validate(required=false, maxlength=255) private String description;
	@Validate(required=false)private boolean reminder;
	
    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
    
    public boolean isReminder() {
		return reminder;
	}
	public void setReminder(boolean reminder) {
		this.reminder = reminder;
	}
    
    public double getAppointmentMonth() {
		return appointmentMonth;
	}
	public void setAppointmentMonth(double appointmentMonth) {
		this.appointmentMonth = appointmentMonth;
	}
	public double getAppointmentDay() {
		return appointmentDay;
	}
	public void setAppointmentDay(double appointmentDay) {
		this.appointmentDay = appointmentDay;
	}
	public double getAppointmentYear() {
		return appointmentYear;
	}
	public void setAppointmentYear(double appointmentYear) {
		this.appointmentYear = appointmentYear;
	}
	
	public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    @ValidationMethod(on="update")
    public void dateFormat(ValidationErrors errors) 
    {
    	if(appointmentMonth < 1 || appointmentMonth > 12)
    		errors.add("appointmentMonth", new SimpleError("Invalid Appointment Month"));
    	if(appointmentDay < 1 || appointmentDay > 31)
    		errors.add("appointmentDay", new SimpleError("Invalid Appointment Day"));
    	if(appointmentYear < 2009)
    		errors.add("appointmentYear", new SimpleError("Invalid Appointment Year"));
    }
    
    @DefaultHandler
    public Resolution update() {
        return new ForwardResolution("patientHome.jsp");
    }
    
    
}