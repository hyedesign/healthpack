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

public class EditAppointmentBean implements ActionBean {
    private ActionBeanContext context;

    @Validate(required=false) private int appointmentMonth;
    @Validate(required=false) private int appointmentDay;
    @Validate(required=false) private int appointmentYear;
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
	public int getAppointmentDay() {
		return appointmentDay;
	}
	public void setAppointmentDay(int appointmentDay) {
		this.appointmentDay = appointmentDay;
	}
	public int getAppointmentYear() {
		return appointmentYear;
	}
	public void setAppointmentYear(int appointmentYear) {
		this.appointmentYear = appointmentYear;
	}
	public int getAppointmentMonth(){
		return appointmentMonth;
	}
	public void setAppointmentMonth(int appointmentMonth) {
		this.appointmentMonth = appointmentMonth;
	}
	
	public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    
    @DefaultHandler
    public Resolution submit() {
    	int appointmentID = 4;
    	AppointmentSQL temp = new AppointmentSQL();
    	boolean loaded = temp.lookupAppointment(appointmentID);
    	if(loaded == true){	
    		Date date = temp.getDate();
    		String s = date.toString();
    		int index = s.indexOf('-');
    		Integer i = new Integer(0);
    		appointmentYear = i.parseInt(s.substring(0, index));
    		s = s.substring(index+1, s.length());
    		index = s.indexOf('-');
    		appointmentMonth = i.parseInt(s.substring(0,index));
    		appointmentDay = i.parseInt(s.substring(index + 1, s.length()));
    		description = temp.getDescription();
    		reminder = temp.isReminder();
    	}
        return new ForwardResolution("editAppointment.jsp");
    }
    
    
}