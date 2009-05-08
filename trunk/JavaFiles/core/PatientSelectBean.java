/**********************************************************
* File: core.PatientSelectBean.java
* Author: Alex Bassett
* Date Created: 4/28/2009
*
* Description: This ActionBean controls loading the
* 				patient information after a user has
* 				selected one from the patient list
* 
* Edited: 4/30/2009 by Alex Bassett
* Changes: Added functionality to load information off of
* 			patient's subtables (ie appointments, tests, etc)
*
**********************************************************/
package core;

import java.util.ArrayList;
import java.util.Date;

import net.sourceforge.stripes.action.*;

public class PatientSelectBean implements ActionBean {

	// Declare class variables
	private HPActionBeanContext context;
	
	// fields from patients
	private int patientId;
	private String patientFirstName;
	private String patientMiddleName;
	private String patientLastName;
	private java.util.Date patientDOB;
	private int patientWeight;
	private int patientHeight;
	private String patientSex;
	private String patientEmergencyContactName;
	private String patientEmergencyContactNumber;
	private String patientInsuranceProvider;
	private String patientInsuranceID;
	private String patientSSN;
	
	// doctor fields
	private String doctorNote;
	private String doctorName;
	private String doctorPhone;
	private String doctorEmail;
	private String doctorDescription;
	
	// subtables
	private ArrayList<Boolean> appointmentReminders;
	
	private ArrayList<Integer> appointmentIDs;
	private ArrayList<String> appointmentDescriptions;
	private ArrayList<Date> appointmentDates;
	
	private ArrayList<Integer> allergyIDs;
	private ArrayList<String> allergyNames;
	private ArrayList<String> allergyDescriptions;
	
	private ArrayList<Integer> medicationIDs;
	private ArrayList<String> medicationNames;
	private ArrayList<String> medicationDescriptions;
	private ArrayList<Date> medicationRefillDates;
	private ArrayList<Date> medicationExpirationDates;
	
	private ArrayList<Integer> testIDs;
	private ArrayList<String> testNames;
	private ArrayList<String> testDescriptions;
	private ArrayList<String> testResults;
	private ArrayList<Date> testDates;
	
	/* Getters and Setters*/
	// overridden from ActionBean
    public HPActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = (HPActionBeanContext)context; }

	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientFirstName() {
		return patientFirstName;
	}
	public String getPatientMiddleName() {
		return patientMiddleName;
	}
	public String getPatientLastName() {
		return patientLastName;
	}
	public java.util.Date getPatientDOB() {
		return patientDOB;
	}
	public int getPatientWeight() {
		return patientWeight;
	}
	public int getPatientHeight() {
		return patientHeight;
	}
	public String getPatientSex() {
		return patientSex;
	}
	public String getPatientEmergencyContactName() {
		return patientEmergencyContactName;
	}
	public String getPatientEmergencyContactNumber() {
		return patientEmergencyContactNumber;
	}
	public String getPatientInsuranceProvider() {
		return patientInsuranceProvider;
	}
	public String getPatientInsuranceID() {
		return patientInsuranceID;
	}
	public String getPatientSSN() {
		return patientSSN;
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
	public ArrayList<Date> getAppointmentDates() {
		return appointmentDates;
	}
	public ArrayList<String> getAppointmentDescriptions() {
		return appointmentDescriptions;
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
	public ArrayList<Date> getTestDates() {
		return testDates;
	}
	
/* Validation (Stripes) Methods and Handlers */
	
	
	/**
	 * Saves the patientId in the session and loads relevant
	 * patient data and patient subtables before forwarding to
	 * patientHome for a more detailed look at the patient
	 *
	 * @return Resolution forwarded to patient home
	 * @author Alex Bassett
	 */
	@DefaultHandler
	public Resolution edit() {
		context.setPatientId(patientId);
		
		// load the patient data
		PatientSQL p = new PatientSQL();
		 if (p.lookupPatient(patientId)) {
			 this.patientFirstName = p.getFirstName();
			 this.patientMiddleName = p.getMiddleName();
			 this.patientLastName = p.getLastName();
			 this.patientDOB = p.getPatientDOB();
			 this.patientWeight = p.getWeight();
			 this.patientHeight = p.getHeight();
			 this.patientEmergencyContactName = p.getEmergencyContactName();
			 this.patientEmergencyContactNumber = p.getEmergencyContactPhone();
			 this.patientInsuranceProvider = p.getInsurance();
			 this.patientInsuranceID = p.getInsuranceID();
			 this.patientSSN = p.getSSN();
			 if (p.getPatientSex() == 1) this.patientSex = "Female";
			 else this.patientSex = "Male";
			
			/* 
			 this.checkNullStrings();*/
			 //System.out.println(this.patientMiddleName.equals("null"));
			 //this.patientMiddleName = "FAIL";
			 
		 }
		 // patient can't be loaded
		 else {
			 System.err.println("PatientSelectBean.java: Patient selected from patientList.jsp could not be loaded");
			 return new ForwardResolution("patientList.jsp");
		 }
		 
		 //replaces null strings with empty strings
		 checkNullStrings();
		 
		 // load patient's subtable data
		 PatientTablesSQL pt = new PatientTablesSQL();
		 pt.getAllSubtables(patientId);

		 this.doctorName = pt.getDoctorName();
		 this.doctorPhone = pt.getDoctorPhone();
		 if(this.doctorPhone.equals("null")) { this.doctorPhone = ""; }
		 this.doctorEmail = pt.getDoctorEmail();
		 this.doctorDescription = pt.getDoctorDescription();
		 if(this.doctorDescription.equals("null")) { this.doctorDescription = ""; }
		 this.doctorNote = pt.getDoctorNote();

		 this.appointmentReminders = pt.getAppointmentReminders();

		 this.appointmentIDs = pt.getAppointmentIDs();
		 this.appointmentDescriptions = pt.getAppointmentDescriptions();
		 this.appointmentDates = pt.getAppointmentDates();

		 this.allergyIDs = pt.getAllergyIDs();
		 this.allergyNames = pt.getAllergyNames();
		 this.allergyDescriptions = pt.getAllergyDescriptions();

		 this.medicationIDs = pt.getMedicationIDs();
		 this.medicationNames = pt.getMedicationNames();
		 this.medicationDescriptions = pt.getMedicationDescriptions();
		 this.medicationRefillDates = pt.getMedicationRefillDates();
		 this.medicationExpirationDates = pt.getMedicationExpirationDates();

		 this.testIDs = pt.getTestIDs();
		 this.testNames = pt.getTestNames();
		 this.testDescriptions = pt.getTestDescriptions();
		 this.testResults = pt.getTestResults();
		 this.testDates = pt.getTestDates();

		 // forward to patientHome
		 return new ForwardResolution("patientHome.jsp");
	}

	/**
	 * Handles deleting a patient from the database
	 *
	 * @return Resolution forwarded back to patientList
	 * after the patient has been deleted. When patientList
	 * updates the user will no longer see the deleted patient
	 * @author Alex Bassett
	 */
	public Resolution delete() {
		PatientSQL.deletePatient(patientId);
		return new ForwardResolution("userHomepage.jsp");
	}
	
	/**
	 * replaces all null strings with empty strings
	 * 
	 * @author Han Dong
	 */
	private void checkNullStrings()
	{	 
		if(this.patientMiddleName.equals("null")) { this.patientMiddleName = ""; }
		if(this.patientEmergencyContactName.equals("null")) { this.patientEmergencyContactName = ""; }
		if(this.patientEmergencyContactNumber.equals("null")) { this.patientEmergencyContactNumber = ""; }
		if(this.patientInsuranceProvider.equals("null")) { this.patientInsuranceProvider = ""; }
		if(this.patientInsuranceID.equals("null")) { this.patientInsuranceID = ""; }
		if(this.patientSSN.equals("null")) { this.patientSSN = ""; }

	}
}