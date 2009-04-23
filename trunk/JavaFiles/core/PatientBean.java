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
import java.util.Calendar;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

public class PatientBean implements ActionBean {
   
	/*
	 * PRIVATE VARIABLES
	 */
	private ActionBeanContext context;
	
	
    private int patientId;
	private int userId;
	@Validate(required=true, maxlength=30) private String firstName ="";
	@Validate(required=true, maxlength=30) private String lastName="";
	@Validate(required=false, maxlength=30) private String middleName="";
	private java.sql.Date patientDOB;
	private int height;
	private int weight;
	@Validate(required=true, maxlength=1) private char patientSex;
	@Validate(required=false, maxlength=30) private String emergencyContactName ="";
	@Validate(required=false, maxlength=10) private String emergencyContactPhone="";
	@Validate(required=false, maxlength=30) private String insurance="";
	@Validate(required=false, maxlength=30) private String insuranceID="";
	@Validate(required=false, maxlength=9) private String SSN="";
	@Validate(required=true, maxlength=2, minlength=2) private int birthMonth;
	@Validate(required=true, maxlength=2, minlength=2)  private int birthDay;
	@Validate(required=true, maxlength=4, minlength=4) private int birthYear;

   
    /*
	 * CONSTRUCTORS
	 */
    public PatientBean() {	
    }	
		
	/*
	 * GETTERS AND SETTERS
	 */
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
		this.emergencyContactName = emergencyContactName;
	}
	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}
	public void setEmergencyContactPhone(String emergencyContactPhone) {
		this.emergencyContactPhone = emergencyContactPhone;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public String getInsuranceID() {
		return insuranceID;
	}
	public void setInsuranceID(String insuranceID) {
		this.insuranceID = insuranceID;
	}
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String ssn) {
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
		
	 
/********************************************************************************************************/
   
	public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
   
    @DefaultHandler
    public Resolution submit() {
    	if (this.patientId == 0) {
    		
    		Calendar cal = Calendar.getInstance();
    		cal.set (birthYear, birthMonth-1, birthDay);
    		this.patientDOB = new Date(cal.getTime().getTime());
    		
    		// Making evything blank before sending it to the SQL. Otherwise the string "NULL" is sent
        	if (this.middleName == null) this.middleName = "";
    		if (this.emergencyContactName == null) this.emergencyContactName = "";
    		if (this.emergencyContactPhone == null) this.emergencyContactPhone = "";
    		if (this.insurance == null) this.insurance = "";
    		if (this.insuranceID == null) this.insuranceID = "";
    		if (this.SSN == null) this.SSN = "";
    		
    		new PatientSQL(this.patientId, this.userId, this.firstName,
    				this.lastName, this.middleName, this.patientDOB, this.height,
    				this.weight, this.patientSex, this.emergencyContactName,
    				this.emergencyContactPhone, this.insurance, this.insuranceID,
    				this.SSN).CreateNewPatient();
    	}else{ 
    		int sex = 0; if (this.patientSex == 'f')sex = 1; 
    		
    		//SETTING THE DATE
    		Calendar cal = Calendar.getInstance();
    		cal.set (birthYear, birthMonth-1, birthDay);
    		this.patientDOB = new Date(cal.getTime().getTime());
    		
    		System.out.println("firstName: " + this.firstName);
    		System.out.println("lastName: " + this.lastName);
    		System.out.println("middleName: " + this.middleName);
    		System.out.println("patientDOB: " + this.patientDOB.toString());
    		System.out.println("height: " + this.height);
    		System.out.println("weight: " + this.weight);
    		System.out.println("sex: " + sex);
    		System.out.println("emergencyContactName: " + this.emergencyContactName);
    		System.out.println("emergencyContactPhone: " + this.emergencyContactPhone);
    		System.out.println("finsuranceName: " + this.insurance);
    		System.out.println("fNinsuranceIDame: " + this.insuranceID);
    		System.out.println("SSN: " + this.SSN);

    		// Making evything blank before sending it to the SQL. Otherwise the string "NULL" is sent
        	if (this.middleName == null) this.middleName = "";
    		if (this.emergencyContactName == null) this.emergencyContactName = "";
    		if (this.emergencyContactPhone == null) this.emergencyContactPhone = "";
    		if (this.insurance == null) this.insurance = "";
    		if (this.insuranceID == null) this.insuranceID = "";
    		if (this.SSN == null) this.SSN = "";
    		
    		new PatientSQL(this.patientId).SetAndUpdateCurrentPatient(this.firstName,
    				this.lastName, this.middleName, this.patientDOB, this.height,
    				this.weight, sex, this.emergencyContactName,
    				this.emergencyContactPhone, this.insurance, this.insuranceID,
    				this.SSN);
    	}
        return new ForwardResolution("patientList.jsp");
    }
    
    public Resolution deletePatient(){
    	PatientSQL.DeletePatient(this.patientId);    	
    	return new ForwardResolution("patientList.jsp");
    }
    
    //VALIDATION 
    @ValidationMethod(on="submit")
    public void dateFormat(ValidationErrors errors) 
    {
    	//FIRST and LAST name
    	if (this.firstName == null)errors.add("firstName", new SimpleError("Please enter a first name"));
    	if (this.lastName == null)errors.add("lastName", new SimpleError("Please enter a last name"));
    	
    	//SEX
    	if (this.patientSex != 'm' && this.patientSex != 'f')
    		errors.add("patientSex", new SimpleError("Patient sex is m for male, f for female"));
    	    	
    	//BIRTHDAY
    	if (!(birthMonth == 0 && birthDay == 0 && birthYear == 0)){
	    	if(birthMonth < 1 || birthMonth > 12)
	    		errors.add("birthMonth", new SimpleError("Invalid Birth Month"));
	    	if(birthDay < 1 || birthDay > 31)
	    		errors.add("birthDay", new SimpleError("Invalid Birth Day"));
	    	if(birthYear > 2009 || birthYear == 0)
	    		errors.add("birthYear", new SimpleError("Invalid Birth Year"));
    	}
    }
  
    /********************************************************************************************************/
  
}