/**********************************************************
* File: core.AppointmentBean.java
* Author: Taylor Evans
* Date Created: 4/19/2009
*
* Description: The AppointmentBean class works with 
*              editAppointment.jsp to verify form inputs
*
* Edited  : 4/28/2009 by Taylor Evans
* Changes : added comments
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

public class AppointmentBean implements ActionBean {
    private ActionBeanContext context;

    //Initial Stripes validation methods
    @Validate(required=true, maxlength=2) private int appointmentMonth;
    @Validate(required=true, maxlength=2) private int appointmentDay;
    @Validate(required=true, maxlength=4) private int appointmentYear;
	@Validate(required=false, maxlength=255) private String description;
	@Validate(required=false)private boolean reminder;
	
	//GETTERS AND SETTERS
    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
    
    public boolean isReminder() { return reminder;	}
	public void setReminder(boolean reminder) {	this.reminder = reminder;	}

	public int getAppointmentDay() { return appointmentDay; }
	public void setAppointmentDay(int appointmentDay) {	this.appointmentDay = appointmentDay;	}
	
	public int getAppointmentYear() { return appointmentYear;	}
	public void setAppointmentYear(int appointmentYear) {this.appointmentYear = appointmentYear;	}
	
	public int getAppointmentMonth(){	return appointmentMonth;	}
	public void setAppointmentMonth(int appointmentMonth) {	this.appointmentMonth = appointmentMonth;}
	
	public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    /**
     * The DateFormat() ensures the inputed date is valid and 
     * checks to make sure there are no illegal characters
     * in description.
     * 
     * @param errors
     * 
     * Taylor Evans
     */
    @ValidationMethod(on="submit")
    public void dateFormat(ValidationErrors errors) 
    {
    	if(appointmentMonth < 1 || appointmentMonth > 12)
    		errors.add("appointmentMonth", new SimpleError("Invalid Appointment Month"));
    	if(appointmentDay < 1 || appointmentDay > 31)
    		errors.add("appointmentDay", new SimpleError("Invalid Appointment Day"));
    	if(appointmentYear < 2009)
    		errors.add("appointmentYear", new SimpleError("Invalid Appointment Year"));
    	if (hasSpecialCharacters(description))
	    	errors.add("description", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
    }
    
    /**
     * hasSpecialCharacters() checks for all illegal characters 
     * in the inputed string.
     * 
     * @param s
     * @return
     * 
     * Alex Bassett
     */
    private boolean hasSpecialCharacters(String s) {
		if (s != s.replaceAll("([^A-Za-z0-9.,!?~`'\"% _-]+)", "")) return true;
		return false;
	}
    
    /**
     * Resolution submit() adds a new appointment or
     * updates an existing appointment depending on 
     * the idea it receives
     * @return
     * 
     * Taylor Evans
     */
    @DefaultHandler
    public Resolution submit() {
    	int appointmentID = 4;
    	Calendar cal = Calendar.getInstance();
		cal.set(this.appointmentYear, this.appointmentMonth-1, this.appointmentDay);
		Date date = new Date(cal.getTime().getTime());
    	if(appointmentID == 0)
    	{
    		AppointmentSQL.addAppointment(date, description, reminder);
    	}
    	else
    	{
    		AppointmentSQL temp = new AppointmentSQL();
    		temp.updateAppointment(appointmentID, date, description, reminder);
    	}
    	return new ForwardResolution("patientHome.jsp");
    }
}