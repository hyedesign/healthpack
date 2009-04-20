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

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;


public class PatientBean implements ActionBean  {
	//BEANS !!!
	private ActionBeanContext context;
    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
	
	
	//PRIVATE VARIABLES
	// variable connects to the database
	private DBAccess dba = new DBAccess();;
	//patient data
	 @Validate(required=false)private int patientId;
	 @Validate(required=false)private int userId;
	 @Validate(required=false)private int doctorId;
	 @Validate(required=false)private String patientFirstName;
	 @Validate(required=false)private String patientLastName;
	 @Validate(required=false)private String patientMiddleName;
	 @Validate(required=false)private int day;
	 @Validate(required=false)private int month;
	 @Validate(required=false)private int year;
	 @Validate(required=false)private Date patientDOB;
	 @Validate(required=false)private int patientHeight;
	 @Validate(required=false)private int patientWeight;
	 @Validate(required=false)private int patientSex;
	 @Validate(required=false)private String patientEmergencyConatactName;
	 @Validate(required=false)private String patientEmergencyConatactNumber;
	 @Validate(required=false)private String patientInsuranceProvider;
	 @Validate(required=false)private String patientInsuranceId;
	 @Validate(required=false)private String patientSSN;
	
	//CONSTRUCTORS 
	public PatientBean() {
		patientId = 0;
		userId = 0;
		doctorId = 0;
		patientFirstName = null;
		patientLastName = null;
		patientMiddleName = null;
		day = 0;
		month = 0;
		year = 0;
		patientHeight = 0;
		patientWeight = 0;
		patientSex = 0;
		patientEmergencyConatactName = null;
		patientEmergencyConatactNumber = null;
		patientInsuranceProvider = null;
		patientInsuranceId = null;
		patientSSN = null;
	}	
	
	public PatientBean(int ID) {
		if (ID != 0){
			lookupPatient(ID);
		}
	}
	
	//GETTERS AND SETTERS	
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
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getPatientFirstName() {
		return patientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	public String getPatientLastName() {
		return patientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	public String getPatientMiddleName() {
		return patientMiddleName;
	}
	public void setPatientMiddleName(String patientMiddleName) {
		this.patientMiddleName = patientMiddleName;
	}
	public Date getPatientDOB() {
		return patientDOB;
	}
	public void setPatientDOB(Date patientDOB) {
		this.patientDOB = patientDOB;
	}
	public int getPatientHeight() {
		return patientHeight;
	}
	public void setPatientHeight(int patientHeight) {
		this.patientHeight = patientHeight;
	}
	public int getPatientWeight() {
		return patientWeight;
	}
	public void setPatientWeight(int patientWeight) {
		this.patientWeight = patientWeight;
	}
	public int getPatientSex() {
		return patientSex;
	}
	public void setPatientSex(int patientSex) {
		this.patientSex = patientSex;
	}
	public String getPatientEmergencyConatactName() {
		return patientEmergencyConatactName;
	}
	public void setPatientEmergencyConatactName(String patientEmergencyConatactName) {
		this.patientEmergencyConatactName = patientEmergencyConatactName;
	}
	public String getPatientEmergencyConatactNumber() {
		return patientEmergencyConatactNumber;
	}
	public void setPatientEmergencyConatactNumber(
			String patientEmergencyConatactNumber) {
		this.patientEmergencyConatactNumber = patientEmergencyConatactNumber;
	}
	public String getPatientInsuranceProvider() {
		return patientInsuranceProvider;
	}
	public void setPatientInsuranceProvider(String patientInsuranceProvider) {
		this.patientInsuranceProvider = patientInsuranceProvider;
	}
	public String getPatientInsuranceId() {
		return patientInsuranceId;
	}
	public void setPatientInsuranceId(String patientInsuranceId) {
		this.patientInsuranceId = patientInsuranceId;
	}
	public String getPatientSSN() {
		return patientSSN;
	}
	public void setPatientSSN(String patientSSN) {
		this.patientSSN = patientSSN;
	}

	//STRIPES VALIDATION
	@HandlesEvent("submit") 
	@DefaultHandler
	    public Resolution submit() {
	        return new ForwardResolution("verifyPatientInfo.jsp");
	    }
	
	@ValidationMethod(on="submit")
	public void ValidationChecks(ValidationErrors errors){
		//First Name
		if (this.patientFirstName == null) errors.add("firstName", new SimpleError("Please enter a first name."));
	    else if (this.patientFirstName.length() > 30) errors.add("firstName", new SimpleError("Please make sure first name is less than 30 characters."));
	    
		//Middle Name
		if (this.patientMiddleName != null && this.patientFirstName.length() > 30) 
			errors.add("middleName", new SimpleError("Please make sure middle name is less than 30 characters."));
	    
		//Last Name
		if (this.patientLastName == null) errors.add("lastName", new SimpleError("Please enter a last name."));
	    else if (this.patientLastName.length() > 30) errors.add("lastName", new SimpleError("Please make sure last name is less than 30 characters."));
	    
		//DOB
		if(this.month < 1 || this.month > 12)
    		errors.add("month", new SimpleError("Please enter a valid month of birth"));
    	if(this.day < 1 || this.day > 31)
    		errors.add("day", new SimpleError("Please enter a valid day of birth"));
    	if(this.year < 2009)
    		errors.add("year", new SimpleError("Please enter a valid year of birth"));
		
		//Weight
    	if (this.patientWeight == 0) errors.add("weight", new SimpleError("Please enter a weight."));
    	//Height
    	if (this.patientHeight == 0) errors.add("height", new SimpleError("Please enter a weight."));
    	
    	//Sex
    	if (this.patientSex != 'm' && this.patientSex != 'f') errors.add("sex", new SimpleError(
    			"Please enter your sex. m for male. f for female"));
    	    	
    	//Emergency Contact Name
    	if (this.patientEmergencyConatactName != null && this.patientEmergencyConatactName.length() > 30) 
    		errors.add("emergencyContactName", 
    				new SimpleError("Emergency Contact Name should be less than 30 characters."));
    	
    	//Emergency Contact Phone
    	if (this.patientEmergencyConatactNumber != null){
    	String ptrStr = "\\d{10}";
    	Pattern p = Pattern.compile(ptrStr);
    	Matcher m = p.matcher(this.patientEmergencyConatactNumber);
    	if(!m.find()) 
    		errors.add("emergencyContactPhone", new SimpleError("Please enter a valid " +
    				"emergency phone number"));
      	else if (this.patientEmergencyConatactName == null)
    		errors.add("emergencyContactName", 
    				new SimpleError("Please enter a Emergency Contact Name " +
    						"for the telephone number " + this.patientEmergencyConatactNumber));
    	}
    	
    	//Insurance Provider
		if (this.patientInsuranceProvider != null && this.patientFirstName.length() > 30) 
			errors.add("insurance", new SimpleError("Please make sure insurance name is less than 30 characters."));
	 
		//Insurance Provider ID
		if (this.patientInsuranceId != null && this.patientFirstName.length() > 30) 
			errors.add("insuranceID", new SimpleError("Please make sure insurance ID is less than 30 characters."));
	 
		//SSN
		if (this.patientSSN != null && this.patientFirstName.length() > 9) 
			errors.add("SSN", new SimpleError("Please make sure SSN is less than 30 characters."));
		
	}
	
	
	//SQL CALLS
	public String lookupPatientNamebyID(int id){
		String returnString;
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			ResultSet results = statement.executeQuery ("SELECT patientFirstName, patientLastName FROM patients WHERE patientid='"+id+"'");
			
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
	
	public boolean lookupPatient(int id) {
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
            System.err.println ("Error in lookupPatient (Patient.java)");
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
					doctorId = rs.getInt("doctorid");
					patientFirstName = rs.getString("patientfirstname");
					patientLastName = rs.getString("patientlastname");
					patientMiddleName = rs.getString("patientmiddlename");
					patientDOB = rs.getDate("patientdob");
					patientHeight = rs.getInt("patientheight");
					patientWeight = rs.getInt("patientweight");
					patientSex = rs.getInt("patientsex");
					patientEmergencyConatactName = rs.getString("patientemergencycontactname");
					patientEmergencyConatactNumber = rs.getString("patientemergencycontactnumber");
					patientInsuranceProvider = rs.getString("patientinsuranceprovider");
					patientInsuranceId = rs.getString("patientinsuranceid");
					patientSSN = rs.getString("patientssn");

					this.day = patientDOB.getDate();
					this.month = patientDOB.getMonth();
					this.year = patientDOB.getYear();
					
				}else return false;
			} catch (SQLException e) {
				System.err.println("Error in loadPatientData (Patient.java)");
	            System.err.println (e.toString());
				return false;
			}
		return false;
	}
	
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
	
	
	
	
}