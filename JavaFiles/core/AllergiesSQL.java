/**********************************************************
* File: core.AllergiesSQL.java
* Author: Taylor Evans
* Date Created: 4/24/2009
*
* Description: The AllergiesSQL class works with 
*              AllergiesBean to connect to the Database
*
* Edited  : 4/28/2009 by Taylor Evans
*
**********************************************************/

package core;

import java.sql.*;

public class AllergiesSQL 
{

	//private data members
	private DBAccess dba; 
	
	private int allergyID;
	private int patientID;
	private String allergyName;
	private String description;
	
	//Constructor
	public AllergiesSQL()
	{
		dba = new DBAccess();
		allergyID = -1;
		patientID = -1;
		allergyName = "";
		description = "";
	}

	//GETTERS AND SETTERS
	public int getAllergyID() {	return allergyID;	}
	public void setAllergyID(int allergyID) {	this.allergyID = allergyID;	}
	
	public int getPatientID() {	return patientID;	}
	public void setPatientID(int patientID) {	this.patientID = patientID;	}
	
	public DBAccess getDba() {	return dba;	}
	public void setDba(DBAccess dba) {	this.dba = dba;	}
	
	public String getAllergyName() {	return allergyName;	}
	public void setAllergyName(String allergyName) {	this.allergyName = allergyName;	}
	
	public String getDescription() {	return description;	}
	public void setDescription(String description) {	this.description = description;	}

	/**
	 * addAllergy takes in the inputed data fields and adds
	 * a new allergy entry into the database.
	 * 
	 * @param name
	 * @param descript
	 */
	public static void addAllergies(int pID, String name, String descript)
	{
		DBAccess dba_s = new DBAccess();
		dba_s.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			descript = descript.replaceAll("\\'", "''");
			name = name.replaceAll("\\'", "''");
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
	
	/**
	 * lookupAllergy looks in the database for the allergy
	 * corresponding to the id inputed. It attempts to load the information
	 * if the entry is found. Returns false otherwise.
	 * 
	 * @param id
	 * @return
	 * 
	 * Taylor Evans
	 */
	public boolean lookupAllergy(int id) {
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			ResultSet results = statement.executeQuery ("SELECT * FROM allergies WHERE allergyid='"+id+"'");
			
			// attempt to load the user from the ResultSet
			boolean successfulLoad = loadAllergy(results);
			
			// close connections
			results.close();
			statement.close();
			dba.disconnect();
			//return true if user was found
			return successfulLoad;
			
		} catch (SQLException e) {
            System.err.println ("Method lookupAllergy() performed bad SQL call");
            System.err.println (e.toString());
			dba.disconnect();
            return false;
		}
	}
	
	/**
	 * loadAllergy attempts to load the allergy from the 
	 * database into the SQL file.
	 * @param rs
	 * @return
	 * 
	 * Taylor Evans
	 */
	private boolean loadAllergy(ResultSet rs) {
		// check that the resultset isn't empty and load the user data
		try {
			if (rs.first()) {
				allergyID = rs.getInt("allergyid");
				patientID = rs.getInt("patientID");
				allergyName = rs.getString("allergy_name");
				description = rs.getString("allergy_description");
				return true;
			}
			// the resultset is empty, no data loaded
			else return false;
		} catch (SQLException e) {
			System.err.println("Bad ResultSet call from AllergySQL.loadAllergy()");
            System.err.println (e.toString());
			return false;
		}
	}
	
	/**
	 * updateAllergy takes the inputed data fields and updates 
	 * the corresponding allergy in the database.
	 * 
	 * @param id
	 * @param allergy
	 * @param descript
	 */
	public void updateAllergy(int id, String allergy, String descript)
	{	
		dba.connect(); // connect to the database
		try
		{
			descript = descript.replaceAll("\\'", "''");
			allergy = allergy.replaceAll("\\'", "''");
			Statement statement = dba.connection.createStatement();
			statement.executeUpdate("UPDATE allergies SET allergy_name='"+allergy+
						"', allergy_description='"+descript+"' WHERE allergyid='"
						+id+"'");
			statement.close();
			dba.disconnect();
		}
		catch (SQLException e)
		{
			System.err.println (e.toString());
			System.out.println("Failed to update in AllergySQL.java.");
			dba.disconnect();
		}
	}

}
