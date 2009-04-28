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
	
	public static void addAppointment(Date date, String descript, boolean remind)
	{
		DBAccess dba_s = new DBAccess();
		dba_s.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			int pID = 9;
			String remember = "0";
			if(remind == true)
				remember = "1";
			
			descript = descript.replaceAll("\\'", "''");
			
			String s = "INSERT INTO appointments (patientid, appointmentdate, appointmentdescription, appointmentreminder)" +
			"VALUES (" + pID + ", '"+ date + "', '" + descript + "', " + remember + ")";
			System.out.println(s);
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
	
	public boolean lookupAppointment(int id) {
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			ResultSet results = statement.executeQuery ("SELECT * FROM appointments WHERE appointmentid='"+id+"'");
			
			// attempt to load the user from the ResultSet
			boolean successfulLoad = loadAppointment(results);
			
			// close connections
			results.close();
			statement.close();
			dba.disconnect();
			//return true if user was found
			return successfulLoad;
			
		} catch (SQLException e) {
            System.err.println ("Method login() performed bad SQL call");
            System.err.println (e.toString());
			dba.disconnect();
            return false;
		}
	}
	
	/**
	 * Takes a ResultSet object and loads the user data from it.
	 * The result set must be the result of a query to the users
	 * table.
	 * 
	 * @param rs the result set that contains a single user's data
	 * @return true when the data is loaded, false when the ResultSet is
	 * empty and there is no data to load
	 * @author Alex Bassett
	 */
	private boolean loadAppointment(ResultSet rs) {
		// check that the resultset isn't empty and load the user data
		try {
			if (rs.first()) {
				appointmentID = rs.getInt("appointmentid");
				patientID = rs.getInt("patientID");
				date = rs.getDate("appointmentdate");
				description = rs.getString("appointmentdescription");
				int bool = rs.getInt("appointmentreminder");
				if(bool == 0)
					reminder = false;
				else reminder = true;
				return true;
			}
			// the resultset is empty, no data loaded
			else return false;
		} catch (SQLException e) {
			System.err.println("Bad ResultSet call from UserSQL.loadUserData()");
            System.err.println (e.toString());
			return false;
		}
	}
	
	public void updateAppointment(int id, String allergy, String descript)
	{	
		dba.connect(); // connect to the database
		try
		{
			Statement statement = dba.connection.createStatement();
			if(description == null)
			{
				statement.executeUpdate("UPDATE appointment SET allergy_name='"+allergy+"' WHERE allergyid='"
						+id+"'");
			}
			else
			{
				statement.executeUpdate("UPDATE allergies SET allergy_name='"+allergy+
						"', allergy_description='"+descript+"' WHERE allergyid='"
						+id+"'");
			}
			statement.close();
			dba.disconnect();
		}
		catch (SQLException e)
		{
			System.err.println (e.toString());
			System.out.println("Failed to update.");
			dba.disconnect();
		}
	}

}
