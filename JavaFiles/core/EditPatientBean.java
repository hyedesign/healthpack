/**********************************************************
* File: core.EditPatientBean.java
* Author: Alex Bassett
* Date Created: 4/28/2009
*
* Description: Bean handles form validation and adding
* 				and updating patients to the patient
* 				table of the database
*
**********************************************************/

package core;

import java.sql.Date;
import java.util.Calendar;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.*;

public class EditPatientBean implements ActionBean {
   
	/*
	 * PRIVATE VARIABLES
	 */
	private HPActionBeanContext context;
	
    private int patientId = -1;
	private int userId;
	@Validate(required=true, maxlength=30) private String firstName = "";
	@Validate(required=true, maxlength=30) private String lastName = "";
	@Validate(required=false, maxlength=30) private String middleName = "";
	private java.sql.Date patientDOB;
	@Validate(required=true, maxlength=4)private int height;
	@Validate(required=true, maxlength=4)private int weight;
	@Validate(required=true, maxlength=1) private char patientSex;
	@Validate(required=false, maxlength=30) private String emergencyContactName = "";
	@Validate(required=false, maxlength=15) private String emergencyContactPhone = "";
	@Validate(required=false, maxlength=30) private String insurance= "" ;
	@Validate(required=false, maxlength=30) private String insuranceID = "";
	@Validate(required=false, maxlength=11) private String SSN= "" ;
	@Validate(required=true, maxlength=2, minlength=1) private int birthMonth;
	@Validate(required=true, maxlength=2, minlength=1)  private int birthDay;
	@Validate(required=true, maxlength=4, minlength=4) private int birthYear;
		
	//getters and setters
    public HPActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = (HPActionBeanContext)context; }
    
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		if (this.middleName.equals(null)) this.middleName = "";
		this.middleName = middleName;
	}
	public Date getPatientDOB() {
		return patientDOB;
	}
	public void setPatientDOB(Date patientDOB) {
		this.patientDOB = patientDOB;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public char getPatientSex() {
		return patientSex;
	}
	public void setPatientSex(char patientSex) {
		this.patientSex = patientSex;
	}
	public String getEmergencyContactName() {
		return emergencyContactName;
	}
	public void setEmergencyContactName(String emergencyContactName) {
		if (this.emergencyContactName.equals(null)) this.emergencyContactName = "";
		this.emergencyContactName = emergencyContactName;
	}
	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}
	public void setEmergencyContactPhone(String emergencyContactPhone) {
		if (this.emergencyContactPhone.equals(null)) this.emergencyContactPhone = "";
		this.emergencyContactPhone = emergencyContactPhone;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		if (this.insurance.equals(null)) this.insurance = "";
		this.insurance = insurance;
	}
	public String getInsuranceID() {
		return insuranceID;
	}
	public void setInsuranceID(String insuranceID) {
		if (this.insuranceID.equals(null)) this.insuranceID = "";
		this.insuranceID = insuranceID;
	}
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String ssn) {
		if (this.SSN.equals(null)) this.SSN = "";
		SSN = ssn;
	}
	public int getBirthMonth() {
		return birthMonth;
	}
	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}
	public int getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
   
	/**
     * deletes the patient specified by the patientID
     * from the mySQL database
     * 
     * @author Han Dong
     */
    public Resolution delete()
    {
    	PatientSQL.deletePatient(this.patientId);
    	return new ForwardResolution("patientList.jsp");
    }
    
    @DefaultHandler
    public Resolution submit() {
    	
    	int sex = 0; if (this.patientSex == 'f')sex = 1; 
    	
    	Calendar cal = Calendar.getInstance();
		cal.set(this.birthYear, this.birthMonth-1, this.birthDay);
		Date date = new Date(cal.getTime().getTime());
    	
    	//this is a new patient
    	if (this.patientId == -1) {

    		// add patient information to the database
    		new PatientSQL(this.patientId, this.userId, this.firstName,
    				this.lastName, this.middleName, date, this.height,
    				this.weight, sex, this.emergencyContactName,
    				this.emergencyContactPhone, this.insurance, this.insuranceID,
    				this.SSN).CreateNewPatient();
    	}
    	//this is an old patient
    	else {
        	
    		// edit existing patient
    		new PatientSQL(this.patientId).SetAndUpdateCurrentPatient(this.firstName,
    				this.lastName, this.middleName, date, this.height,
    				this.weight, sex, this.emergencyContactName,
    				this.emergencyContactPhone, this.insurance, this.insuranceID,
    				this.SSN);
    	}
        return new ForwardResolution("patientList.jsp");
    }
    
    //VALIDATION 
    @ValidationMethod(on="submit")
    public void dateFormat(ValidationErrors errors) 
    {	
    	//sex
    	if (this.patientSex != 'm' && this.patientSex != 'f')
    		errors.add("patientSex", new SimpleError("Patient sex must be either m for male or f for female"));
    	    	
    	//birthday
    	if (!(birthMonth == 0 && birthDay == 0 && birthYear == 0)){
	    	if(birthMonth < 1 || birthMonth > 12)
	    		errors.add("birthMonth", new SimpleError("Invalid Birth Month"));
	    	if(birthDay < 1 || birthDay > 31)
	    		errors.add("birthDay", new SimpleError("Invalid Birth Day"));
	    	if(birthYear > 2009 || birthYear == 0)
	    		errors.add("birthYear", new SimpleError("Invalid Birth Year"));
    	}
    	
		// Check for flagged characters
	    if (hasSpecialCharacters(this.firstName) || hasSpecialCharacters(this.lastName) ||
	    		hasSpecialCharacters(this.middleName) || hasSpecialCharacters(this.emergencyContactName) ||
	    		hasSpecialCharacters(this.emergencyContactPhone) || hasSpecialCharacters(this.insurance) ||
	    		hasSpecialCharacters(this.insuranceID) || hasSpecialCharacters(this.SSN))
	        errors.addGlobalError(new SimpleError("These characters are not allowed: <> () [] \\ / | = + * $ # ^ : ; "));
    }
    
	/**
	 * Base level function that returns true when the given
	 * input string contains a character that could be used for
	 * a SQL injection attack
	 *
	 * @param s the user created string to be checked
	 * @return true when the string contains special
	 * characters and false if it does not
	 * @author Alex Bassett
	 */
	private boolean hasSpecialCharacters(String s) {
		if (s != null)
			if (s != s.replaceAll("([^A-Za-z0-9.,@!?~`'\"% _-]+)", ""))
				return true;
		return false;
	}
}