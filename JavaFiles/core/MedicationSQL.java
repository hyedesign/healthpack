/**********************************************************
* File: core.MedicationSQL.java
* Author: Jon Conti-Vock
* Date Created: 4/26/2009
*
* Description: The NewMedication class handles adding a medication to
*              the patient's medication table in the HealthPack database.
*
**********************************************************/

package core;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class MedicationSQL {
   
	/*
	 * PRIVATE VARIABLES
	 */
	private DBAccess dba = new DBAccess();
	
    //DB variables
	private int medicationId;
	private int patientId;
	private String medicationName = "";
	private Date medicationExpirationDate = new Date(000000);
	private Date medicationRefillDate = new Date(000000);
	private String medicationDescription = 	"";
    
	/*
	 * CONSTRUCTORS - MAKE IT COMPATIBLE WITH DB
	 */
    public MedicationSQL() {}	
	
	public MedicationSQL(int medicationId, int patientId, String medicationName,
			Date medicationExpirationDate, Date medicationRefillDate, String medicationDescription) {
		this.patientId = patientId;
		this.medicationId = medicationId;
		this.medicationName = medicationName;
		this.medicationExpirationDate = medicationExpirationDate;
		this.medicationRefillDate = medicationRefillDate;
		this.medicationDescription = medicationDescription;
	}
	
	//GETTERS AND SETTERS
	public int getMedicationId() {
		return medicationId;
	}
	public void setMedicationId(int medicationId) {
		this.medicationId = medicationId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getMedicationName() {
		return medicationName;
	}
	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}
	public Date getMedicationExpirationDate() {
		return medicationExpirationDate;
	}
	public void setMedicationExpirationDate(Date medicationExpirationDate) {
		this.medicationExpirationDate = medicationExpirationDate;
	}
	public Date getMedicationRefillDate() {
		return medicationRefillDate;
	}
	public void setMedicationRefillDate(Date medicationRefillDate) {
		this.medicationRefillDate = medicationRefillDate;
	}
	public String getMedicationDescription() {
		return medicationDescription;
	}
	public void setMedicationDescription(String medicationDescription) {
		this.medicationDescription = medicationDescription;
	}

	//SQL CALLS
/*	private boolean deleteMedication(){
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
						
			statement.executeUpdate("DELETE FROM cmsc345.medications" +
					" WHERE patientid = '" + this.patientId +"' AND medicationid = '"+this.medicationId+"' ");
			
			statement.close();
			dba.disconnect();
			//return true if user was found
			return true;
		} catch (SQLException e) {
            System.err.println ("Error in Deleting a patient");
            System.err.println (e.toString());
			dba.disconnect();
			return false;
		}		
	
		
	}
*/	
	
	/*
	 * Static Calls
	 */
	
	/**
	 * addNewMedication takes in the input data fields and adds
	 * a new medication entry into the database.
	 * 
	 * @param patientId
	 * @param medicationName
	 * @param medicationExpirationDate
	 * @param medicationRefillDate
	 * @param medicationDescription
	 * 
	 * Jon Conti-Vock
	 */
	public static void addNewMedication(int patientId, String medicationName, 
			Date medicationExpirationDate, Date medicationRefillDate, 
			String medicationDesription) {
		DBAccess dba = new DBAccess();
		dba.connect(); // connect to the database
		
		try {
			// construct and execute the SQL call
			Statement statement = dba.connection.createStatement ();
			String s = "INSERT INTO medications " + "" +
			" (patientid, medicationname, medicationexpirationsdate, medicationrefilldate, medicationdescription) " + 
			" VALUES('"+patientId+"', '"+medicationName+"', '"+medicationExpirationDate+"', '"
			+medicationRefillDate+"', '"+medicationDesription+"')";
			statement.executeUpdate(s);
			
			// close connections
			statement.close();
			dba.disconnect();
		} catch (SQLException e) {
            System.err.println ("Error in addNewMedication (AddMedicationBean.java)");
            System.err.println (e.toString());
			dba.disconnect();
		}
	}
	
	/**
	 * editMedication takes in the input data fields and edits
	 * a medication entry in the database.
	 * 
	 * @param patientId
	 * @param medicationName
	 * @param medicationExpirationDate
	 * @param medicationRefillDate
	 * @param medicationDescription
	 * 
	 * Jon Conti-Vock
	 */
	private static void editMedication(int patientId, String medicationName, 
			Date medicationExpirationDate, Date medicationRefillDate, 
			String medicationDesription) {
		DBAccess dba = new DBAccess();
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			String s = "INSERT INTO medications " + "" +
			" (patientId, medicationName, medicationExpirationDate, medicationRefillDate, medicationDescription) " + 
			" VALUES('"+patientId+"', '"+medicationName+"', '"+medicationExpirationDate+"', '"
			+medicationRefillDate+"', '"+medicationDesription+"')";
			statement.executeUpdate(s);
			
			// close connections
			statement.close();
			dba.disconnect();
			
		} catch (SQLException e) {
            System.err.println ("Error in editMedication where id = " + patientId);
            System.err.println (e.toString());
			dba.disconnect();
		}
	}	
/*
	public static void DeleteMedication(int id){
		DBAccess dba = new DBAccess();
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			String s = "DELETE FROM medications " + "" +
			" (patientId, medicationName, medicationExpirationDate, medicationRefillDate, medicationDescription) " + 
			" VALUES('"+patientId+"', '"+medicationName+"', '"+medicationExpirationDate+"', '"
			+medicationRefillDate+"', '"+medicationDesription+"')";
			statement.executeUpdate(s);
			
			// close connections
			statement.close();
			dba.disconnect();
			
		} catch (SQLException e) {
            System.err.println ("Error in looking up patient where id = " +id);
            System.err.println (e.toString());
			dba.disconnect();
		}
	}
*/

	/**
	 * Looks up the medication ID in the database.
	 * The result set must be the result of a query to the medications
	 * table.
	 * 
	 * @param successfulLoad A boolean that tells whether the load was successful or not.
	 * @return true when the data is loaded, false when the ResultSet is
	 * empty and there is no data to load
	 * 
	 * @author Jon Conti-Vock
	 */
	public boolean lookupMedication(int medicationID) {
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			ResultSet results = statement.executeQuery ("SELECT * FROM medications WHERE medicationid=" + medicationID);
			
			// attempt to load the user from the ResultSet
			boolean successfulLoad = loadMedication(results);
			
			// close connections
			results.close();
			statement.close();
			dba.disconnect();
			//return true if medication was found
			return successfulLoad;
			
		} catch (SQLException e) {
            System.err.println ("Method login() performed bad SQL call");
            System.err.println (e.toString());
			dba.disconnect();
            return false;
		}
	}
	
	/**
	 * Takes a ResultSet object and loads the medication data from it.
	 * The result set must be the result of a query to the medications
	 * table.
	 * 
	 * @param rs the result set that contains a single medication's data
	 * @return true when the data is loaded, false when the ResultSet is
	 * empty and there is no data to load
	 * 
	 * @author Jon Conti-Vock
	 */
	private boolean loadMedication(ResultSet rs) {
		// check that the resultset isn't empty and load the medication data
		try {
			if (rs.first()) {
				patientId = rs.getInt("patientid");
				medicationName = rs.getString("medicationname");
				medicationExpirationDate = rs.getDate("medicationexpirationsdate");
				medicationRefillDate = rs.getDate("medicationrefilldate");
				medicationDescription = rs.getString("medicationdescription");
				return true;
			}
			// the resultset is empty, no data loaded
			else return false;
		} catch (SQLException e) {
			System.err.println("Bad ResultSet call from MedicationSQL.loadMedication()");
            System.err.println (e.toString());
			return false;
		}
	}
	
	/**
	 * Updates a medication in the database which is specified by the medication ID.
	 * 
	 * @param medicationid the unique ID of a single medication's data
	 * @param patientid the unique ID of a single patient's data
	 * @param medicationName the medication's name
	 * @param expire the medication's expiration date
	 * @param refill the medication's refill date
	 * @param description the description of a medication
	 * 
	 * @author Jon Conti-Vock
	 */
	public static void updateMedication(int medicationid, int patientid, String medicationName, Date expire, Date refill, String descript)
	{	
		DBAccess dba = new DBAccess();
		dba.connect(); // connect to the database
		try
		{
			Statement statement = dba.connection.createStatement();
			String s = "UPDATE medications SET medicationname='"+medicationName+"', medicationdescription='"+descript+"' " + " WHERE medicationid=" + medicationid;
			statement.executeUpdate(s);
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
	
	/**
	 * deletes a medication from the 'medications' table in the mySQL database
	 * @param medicationId is the id of the medication
	 * @author Han Dong
	 */
	public static void deleteMedication(int medicationId)
	{
		DBAccess dba_s = new DBAccess();
		dba_s.connect(); // connect to the database
		try
		{	
			// construct and execute the SQL call, deletes the allergy
			Statement statement = dba_s.connection.createStatement();
			statement.executeUpdate("DELETE FROM cmsc345.medications" +
					" WHERE medicationid='"+medicationId+"';");
			statement.close();
			dba_s.disconnect();
		}
		catch (SQLException e)
		{
			System.err.println (e.toString());
			dba_s.disconnect();
		}
	}

}