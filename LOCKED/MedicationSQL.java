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
	
	public MedicationSQL(int patientId, int medicationId, String medicationName,
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
	private boolean deleteMedication(){
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
	
	/*
	 * Static Calls
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
}