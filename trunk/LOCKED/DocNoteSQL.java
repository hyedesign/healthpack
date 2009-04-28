/**********************************************************
* File: core.DocNoteSQL.java
* Author: Jon Conti-Vock
* Date Created: 4/27/2009
*
* Description: The DocNoteSQL class works with 
*              DocNoteBean to connect to the Database
*
*
**********************************************************/

package core;

import java.sql.*;

public class DocNoteSQL 
{

	private DBAccess dba; 
	
	private int userID;
	private int patientID;
	private String description;
	
	public DocNoteSQL()
	{
		dba = new DBAccess();
		patientID = -1;
		description = "";
	}

	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public DBAccess getDba() {
		return dba;
	}
	public void setDba(DBAccess dba) {
		this.dba = dba;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public static void addDocNote(int userid, int patientid, String note)
	{
		DBAccess dba_s = new DBAccess();
		dba_s.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			note = note.replaceAll("\\'", "''");
			String s = "INSERT INTO doctorpatient (userid, patientid, notedescription) " +
			"VALUES ('" + userid + "', '"+ patientid +"', '" + note + "')";
			Statement statement = dba_s.connection.createStatement ();
			statement.executeUpdate (s);
			
			// close connections
			statement.close();
			dba_s.disconnect();
			
		} catch (SQLException e) {
            System.err.println ("Method DocNoteSQL.addAllergies() performed bad SQL call");
            System.err.println (e.toString());
			dba_s.disconnect();
		}
		
	}

}