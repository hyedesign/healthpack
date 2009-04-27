/**********************************************************
* File: core.AllergiesSQL.java
* Author: Taylor Evans
* Date Created: 4/24/2009
*
* Description: The AllergiesSQL class works with 
*              AllergiesBean to connect to the Database
*
* Edited  : 4/24/2009 by Taylor Evans
*
**********************************************************/

package core;

import java.sql.*;

public class AllergiesSQL 
{

	private DBAccess dba; 
	
	private int patientID;
	private String allergyName;
	private String description;
	
	public AllergiesSQL()
	{
		dba = new DBAccess();
		patientID = -1;
		allergyName = "";
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
	public String getAllergyName() {
		return allergyName;
	}
	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public static void addAllergies(String name, String descript)
	{
		DBAccess dba_s = new DBAccess();
		dba_s.connect(); // connect to the database
		try {
			int pID = 9;
			// construct and execute the SQL call, retrieve the results
			descript = descript.replaceAll("\\'", "''");
			String s = "INSERT INTO allergies (patientid, allergy_name, allergy_description) " +
			"VALUES ("+ pID +", '" + name + "', '" + descript + "')";
			Statement statement = dba_s.connection.createStatement ();
			statement.executeUpdate (s);
			
			// close connections
			statement.close();
			dba_s.disconnect();
			
		} catch (SQLException e) {
            System.err.println ("Method AllergiesSQL.addAllergies() performed bad SQL call");
            System.err.println (e.toString());
			dba_s.disconnect();
		}
		
	}

}
