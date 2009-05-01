/**********************************************************
* File: core.PatientTablesSQL.java
* Author: Alex Bassett
* Date Created: 4/30/2009
*
* Description: Handles getting a patient's subtables from the
* 				database, ie Appointments and Medications. All
* 				methods are static and return arrays of names
* 				or dates
*              
**********************************************************/

package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import core.DBAccess;

public class PatientTablesSQL {
		
	//declare local variables
	DBAccess dba_s;
	
	String doctorNote;
	String doctorName;
	String doctorPhone;
	String doctorEmail;
	String doctorDescription;
	
	ArrayList<Boolean> appointmentReminders;
	
	ArrayList<Integer> appointmentIDs;
	ArrayList<String> appointmentDescriptions;
	ArrayList<Date> appointmentDates;
	
	ArrayList<Integer> allergyIDs;
	ArrayList<String> allergyNames;
	ArrayList<String> allergyDescriptions;
	
	ArrayList<Integer> medicationIDs;
	ArrayList<String> medicationNames;
	ArrayList<String> medicationDescriptions;
	ArrayList<Date> medicationRefillDates;
	ArrayList<Date> medicationExpirationDates;
	
	ArrayList<Integer> testIDs;
	ArrayList<String> testNames;
	ArrayList<String> testDescriptions;
	ArrayList<String> testResults;
	ArrayList<Date> testDate;
	
	//constructor
	public PatientTablesSQL() {
		dba_s = new DBAccess();
		
		doctorNote = "";
		doctorName = "";
		doctorPhone = "";
		doctorEmail = "";
		doctorDescription = "";
		
		appointmentReminders = new ArrayList<Boolean>();
		
		appointmentIDs = new ArrayList<Integer>();
		appointmentDescriptions = new ArrayList<String>();
		appointmentDates = new ArrayList<Date>();
		
		allergyIDs = new ArrayList<Integer>();
		allergyNames = new ArrayList<String>();
		allergyDescriptions = new ArrayList<String>();
		
		medicationIDs = new ArrayList<Integer>();
		medicationNames = new ArrayList<String>();
		medicationDescriptions = new ArrayList<String>();
		medicationRefillDates = new ArrayList<Date>();
		medicationExpirationDates = new ArrayList<Date>();
		
		testIDs = new ArrayList<Integer>();
		testNames = new ArrayList<String>();
		testDescriptions = new ArrayList<String>();
		testResults = new ArrayList<String>();
		testDate = new ArrayList<Date>();
	}
	
	/**
	 * Gets the patient's subtables and populates the various array
	 * lists with the data
	 * 
	 * @param patientId the ID of the patient whose sbtables you want
	 */
	public void getAllSubtables(int patientId) {
		dba_s .connect(); // connect to the database
		try {
			// Load Doctor's information
			int doctorId = -1;
			Statement st_doc1 = dba_s.connection.createStatement ();
			ResultSet rs_doc1 = st_doc1.executeQuery ("SELECT * FROM doctorpatient WHERE patientid='"+patientId+"'");
			
			if (rs_doc1.first()) {
				doctorId = rs_doc1.getInt("userId");
				doctorNote = rs_doc1.getString("notedescription");
			}
			
			// close connections
			rs_doc1.close();
			st_doc1.close();
			
			// if a doctor was loaded for this patient
			if (doctorId != -1) {
				Statement st_doc2 = dba_s.connection.createStatement ();
				ResultSet rs_doc2 = st_doc2.executeQuery ("SELECT * FROM users WHERE userid='"+doctorId+"'");
			
				// get the doctor's information
				if (rs_doc2.first()) {
					doctorName = "Dr. " + rs_doc2.getString("userfirstname") 
						+ " " + rs_doc2.getString("userlastname");
					doctorPhone = rs_doc2.getString("userphone");
					doctorEmail = rs_doc2.getString("useremail");
					doctorDescription = rs_doc2.getString("userdescription");
				}
				
				rs_doc2.close();
				st_doc2.close();
			}
			
			// Load appointments data
			Statement st_ap = dba_s.connection.createStatement ();
			ResultSet rs_ap = st_ap.executeQuery ("SELECT * FROM appointments WHERE patientid='"+patientId+"'");			
			while (rs_ap.next()) {
				appointmentIDs.add(rs_ap.getInt("appointmentid"));
				appointmentDescriptions.add(rs_ap.getString("appointmentdescription"));
				appointmentDates.add(rs_ap.getDate("appointmentdate"));
				if (rs_ap.getInt("appointmentreminder") == 1)
					appointmentReminders.add(true);
				else appointmentReminders.add(false);
			}	
			rs_ap.close();
			st_ap.close();
			
			// Load allergies data
			Statement st_al = dba_s.connection.createStatement ();
			ResultSet rs_al = st_al.executeQuery ("SELECT * FROM allergies WHERE patientid='"+patientId+"'");			
			while (rs_al.next()) {
				allergyIDs.add(rs_al.getInt("allergyid"));
				allergyNames.add(rs_al.getString("allergyname"));
				allergyDescriptions.add(rs_al.getString("allergydescription"));
			}
			rs_al.close();
			st_al.close();
			
			// Load medications data
			Statement st_me = dba_s.connection.createStatement ();
			ResultSet rs_me = st_me.executeQuery ("SELECT * FROM medications WHERE patientid='"+patientId+"'");			
			while (rs_me.next()) {
				medicationIDs.add(rs_me.getInt("medicationid"));
				medicationNames.add(rs_me.getString("medicationname"));
				medicationDescriptions.add(rs_me.getString("medicationdescription"));
				medicationRefillDates.add(rs_me.getDate("medicationexpirationsdate"));
				medicationExpirationDates.add(rs_me.getDate("medicationrefilldate"));
			}
			rs_me.close();
			st_me.close();
			
			// Load tests data
			Statement st_te = dba_s.connection.createStatement ();
			ResultSet rs_te = st_te.executeQuery ("SELECT * FROM tests WHERE patientid='"+patientId+"'");			
			while (rs_te.next()) {
				testIDs.add(rs_te.getInt("testid"));
				testNames.add(rs_te.getString("testname"));
				testDescriptions.add(rs_te.getString("testresult"));
				testResults.add(rs_te.getString("testdescription"));
				testDate.add(rs_te.getDate("testdate"));
			}
			rs_te.close();
			st_te.close();
			
			// disconnect
			dba_s.disconnect();
			
		} catch (SQLException e) {
            System.err.println ("PatientTableSQL.getAllSubtables: Malformed SQL statement");
            System.err.println (e.toString());
			dba_s.disconnect();
		}
	}

	public String getDoctorNote() {
		return doctorNote;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public String getDoctorPhone() {
		return doctorPhone;
	}

	public String getDoctorEmail() {
		return doctorEmail;
	}

	public String getDoctorDescription() {
		return doctorDescription;
	}

	public ArrayList<Boolean> getAppointmentReminders() {
		return appointmentReminders;
	}

	public ArrayList<Integer> getAppointmentIDs() {
		return appointmentIDs;
	}

	public ArrayList<String> getAppointmentDescriptions() {
		return appointmentDescriptions;
	}

	public ArrayList<Date> getAppointmentDates() {
		return appointmentDates;
	}

	public ArrayList<Integer> getAllergyIDs() {
		return allergyIDs;
	}

	public ArrayList<String> getAllergyNames() {
		return allergyNames;
	}

	public ArrayList<String> getAllergyDescriptions() {
		return allergyDescriptions;
	}

	public ArrayList<Integer> getMedicationIDs() {
		return medicationIDs;
	}

	public ArrayList<String> getMedicationNames() {
		return medicationNames;
	}

	public ArrayList<String> getMedicationDescriptions() {
		return medicationDescriptions;
	}

	public ArrayList<Date> getMedicationRefillDates() {
		return medicationRefillDates;
	}

	public ArrayList<Date> getMedicationExpirationDates() {
		return medicationExpirationDates;
	}

	public ArrayList<Integer> getTestIDs() {
		return testIDs;
	}

	public ArrayList<String> getTestNames() {
		return testNames;
	}

	public ArrayList<String> getTestDescriptions() {
		return testDescriptions;
	}

	public ArrayList<String> getTestResults() {
		return testResults;
	}

	public ArrayList<Date> getTestDate() {
		return testDate;
	}
}