/**********************************************************
* File: core.EditAppointmentBean.java
* Author: Taylor Evans
* Date Created: 4/27/2009
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

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.validation.Validate;

public class EditAppointmentBean implements ActionBean {
 
	//Private data members
	private ActionBeanContext context;
	int appointmentID;
    @Validate(required=false) private int appointmentMonth;
    @Validate(required=false) private int appointmentDay;
    @Validate(required=false) private int appointmentYear;
	@Validate(required=false, maxlength=255) private String description;
	@Validate(required=false)private boolean reminder;

	//GETTERS AND SETTERS
    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
    
    public int getAppointmentID(){return appointmentID;}
    public void setAppointmentID(int appointmentID) { this.appointmentID = appointmentID;}
    
    public boolean getReminder() {	return reminder;	}
	public void setReminder(boolean reminder) {	this.reminder = reminder;	}
	
	public int getAppointmentDay() {	return appointmentDay;	}
	public void setAppointmentDay(int appointmentDay) {	this.appointmentDay = appointmentDay;	}
	
	public int getAppointmentYear() {	return appointmentYear;	}
	public void setAppointmentYear(int appointmentYear) {	this.appointmentYear = appointmentYear;	}
	
	public int getAppointmentMonth(){	return appointmentMonth;	}
	public void setAppointmentMonth(int appointmentMonth) {	this.appointmentMonth = appointmentMonth; }
	
	public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    /**
     * Looks for the inputed appointment and loads the information
     * into the bean variables.
     * @return
     * 
     * Taylor Evans
     */
    @DefaultHandler
    public Resolution submit() {
    	AppointmentSQL temp = new AppointmentSQL();
    	boolean loaded = temp.lookupAppointment(appointmentID);
    	if(loaded == true){	
    		Date date = temp.getDate();
    		String s = date.toString();
    		int index = s.indexOf('-');
    		appointmentYear = Integer.parseInt(s.substring(0, index));
    		s = s.substring(index+1, s.length());
    		index = s.indexOf('-');
    		appointmentMonth = Integer.parseInt(s.substring(0,index));
    		appointmentDay = Integer.parseInt(s.substring(index + 1, s.length()));
    		description = temp.getDescription();
    		if(description.equals("null")) { description = ""; }
    		reminder = temp.isReminder();
    		System.out.println(reminder);
    	}
        return new ForwardResolution("editAppointment.jsp");
    }
    
    /**
     * deletes the appointment specified by the appointmentID
     * from the mySQL database
     * 
     * @author Han Dong
     */
    public Resolution delete()
    {
    	AppointmentSQL.deleteAppointment(this.appointmentID);
    	return new ForwardResolution("patientList.jsp");
    }
    
}