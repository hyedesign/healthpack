
/**********************************************************
* File: core.MedicationSQL.java
* Author: Jon Conti-Vock
* Date Created: 4/26/2009
*
* Description: The Medication class handles loading and updating
*              the patient's medication table in the HealthPack database.
*
**********************************************************/

package core;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

	/*  What does this do? */
	public MedicationSQL(int patID) {
		if (patID != 0){
			lookupPatient(patID);
		}else this.patientId = patID;
	}
	
	//GETTERS AND SETTERS
	

	//SQL CALLS
	private String lookupMedicationNamebyID(int id){
		String returnString;
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			ResultSet results = statement.executeQuery ("SELECT patientfirstname, " +
					"patientmiddlename, patientlastname  " +
					"FROM patients WHERE patientid='"+id+"'");
			
			// attempt to load the Medication from the ResultSet
			if (results.first()) {
				String firstName = results.getString("patientFirstName");
				String lastName= results.getString("patientLastName");
				returnString = firstName + " " + lastName;
			}else{
				returnString = "No Medication Found";
			}
			
			// close connections
			results.close();
			statement.close();
			dba.disconnect();
			//return true if user was found
			
			return returnString;
		} catch (SQLException e) {
            System.err.println ("Error in lookupPatientNamebyID (Medication.java)");
            System.err.println (e.toString());
			dba.disconnect();
			return "Error";
		}
	}
	
	private boolean insertMedication (){
		dba.connect(); // connect to the database
		try {
							
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			
			statement.executeUpdate("INSERT INTO cmsc345.medications" +
					" VALUES ( 0 ,"+userId+",'"+firstName+"','"+lastName+"','"
					+middleName+"','"+this.patientDOB+"',"+weight+","+height+","+this.patientSex+",'"
					+emergencyContactName+"','"	+emergencyContactPhone+"','"+insurance+"','"
					+insuranceID+"','"+SSN+"');");
			
			statement.close();
			dba.disconnect();
			//return true if user was found
			
			return true;
		} catch (SQLException e) {
            System.err.println ("Error in inserting medication");
            System.err.println (e.toString());
			dba.disconnect();
			return false;
		}
	}
	
	private boolean updateMedications(){
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			System.out.println("\n*****************\nupdating .....\n");
			
			
			System.out.println("UPDATE cmsc345.medications" +
					" SET 	 patientfirstname='"+this.firstName+"', " +
							"patientlastname'"+this.lastName+"', " +
							"patientmiddlename='"+this.middleName+"', " +
							"patientdob='"+this.patientDOB+"', " +
							"patientweight="+this.weight+", " +
							"patientheight="+this.height+", " +
							"patientsex="+this.patientSex+", "+
							"patientemergencycontactname='"+this.emergencyContactName+"', " +
							"patientemergencycontactnumber='"+this.emergencyContactPhone+"', " +
							"patientinsuranceprovider='"+this.insurance+"', " +
							"patientinsuranceid='"+this.insuranceID+"'," +
							"patientssn='"+this.SSN+"' " +
					"WHERE patientid = " + this.patientId +";");
			
			statement.executeUpdate("UPDATE cmsc345.medications" +
					" SET 	 patientfirstname='"+this.firstName+"', " +
							"patientlastname='"+this.lastName+"', " +
							"patientmiddlename='"+this.middleName+"', " +
							"patientdob='"+this.patientDOB+"', " +
							"patientweight="+this.weight+", " +
							"patientheight="+this.height+", " +
							"patientsex="+this.patientSex+", "+
							"patientemergencycontactname='"+this.emergencyContactName+"', " +
							"patientemergencycontactnumber='"+this.emergencyContactPhone+"', " +
							"patientinsuranceprovider='"+this.insurance+"', " +
							"patientinsuranceid='"+this.insuranceID+"'," +
							"patientssn='"+this.SSN+"' " +
					"WHERE patientid = " + this.patientId +";");
			
			statement.close();
			dba.disconnect();
			//return true if user was found
			return true;
		} catch (SQLException e) {
            System.err.println ("Error in Updating medication where patient id = " + this.patientId + " and medication id = " + this.medicationid);
            System.err.println (e.toString());
			dba.disconnect();
			return false;
		}		
	}
	
	private boolean deleteMedication(){
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
						
			statement.executeUpdate("DELETE FROM cmsc345.medications" +
					" WHERE patientid = '" + this.patientId +"' AND medicationid = '"+this.medicationid+"' ";");
			
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
	
	private boolean loadPatientData (ResultSet rs) {
		// check that the resultset isn't empty and load the patient data
		try {
			if (rs.first()) {
					patientId = rs.getInt("patientid");
					userId = rs.getInt("userid");
					firstName = rs.getString("patientfirstname");
					lastName = rs.getString("patientlastname");
					middleName = rs.getString("patientmiddlename");
					patientDOB = rs.getDate("patientdob");
					height = rs.getInt("patientheight");
					weight = rs.getInt("patientweight");
					patientSex = rs.getInt("patientsex");
					emergencyContactName = rs.getString("patientemergencycontactname");
					emergencyContactPhone = rs.getString("patientemergencycontactnumber");
					insurance = rs.getString("patientinsuranceprovider");
					insuranceID = rs.getString("patientinsuranceid");
					SSN = rs.getString("patientssn");
					return true;
				}else return false;
			} catch (SQLException e) {
				System.err.println("Error in loadPatientData (Patient.java)");
	            System.err.println (e.toString());
				return false;
			}
	}
	
	/*
	 * Static Calls
	 */
	public static ArrayList<Integer> lookupPatientsByUserID(int UserId) {
		DBAccess dba = new DBAccess();
		dba.connect(); // connect to the database
		
		try {
			ArrayList<Integer> arrayOfIds = new ArrayList<Integer>();
			
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			ResultSet results = statement.executeQuery ("SELECT patientid FROM patients WHERE userid='"+UserId+"'");
			
			// attempt to load the patient from the ResultSet
			while (results.next()){
				arrayOfIds.add(results.getInt("patientid"));

				System.out.println("patient: " + arrayOfIds.get(arrayOfIds.size() - 1));
			}
			
			
			// close connections
			results.close();
			statement.close();
			dba.disconnect();
			//return true if user was found
			return arrayOfIds;
			
		} catch (SQLException e) {
            System.err.println ("Error in lookupPatientsByUserID (Patient.java)");
            System.err.println (e.toString());
			dba.disconnect();
            return null;
		}
	}
	
	private boolean lookupPatient(int id) {
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			ResultSet results = statement.executeQuery ("SELECT * FROM patients WHERE patientid='"+id+"'");
						
			// attempt to load the patient from the ResultSet
			boolean successfulLoad = loadPatientData(results);
			
			// close connections
			results.close();
			statement.close();
			dba.disconnect();
			//return true if user was found
			return successfulLoad;
			
		} catch (SQLException e) {
            System.err.println ("Error in looking up patient where id = " +id);
            System.err.println (e.toString());
			dba.disconnect();
            return false;
		}
	}	
	
	/*
	 * PUBLIC METHODS
	 */

	public String PatientName(){
		return this.firstName + " " + this.middleName + " " + this.lastName;
	}
	public static String PatientNameFromID(int ID){
		return new PatientSQL().lookupPatientNamebyID(ID);
	}
	public boolean CreateNewPatient(){
		return insertPatient();
	}
	public boolean UpdateCurrentPatient(){
		return updatePatient();
	}
	public boolean SetAndUpdateCurrentPatient(String firstName,
			String lastName, String middleName, Date patientDOB, int height,
			int weight, int patientSex, String emergencyContactName,
			String emergencyContactPhone, String insurance, String insuranceID,
			String ssn){
		System.out.println("patientID = " + this.patientId);
		System.out.println("userID = " + this.userId);
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.patientDOB = patientDOB;
		this.height = height;
		this.weight = weight;
		this.patientSex = patientSex;
		this.emergencyContactName = emergencyContactName;
		this.emergencyContactPhone = emergencyContactPhone;
		this.insurance = insurance;
		this.insuranceID = insuranceID;
		SSN = ssn;
		return updatePatient();
	}
	public static boolean DeletePatient(int id){
		return new PatientSQL(id).deletePatient();
	}
}