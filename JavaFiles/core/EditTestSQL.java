package core;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

public class EditTestSQL 
{
	private DBAccess dba = new DBAccess();
	private Date date;
	private Date testDate;
	
	public EditTestSQL(String testName, String testResult,
			String testDescription, Date testDate) {
		super();
		this.testName = testName;
		this.testResult = testResult;
		this.testDescription = testDescription;
		this.testDate = testDate;
	}
	
	public void updateTest(int patientID)
	{
		System.out.println(this.testName+" "+this.testResult+" "+this.testDescription);
		
		dba.connect(); // connect to the database
		try
		{	
			Statement statement = dba.connection.createStatement();
			if(this.testDescription == null) { this.testDescription = ""; }
			
			statement.executeUpdate("INSERT INTO cmsc345.tests " +
					"(patientid, testname, testresult, testdescription, testdate)"
					+" VALUES('"+patientID+"', '"+this.testName+"', '"+this.testResult+"', '"
					+this.testDescription+"', '"+this.testDate+"')");
			statement.close();
			dba.disconnect();
		}
		catch (SQLException e)
		{
			System.err.println (e.toString());
			System.out.println("Failed to update.");
			dba.disconnect();
		}
	}
	
	public void deleteTest(int patientID, String testName)
	{
		dba.connect(); // connect to the database
		try
		{	
			Statement statement = dba.connection.createStatement();
			statement.executeUpdate("DELETE FROM cmsc345.tests" +
					" WHERE patientid='"+patientID+"' AND testname='"+testName+"';");
			statement.close();
			dba.disconnect();
		}
		catch (SQLException e)
		{
			System.err.println (e.toString());
			dba.disconnect();
		}
	}
	
}
