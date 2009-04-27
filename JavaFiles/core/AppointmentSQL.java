/**********************************************************
* File: core.AppointmentSQL.java
* Author: Taylor Evans
* Date Created: 4/24/2009
*
* Description: The AppointmentSQL class works with 
*              AppointmentBean to connect to the Database
*
* Edited  : 4/24/2009 by Taylor Evans
*
**********************************************************/

package core;

import java.sql.*;

public class AppointmentSQL {
	
	private DBAccess dba; 
	
	private int patientID;
	private int appointmentID;
	private Date date;
	private String description;
	private boolean reminder;
	
	public AppointmentSQL()
	{
		dba = new DBAccess();
		patientID = -1;
		appointmentID = -1;
		date = new Date(0,0,0);
		description = "";
		reminder = false;
	}

	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public int getAppointmentID() {
		return appointmentID;
	}
	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isReminder() {
		return reminder;
	}
	public void setReminder(boolean reminder) {
		this.reminder = reminder;
	}
	
	public static void addAppointment(String month,String day, String year, String descript, boolean remind)
	{
		DBAccess dba_s = new DBAccess();
		dba_s.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			String remember = "0";
			if(remind == true)
				remember = "1";
			//Date date = new Date(year,month,day);
			String s = "INSERT INTO appointments (appointmentdate, appointmentdescription, appointmentreminder," +
			"VALUES (" + year+"-"+month+"-"+day + ", '" + descript + "', " + remember + ")";
			
			Statement statement = dba_s.connection.createStatement ();
			statement.executeUpdate (s);
			
			// close connections
			statement.close();
			dba_s.disconnect();
			
		} catch (SQLException e) {
            System.err.println ("Method AppointmentSQL.addAppointment() performed bad SQL call");
            System.err.println (e.toString());
			dba_s.disconnect();
		}
		
	}

}
