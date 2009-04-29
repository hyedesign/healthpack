/**********************************************************
* File: core.PatientSelectBean.java
* Author: Alex Bassett
* Date Created: 4/28/2009
*
* Description: This ActionBean controls loading the
* 				patient information after a user has
* 				selected one from the patient list
*
**********************************************************/
package core;

import net.sourceforge.stripes.action.*;

public class PatientSelectBean implements ActionBean {

	// Declare class variables
	private HPActionBeanContext context;
	
	// bean fields
	private int patientId;
	private String patientNote;
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
	public String getPatientNote() {
		return patientNote;
	}
	public void setPatientNote(String patientNote) {
		this.patientNote = patientNote;
	}
	public String getPatientFirstName() {
		return patientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	public String getPatientMiddleName() {
		return patientMiddleName;
	}
	public void setPatientMiddleName(String patientMiddleName) {
		this.patientMiddleName = patientMiddleName;
	}
	public String getPatientLastName() {
		return patientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	public java.util.Date getPatientDOB() {
		return patientDOB;
	}
	public void setPatientDOB(java.util.Date patientDOB) {
		this.patientDOB = patientDOB;
	}
	public int getPatientWeight() {
		return patientWeight;
	}
	public void setPatientWeight(int patientWeight) {
		this.patientWeight = patientWeight;
	}
	public int getPatientHeight() {
		return patientHeight;
	}
	public void setPatientHeight(int patientHeight) {
		this.patientHeight = patientHeight;
	}
	public String getPatientSex() {
		return patientSex;
	}
	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	public String getPatientEmergencyContactName() {
		return patientEmergencyContactName;
	}
	public void setPatientEmergencyContactName(String patientEmergencyContactName) {
		this.patientEmergencyContactName = patientEmergencyContactName;
	}
	public String getPatientEmergencyContactNumber() {
		return patientEmergencyContactNumber;
	}
	public void setPatientEmergencyContactNumber(
			String patientEmergencyContactNumber) {
		this.patientEmergencyContactNumber = patientEmergencyContactNumber;
	}
	public String getPatientInsuranceProvider() {
		return patientInsuranceProvider;
	}
	public void setPatientInsuranceProvider(String patientInsuranceProvider) {
		this.patientInsuranceProvider = patientInsuranceProvider;
	}
	public String getPatientInsuranceID() {
		return patientInsuranceID;
	}
	public void setPatientInsuranceID(String patientInsuranceNumber) {
		this.patientInsuranceID = patientInsuranceNumber;
	}
	public String getPatientSSN() {
		return patientSSN;
	}
	public void setPatientSSN(String patientSSN) {
		this.patientSSN = patientSSN;
	}
	
	
/* Validation (Stripes) Methods and Handlers */
	

	/**
	 * Saves the patientId in the session and forwards to
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
			 this.patientSSN = p.getSSN();
			 this.patientNote = p.getNote();
			 if (p.getPatientSex() == 1) this.patientSex = "Female";
			 else this.patientSex = "Male";
			 
			 return new ForwardResolution("patientHome.jsp");
		 }
		 // patient can't be loaded
		 else {
			 System.err.println("PatientSelectBean.java: Patient selected from patientList.jsp could not be loaded");
			 return new ForwardResolution("patientList.jsp");
		 }
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
		PatientSQL.DeletePatient(patientId);
		return new ForwardResolution("patientList.jsp");
	}
}