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
	private DBAccess dba = new DBAccess();
	
    private int patientId;
	private int userId;
	@Validate(required=true, maxlength=30) private String firstName ="";
	@Validate(required=true, maxlength=30) private String lastName="";
	@Validate(required=false, maxlength=30) private String middleName="";
	private Date patientDOB;
	private int height;
	private int weight;
	@Validate(required=true, maxlength=1) private char patientSex;
	@Validate(required=false, maxlength=30) private String emergencyContactName ="";
	@Validate(required=false, maxlength=10) private String emergencyContactPhone="";
	@Validate(required=false, maxlength=30) private String insurance="";
	@Validate(required=false, maxlength=30) private String insuranceID="";
	@Validate(required=false, maxlength=9) private String SSN="";
    private int birthMonth;
    private int birthDay;
    private int birthYear;

   
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
    		new Patient(this.patientId, this.userId, this.firstName,
    				this.lastName, this.middleName, this.patientDOB, this.height,
    				this.weight, this.patientSex, this.emergencyContactName,
    				this.emergencyContactPhone, this.insurance, this.insuranceID,
    				this.SSN).CreateNewPatient();
    	}else{ 
    		new Patient(this.patientId).SetAndUpdateCurrentPatient(this.firstName,
    				this.lastName, this.middleName, this.patientDOB, this.height,
    				this.weight, this.patientSex, this.emergencyContactName,
    				this.emergencyContactPhone, this.insurance, this.insuranceID,
    				this.SSN);
    	}
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
    
	//SQL CALLS
	private String lookupPatientNamebyID(int id){
		String returnString;
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			ResultSet results = statement.executeQuery ("SELECT patientFirstName, " +
					"patientLastName FROM patients WHERE patientid='"+id+"'");
			
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
            System.err.println ("Error in lookupPatient (Patient.java)");
            System.err.println (e.toString());
			dba.disconnect();
            return false;
		}
	}
	
	private boolean insertPatient (){
		dba.connect(); // connect to the database
		try {
			
			Date birth = new Date(birthYear, birthMonth, birthDay);
			int sex;
			if (patientSex == 'm') sex = 0; else sex = 1;
			
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			statement.executeUpdate("INSERT INTO cmsc345.patients" +
					" VALUES ( 0 ,"+userId+",'"+firstName+"','"+lastName+"','"
					+middleName+"','"+birth.toString()+"',"+weight+","+height+","+sex+",'"+emergencyContactName+"','" 
					+emergencyContactPhone+"','"+insurance+"','"+insuranceID+"','"+SSN+"');");
			statement.close();
			dba.disconnect();
			//return true if user was found
			
			return true;
		} catch (SQLException e) {
            System.err.println ("Error in inserting a patient");
            System.err.println (e.toString());
			dba.disconnect();
			return false;
		}
	}
	
	private boolean updatePatient(){
		dba.connect(); // connect to the database
		try {
			Date birth = new Date(birthYear, birthMonth, birthDay);
			int sex;
			if (patientSex == 'm') sex = 0; else sex = 1;
						
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			
			statement.executeUpdate("UPDATE cmsc345.patients" +
					" SET patientfirstname='"+firstName+"', patientlastname'"+lastName+"', " +
						"patientmiddlename='"+middleName+"', patientdob='"+birth.toString()+"', " +
						"patientweight="+weight+", patientheight="+height+", patientsex="+sex+", " +
						"patientemergencycontactname='"+emergencyContactName+"', " +
						"patientemergencycontactnumber='"+emergencyContactPhone+"', " +
						"patientinsuranceprovider='"+insurance+"',patientinsuranceid='"+insuranceID+"'," +
						"patientssn='"+SSN+"' " +
					"WHERE patientid = " + this.patientId +";");
			
			statement.close();
			dba.disconnect();
			//return true if user was found
			return true;
		} catch (SQLException e) {
            System.err.println ("Error in Updating a patient");
            System.err.println (e.toString());
			dba.disconnect();
			return false;
		}		
	}
	
	private boolean deletePatient(int PATID){
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
						
			statement.executeUpdate("DELETE FROM cmsc345.patients" +
					"WHERE patientid = " + PATID +";");
			
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
	
					if (rs.getInt("patientsex") == 0) patientSex = 'm';
					else patientSex = 'f';
	
					emergencyContactName = rs.getString("patientemergencycontactname");
					emergencyContactPhone = rs.getString("patientemergencycontactnumber");
					insurance = rs.getString("patientinsuranceprovider");
					insuranceID = rs.getString("patientinsuranceid");
					SSN = rs.getString("patientssn");

					birthDay = patientDOB.getDate();
					birthMonth = patientDOB.getMonth();
					birthYear = patientDOB.getYear();
					
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
	
	/*
	 * PUBLIC METHODS
	 */
	public String PatientNameFromID(int ID){
		return lookupPatientNamebyID(ID);
	}
	public boolean CreateNewPatient(){
		return insertPatient();
	}
	public boolean UpdatePatient(){
		return insertPatient();
	}
	public boolean DeletePatien(int id){
		return deletePatient(id);
	}
}