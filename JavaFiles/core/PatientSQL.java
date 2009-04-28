/**********************************************************
* File: core.Patient.java
* Author: Vahan Kristosturyan
* Date Created: 4/19/2009
*
* Description: The Patient class handles loading and updating
*              the patient table in the HealthPack database.
*
**********************************************************/

package core;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PatientSQL {
   
	/*
	 * PRIVATE VARIABLES
	 */
	private DBAccess dba = new DBAccess();
	
    //DB variables
	private int patientId;
	private int userId;
	private String firstName = "";
	private String lastName = "";
	private String middleName = "";
	private Date patientDOB = new Date(000000);
	private int height = 0;
	private int weight = 0;
	private int patientSex = 0; //0 for male, 1 for female
	private String emergencyContactName = "";
	private String emergencyContactPhone = "";
	private String insurance = "";
	private String insuranceID = "";
	private String SSN = "";
	
    
	/*
	 * CONSTRUCTORS - MAKE IT COMPATIBLE WITH DB
	 */
    public PatientSQL() {}	
	
	public PatientSQL(int patientId, int userId, String firstName,
			String lastName, String middleName, Date patientDOB, int height,
			int weight, int patientSex, String emergencyContactName,
			String emergencyContactPhone, String insurance, String insuranceID,
			String ssn) {
		this.patientId = patientId;
		this.userId = userId;
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
	}

	public PatientSQL(int patID) {
		if (patID != 0){
			lookupPatient(patID);
		}else this.patientId = patID;
	}
	
	//GETTERS AND SETTERS
	public int getPatientId() {
		return patientId;
	}
	public int getUserId() {
		return userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public Date getPatientDOB() {
		return patientDOB;
	}
	public int getHeight() {
		return height;
	}
	public int getWeight() {
		return weight;
	}
	public int getPatientSex() {
		return patientSex;
	}
	public String getEmergencyContactName() {
		return emergencyContactName;
	}
	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}
	public String getInsurance() {
		return insurance;
	}
	public String getInsuranceID() {
		return insuranceID;
	}
	public String getSSN() {
		return SSN;
	}

	//SQL CALLS
	private String lookupPatientNamebyID(int id){
		String returnString;
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			ResultSet results = statement.executeQuery ("SELECT patientfirstname, " +
					"patientmiddlename, patientlastname  " +
					"FROM patients WHERE patientid='"+id+"'");
			
			// attempt to load the patient from the ResultSet
			if (results.first()) {
				String firstName = results.getString("patientFirstName");
				String lastName= results.getString("patientLastName");
				returnString = firstName + " " + lastName;
			}else{
				returnString = "No Patient Found";
			}
			
			// close connections
			results.close();
			statement.close();
			dba.disconnect();
			//return true if user was found
			
			return returnString;
		} catch (SQLException e) {
            System.err.println ("Error in lookupPatientNamebyID (Patient.java)");
            System.err.println (e.toString());
			dba.disconnect();
			return "Error";
		}
	}
	
	private boolean insertPatient (){
		dba.connect(); // connect to the database
		try {
							
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			
			statement.executeUpdate("INSERT INTO cmsc345.patients" +
					" VALUES ( 0 ,"+userId+",'"+firstName+"','"+lastName+"','"
					+middleName+"','"+this.patientDOB+"',"+weight+","+height+","+this.patientSex+",'"
					+emergencyContactName+"','"	+emergencyContactPhone+"','"+insurance+"','"
					+insuranceID+"','"+SSN+"');");
			
			statement.close();
			dba.disconnect();
			//return true if user was found
			
			return true;
		} catch (SQLException e) {
            System.err.println ("Error in inserting patient");
            System.err.println (e.toString());
			dba.disconnect();
			return false;
		}
	}
	
	private boolean updatePatient(){
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			System.out.println("\n*****************\nupdating .....\n");
			
			
			System.out.println("UPDATE cmsc345.patients" +
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
			
			statement.executeUpdate("UPDATE cmsc345.patients" +
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
            System.err.println ("Error in Updating patient where id = " + this.patientId);
            System.err.println (e.toString());
			dba.disconnect();
			return false;
		}		
	}
	
	private boolean deletePatient(){
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
						
			statement.executeUpdate("DELETE FROM cmsc345.patients" +
					" WHERE patientid = '" + this.patientId +"';");
			
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