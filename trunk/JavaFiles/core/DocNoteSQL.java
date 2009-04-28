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
		userID = -1;
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
	
	public boolean lookupDocNote(int id) {
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			ResultSet results = statement.executeQuery ("SELECT * FROM doctorpatient WHERE doctorpatientid='"+id+"'");
			
			// attempt to load the user from the ResultSet
			boolean successfulLoad = loadDocNote(results);
			
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
	 * @author Jon Conti-Vock
	 */
	private boolean loadDocNote(ResultSet rs) {
		// check that the resultset isn't empty and load the user data
		try {
			if (rs.first()) {
				userID = rs.getInt("userid");
				patientID = rs.getInt("patientID");
				description = rs.getString("notedescription");
				return true;
			}
			// the resultset is empty, no data loaded
			else return false;
		} catch (SQLException e) {
			System.err.println("Bad ResultSet call from DocNoteSQL.loadDocNote()");
            System.err.println (e.toString());
			return false;
		}
	}

	public static void updateDocNote(int id, String description2) {
		DBAccess dba = new DBAccess();
		dba.connect(); // connect to the database
		try
		{
			Statement statement = dba.connection.createStatement();
			if (description2 != null)
			{
				statement.executeUpdate("UPDATE doctorpatient SET notedescription='"+description2+"' " +
						"WHERE doctorpatientid='"+id+"'");
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