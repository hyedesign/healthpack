/**********************************************************
* File: core.PatientInfoBean.java
* Author: Alex Bassett
* Date Created: 4/30/2009
*
* Description: This Bean looks up a patient's information for
* 				the edit patient form
*
**********************************************************/
package core;

import java.util.Date;

public class PatientInfoBean {
	
	private boolean loaded;
	private int patientId;
	private String firstName;
	private String lastName;
	private String middleName;
	private int weight;
	private int height;
	private int month;
	private int day;
	private int year;
	private char sex;
	private String ECName;
	private String ECPhone;
	private String insurance;
	private String insuranceID;
	private String SSN;
	
	// Load user data from the database
	public boolean getLoaded() {
		
		try {
			// load the data
			PatientSQL p = new PatientSQL();
			p.lookupPatient(patientId);
			
			// copy it over
			this.firstName = p.getFirstName();
			this.middleName = p.getMiddleName();
			this.lastName = p.getLastName();
			this.weight = p.getWeight();
			this.height = p.getHeight();
			this.ECName = p.getEmergencyContactName();
			this.ECPhone = p.getEmergencyContactPhone();
			this.insurance = p.getInsurance();
			this.insuranceID = p.getInsuranceID();
			this.SSN = p.getSSN();
			
			// convert the date
			Date date = p.getPatientDOB();
			String s = date.toString();
    		int index = s.indexOf('-');
    		this.year = Integer.parseInt(s.substring(0, index));
    		s = s.substring(index+1, s.length());
    		index = s.indexOf('-');
    		this.month = Integer.parseInt(s.substring(0,index));
    		this.day = Integer.parseInt(s.substring(index + 1, s.length()));
			
			// get the sex
			if(p.getPatientSex() == 0)
				this.sex = 'm';
			else this.sex = 'f';
			
			loaded = true;
			return loaded;
		} 
		catch (Exception e){
			System.err.println("PatientInfoBean.getLoaded(): Couldn't load patient from ID " + patientId);
			loaded = false;
			return loaded;
		}
	}
	
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getPatientId() {
		return patientId;
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
	public int getWeight() {
		return weight;
	}
	public int getHeight() {
		return height;
	}
	public int getMonth() {
		return month;
	}
	public int getDay() {
		return day;
	}
	public int getYear() {
		return year;
	}
	public char getSex() {
		return sex;
	}
	public String getECName() {
		return ECName;
	}
	public String getECPhone() {
		return ECPhone;
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
}
