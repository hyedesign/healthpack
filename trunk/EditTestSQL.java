/**********************************************************
* File: core.EditTestSQL.java
* Author: Han Dong
* Date Created: 4/28/2009
*
* Description: The EditTestSQL class handles loading and updating
*              the test table in the HealthPack database.
*
**********************************************************/
package core;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditTestSQL 
{
	//instance variables
	private String testName;
	private String testResult;
	private String testDescription;
	private Date testDate;

	/**
	 * Constructor that sets all fields to null
	 */
	public EditTestSQL() 
	{
		this.testName = null;
		this.testResult = null;
		this.testDescription = null;
		this.testDate = null;
	}
	
	//getters and setters
	public String getTestName() { return testName; }
	public void setTestName(String testName) { this.testName = testName; }
	public String getTestResult() { return testResult; }
	public void setTestResult(String testResult) { this.testResult = testResult; }
	public String getTestDescription() { return testDescription; }
	public void setTestDescription(String testDescription) { this.testDescription = testDescription; }
	public Date getTestDate() { return testDate; }
	public void setTestDate(Date testDate) { this.testDate = testDate; }

	/* SQL Access Methods */

	/**
	 * Connects to the 'tests' table in the mySQL database using
	 * DBAccess and attempts to load the test data specified by the
	 * testID. 
	 * 
	 * @param testID the test id that will be used to look up the information
	 * @return true when the test specified can be loaded from the
	 * database, false when the test
	 * @author Han Dong
	 */
	public boolean lookupTest(int testID)
	{
		DBAccess dba = new DBAccess();
		dba.connect(); // connect to the database
		try {
			// construct and execute the SQL call, retrieve the results
			Statement statement = dba.connection.createStatement ();
			ResultSet results = statement.executeQuery ("SELECT * FROM cmsc345.tests WHERE testid='"+testID+"'");
			
			// attempt to load the test data from the ResultSet
			boolean successfulLoad = loadTestData(results);
			
			// close connections
			results.close();
			statement.close();
			dba.disconnect();
			//return true if test was found
			return successfulLoad;
			
		} catch (SQLException e) {
            System.err.println ("Method login() performed bad SQL call");
            System.err.println (e.toString());
			dba.disconnect();
            return false;
		}
	}
	
	/**
	 * Takes a ResultSet object and loads the test data from it.
	 * The result set must be the result of a query to the tests
	 * table.
	 * 
	 * @param rs the result set that contains a single test's data
	 * @return true when the data is loaded, false when the ResultSet is
	 * empty and there is no data to load
	 * @author Han Dong
	 */
	private boolean loadTestData (ResultSet rs) {
		// check that the resultset isn't empty and load the test data
		try {
			if (rs.first()) {
				testName = rs.getString("testname");
				testResult = rs.getString("testresult");
				testDescription = rs.getString("testdescription");
				testDate = rs.getDate("testdate");
				return true;
			}
			// the resultset is empty, no data loaded
			else return false;
		} catch (SQLException e) {
			System.err.println("Bad ResultSet call from UserSQL.loadUserData()");
            System.err.println (e.toString());
			return false;
		}
	}
	
	/**
	 * Updates the 'tests' table in the mySQL database with the arguments 
	 * passed in
	 * 
	 * @param testName is the name of the test
	 * @param testResult is the result of the test
	 * @param testDescription is the description of the test, if there is one
	 * @param testDate is the date on which the test was performed
	 * @param testID the id of the test in the table to be updated
	 * @author Han Dong
	 */
	public static void updateTest(String testName, String testResult, String testDescription,
			Date testDate, int testID)
	{	
		DBAccess dba_s = new DBAccess();
		dba_s.connect(); // connect to the database
		try
		{	
			Statement statement = dba_s.connection.createStatement();
			
			//if there's no test description specified, sets to empty string
			if(testDescription == null) { testDescription = ""; }
			
			//executes query statement
			statement.executeUpdate("UPDATE cmsc345.tests SET testname='"+testName+"', testresult='"
					+testResult+"', testdescription='"+testDescription+"', testdate='"+testDate+"' "
					+"WHERE testid='"+testID+"'");
			statement.close();
			dba_s.disconnect();
		}
		catch (SQLException e)
		{
			System.err.println (e.toString());
			System.out.println("Failed to update.");
			dba_s.disconnect();
		}
	}
	
	/**
	 * adds a new entry in the 'tests' table in the mySQL database
	 * 
	 * @param testName is the name of the test
	 * @param testResult is the result of the test
	 * @param testDescription is the description of the test, if there is one
	 * @param testDate is the date on which the test was performed
	 * @param testID the id of the test in the table to be updated
	 * @param patientID is the id of the patient that had the test
	 * @author Han Dong
	 */
	public static void addTest(String testName, String testResult, String testDescription,
			Date testDate, int patientID)
	{	
		DBAccess dba_s = new DBAccess();
		dba_s.connect(); // connect to the database
		try
		{	
			Statement statement = dba_s.connection.createStatement();
			
			//changes description to empty string should it be null
			if(testDescription == null) { testDescription = ""; }
			
			//executes SQL query statement and adds new entry
			statement.executeUpdate("INSERT INTO cmsc345.tests " +
					"(patientid, testname, testresult, testdescription, testdate)"
					+" VALUES('"+patientID+"', '"+testName+"', '"+testResult+"', '"
					+testDescription+"', '"+testDate+"')");
			statement.close();
			dba_s.disconnect();
		}
		catch (SQLException e)
		{
			System.err.println (e.toString());
			System.out.println("Failed to update.");
			dba_s.disconnect();
		}
	}
	
	/**
	 * deletes a test from the 'tests' table in the mySQL database
	 * @param testID is the id of the test
	 * @author Han Dong
	 */
	public static void deleteTest(int testID)
	{
		DBAccess dba_s = new DBAccess();
		dba_s.connect(); // connect to the database
		try
		{	
			// construct and execute the SQL call, deletes test
			Statement statement = dba_s.connection.createStatement();
			statement.executeUpdate("DELETE FROM cmsc345.tests" +
					" WHERE testid='"+testID+"';");
			statement.close();
			dba_s.disconnect();
		}
		catch (SQLException e)
		{
			System.err.println (e.toString());
			dba_s.disconnect();
		}
	}
}
