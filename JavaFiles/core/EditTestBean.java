/**********************************************************
* File: core.EditTestBean.java
* Author: Han Dong
* Date Created: 4/23/2009
*
* Description: Verifies test info and updates SQL db
*
* Edited  : 
* Changes : 
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

public class EditTestBean implements ActionBean
{
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
	
	/* Handles form validation */
	@DefaultHandler
    public Resolution submit()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(this.testYear, this.testMonth-1, this.testDay);
		this.testDate = new Date(cal.getTime().getTime());
		new EditTestSQL(this.testName, this.testResult,
				this.testDescription, this.testDate).updateTest(8);
		return new ForwardResolution("patientHome.jsp");
	}
    
	//VALIDATION 
    @ValidationMethod(on="submit")
    public void dateFormat(ValidationErrors errors) 
    {	
    	//test date
    	if (!(testMonth == 0 && testDay == 0 && testYear == 0)){
	    	if(testMonth < 1 || testMonth > 12)
	    		errors.add("birthMonth", new SimpleError("Invalid Birth Month"));
	    	if(testDay < 1 || testDay > 31)
	    		errors.add("birthDay", new SimpleError("Invalid Birth Day"));
	    	if(testYear > 2009 || testYear == 0)
	    		errors.add("birthYear", new SimpleError("Invalid Birth Year"));
    	}
    }
}
