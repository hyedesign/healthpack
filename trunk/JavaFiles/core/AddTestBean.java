/**********************************************************
* File: core.AddTestBean.java
* Author: Han Dong
* Date Created: 4/28/2009
*
* Description: AddTestBean.java shall let the user add
*              a new test result for the patient.
*
**********************************************************/
package core;

import java.sql.Date;
import java.util.Calendar;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

public class AddTestBean implements ActionBean
{
	//instance variables
	private ActionBeanContext context;
	@Validate(required=true, maxlength=20) private String testName;
	@Validate(required=true, maxlength=20) private String testResult;
	@Validate(required=false) private String testDescription;
	private Date testDate;
	@Validate(required=true, maxlength=2, minlength=2) private int testMonth;
	@Validate(required=true, maxlength=2, minlength=2)  private int testDay;
	@Validate(required=true, maxlength=4, minlength=4) private int testYear;
	
	//getters and setters
	public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
	public String getTestName() { return testName; }
	public void setTestName(String testName) { this.testName = testName; }
	public String getTestResult() { return testResult; }
	public void setTestResult(String testResult) { this.testResult = testResult; }
	public String getTestDescription() { return testDescription; }
	public void setTestDescription(String testDescription) { this.testDescription = testDescription; }
	public int getTestMonth() { return testMonth; }
	public void setTestMonth(int testMonth) { this.testMonth = testMonth; }
	public int getTestDay() { return testDay; }
	public void setTestDay(int testDay) { this.testDay = testDay; }
	public int getTestYear() { return testYear; }
	public void setTestYear(int testYear) { this.testYear = testYear; }
	
	/**
	 * submit - adds the relevant test info as a new entry
	 *          into the database
	 * @return
	 */
	@DefaultHandler
    public Resolution submit()
	{
		//converts the month, day, year the user inputs
		//into a Date format for the SQL database
		Calendar cal = Calendar.getInstance();
		cal.set(this.testYear, this.testMonth-1, this.testDay);
		this.testDate = new Date(cal.getTime().getTime());
		
		//calls static addTest method to add new entry
		EditTestSQL.addTest(this.testName, this.testResult,
				this.testDescription, this.testDate, 8);
		
		//goes back to home page
		return new ForwardResolution("patientHome.jsp");
	}
    
	/**
	 * checksTestInfo - validates inputs for test fields.
	 * 					eg. apostrophe, special characters, valid date
	 * @param errors
	 */
    @ValidationMethod(on="submit")
    public void checksTestInfo(ValidationErrors errors) 
    {	
    	//escapes all apostrophes for the SQL database
    	this.testName = this.testName.replaceAll("\\'", "''");
    	this.testResult = this.testResult.replaceAll("\\'", "''");
    	this.testDescription = this.testDescription.replaceAll("\\'", "''");
    	
    	//checks for special characters
    	if(hasSpecialCharacters(this.testName))
		{
			errors.add("testName", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
		}
    	
    	if(hasSpecialCharacters(this.testResult))
		{
			errors.add("Result", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
		}
    	
    	if(hasSpecialCharacters(this.testDescription))
		{
			errors.add("testDescription", new SimpleError("These characters are not allowed in Description: <> () [] \\ / | = + * @ $ # ^ : ; "));
		}
    	
    	
    	//checks the date to and make sure its valid
    	if (!(testMonth == 0 && testDay == 0 && testYear == 0)){
	    	if(testMonth < 1 || testMonth > 12)
	    		errors.add("birthMonth", new SimpleError("Invalid Birth Month"));
	    	if(testDay < 1 || testDay > 31)
	    		errors.add("birthDay", new SimpleError("Invalid Birth Day"));
	    	if(testYear > 2009 || testYear == 0)
	    		errors.add("birthYear", new SimpleError("Invalid Birth Year"));
    	}
    }
    
    /**
	 * Checks the inputed string for illegal characters
	 * and returns true if any are found. Otherwise it
	 * returns false.
	 * @param s
	 * @return
	 */
	private boolean hasSpecialCharacters(String s) {
		if (s != s.replaceAll("([^A-Za-z0-9.,!?~`'\"% _-]+)", "")) return true;
		return false;
	}
}
