/**********************************************************
* File: core.GetTestBean.java
* Author: Han Dong
* Date Created: 4/28/2009
*
* Description: Fetches user information for database and 
* 			   forwards info to editTest.jsp to fill
*			   the fields.
*
**********************************************************/
package core;

import java.sql.Date;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class GetTestBean implements ActionBean
{
	//instance variables corresponding to fields in editTest.jsp
	private ActionBeanContext context;
	private int testID;
	private String testName;
	private String testResult;
	private String testDescription;
	private Date testDate;
	private int testMonth;
	private int testDay;
	private int testYear;
		
	//getters and setters
	public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
	public int getTestID() { return testID; }
	public void setTestID(int testID) { this.testID =testID; }
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
	 * submit - access the database and gets the entry with the 
	 * 			corresponding testID. Assigns values to instance
	 * 			variables of GetTestBean, then forwards 
	 * 			information to editTest.jsp
	 * @return
	 */
	@DefaultHandler
    public Resolution submit() 
	{
		//creates object of type UserSQL and looks up the test
		//with the same testID

		EditTestSQL temp = new EditTestSQL();
		temp.lookupTest(this.testID);

		//gets all necessary fields to be displayed
		this.testName = temp.getTestName();
		this.testResult = temp.getTestResult();
		this.testDescription = temp.getTestDescription();
		this.testDate = temp.getTestDate();

		String s = testDate.toString();
		int index = s.indexOf('-');
		this.testYear =Integer.parseInt(s.substring(0, index));
		s = s.substring(index+1, s.length());
		index = s.indexOf('-');
		this.testMonth = Integer.parseInt(s.substring(0,index));
		this.testDay = Integer.parseInt(s.substring(index + 1, s.length()));

		return new ForwardResolution("editTest.jsp");	
    }
	
	/**
     * deletes the test specified by the testID
     * from the mySQL database
     * 
     * @author Han Dong
     */
    public Resolution delete()
    {
    	EditTestSQL.deleteTest(this.testID);
    	return new ForwardResolution("patientList.jsp");
    }
}
