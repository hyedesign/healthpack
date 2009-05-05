/**********************************************************
 * File: core.DownloadServlet.java
 * Author: Han Dong
 * Date Created: 4/30/2009
 *
 * Description: DownloadServlet.java shall let the the user
 * 			   export all of his patient data along with
 *              the patient's medications, allergies, tests,
 *              appointments into a comma delimited excel
 *              spreadsheet.
 *
 **********************************************************/
package core;

import java.io.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class DownloadServlet extends HttpServlet
{
	//dba access allows access to mySQL database
	private DBAccess dba = new DBAccess();
	//temporarily here
	private ArrayList<Integer> patientAry = new ArrayList<Integer>();
	//keeps track of patient number
	public static String patientNum = "";

	/**
	 * The java servlet will allow the user to export into a comma delimited
	 * excel spreadsheet.
	 * @author Han Dong
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		//setting the content type, which is a stream and filename "PatientInfo.csv"
		res.setContentType("application/octet-stream");
		res.setHeader("Content-Disposition","attachment;filename=PatientInfo.csv");

		HttpSession session = req.getSession();
		int userID = Integer.parseInt(session.getAttribute("userid").toString());
		//int userID = 1;
		
		//tries to output file to user screen
		try 
		{
			//creates a new servlet output stream
			ServletOutputStream out = res.getOutputStream();
			
			//getPatientInfo method will return all necessary data
			//to be exported
			StringBuffer sb = getPatientInfo(userID);

			//flushes the output so the user can download
			InputStream in = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));

			byte[] outputByte = new byte[sb.length()+1000];

			//copy binary contect to output stream
			while(in.read(outputByte, 0, sb.length()+1000) != -1)
			{
				out.write(outputByte, 0, sb.length()+1000);
			}
			in.close();
			out.flush();
			out.close();

		}
		catch(Exception e)
		{
			System.out.println("Failed to get patient info.");
		}
	}

	/**
	 * Connects to mySQL database and gets all patient data.
	 * Then calls subsequent functions that will get other
	 * data that relates to specific patient.
	 * 
	 * @param userID
	 * @return returns the string buffer to be downloaded
	 * @author Han Dong
	 */
	public StringBuffer getPatientInfo(int userID)
	{
		//creates new string buffer
		StringBuffer sb = new StringBuffer();
		dba.connect(); // connect to the database
		
		//variables of patient data in database
		int patientID = 0;
		String patientName = "";
		Date patientDOB = null;
		int patientWeight = 0;
		int patientHeight = 0;
		int sex = 0;
		String emContactName = null;
		String emContactNum = null;
		String insProvider = null;
		String insID = null;
		int counter = 1;
		
		sb.append("****************************************************" +
		"***********************************************************\n");
		
		//try catch block to access database and get data
		try 
		{
			//creates new sql query and executes
			Statement statement = dba.connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM cmsc345.patients WHERE" +
			" userid='3'");
			
			//loops through all patient
			while(results.next())
			{
				//gets patient data
				patientID = results.getInt("patientid");
				patientName = results.getString("patientfirstname")+" "+
				results.getString("patientmiddlename")+". "+results.getString("patientlastname");
				patientDOB = results.getDate("patientdob");
				patientWeight = results.getInt("patientweight");
				patientHeight = results.getInt("patientheight");
				sex = results.getInt("patientsex");
				emContactName = results.getString("patientemergencycontactname");
				emContactNum = results.getString("patientemergencycontactnumber");
				insProvider = results.getString("patientinsuranceprovider");
				insID = results.getString("patientinsuranceid");
				//patientssn = results.getString("patientssn");

				//appending patient data to string buffer
				patientNum = "[PATIENT "+counter;
				sb.append(patientNum+"]\n");
				sb.append("Name: ,");
				sb.append(patientName+"\n");

				sb.append("Date of Birth: ,");
				sb.append(patientDOB.toString()+"\n");

				sb.append("Weight: ,");
				if(patientWeight == 0){ sb.append("\n"); }
				else { sb.append(patientWeight+" lbs\n"); }

				sb.append("Height: ,");
				if(patientHeight == 0){ sb.append("\n"); }
				else { sb.append(patientHeight+" inchs\n"); }

				sb.append("Sex: ,");
				if(sex == 0)
				{
					sb.append("M\n");
				}
				else
				{
					sb.append("F\n");
				}
				if(emContactName == null) {emContactName = "";}
				sb.append("Emergency Contact: ,");
				sb.append(emContactName+"\n");

				if(emContactNum == null) {emContactNum = "";}
				sb.append("Emergency Contact Number: ,");
				sb.append(emContactNum+"\n");

				if(insProvider == null) {insProvider = "";}
				sb.append("Insurance Provider: ,");
				sb.append(insProvider+"\n");

				if(insID == null) {insID = "";}
				sb.append("Insurance ID: ,");
				sb.append(insID+"\n\n");

				//these methods will get other data relating to 
				//the specific patient based off his/her
				//patient id
				sb = getPatientAllergy(patientID, sb);
				sb = getPatientMedications(patientID, sb);
				sb = getPatientAppointments(patientID, sb);
				sb = getPatientTests(patientID, sb);
				sb = getDoctorNotes(patientID, sb);
				counter ++;
				sb.append("\n\n****************************************************" +
				"***********************************************************\n\n\n");
			}
			results.close();
			statement.close();
			dba.disconnect();
		}
		catch (SQLException e)
		{
			System.err.println (e.toString());
			sb.append("Failed");
			dba.disconnect();
		}

		return sb;
	}

	/**
	 * Gets the doctors note of the patient
	 * 
	 * @param patientID
	 * @param sb is the string buffer
	 * @return
	 * @author Han Dong
	 */
	public StringBuffer getDoctorNotes(int patientID, StringBuffer sb)
	{
		String note = null;
		
		sb.append("[Doctors Note]\n");
		try 
		{
			//creates and executes query
			Statement statement = dba.connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM cmsc345.doctorpatient WHERE" +
					" patientid="+patientID);
			while(results.next())
			{
				//appends all doctor's note
				if(note == null) {note = "";}
				sb.append("Doctors Note: ,");
				sb.append(note+"\n\n");
			}
			results.close();
			statement.close();
		}
		catch (SQLException e)
		{
			System.err.println (e.toString());
			sb.append("Failed");
			dba.disconnect();
		}

		return sb;
	}

	/**
	 * Gets the allergies of the specific patient
	 * 
	 * @param patientID
	 * @param sb
	 * @return
	 * @author Han Dong
	 */
	public StringBuffer getPatientAllergy(int patientID, StringBuffer sb)
	{
		String allergyName = null;
		String allergyDescription = null;

		sb.append("[Allergies]\n");

		try 
		{
			//creates and executes query
			Statement statement = dba.connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM cmsc345.allergies WHERE" +
					" patientid="+patientID);
			//appends all allergy data to patient
			while(results.next())
			{
				allergyName = results.getString("allergy_name");
				allergyDescription = results.getString("allergy_description");
				sb.append("Allergy Name: ,");
				sb.append(allergyName+"\n");
				if(allergyDescription == null) {allergyDescription = "";}
				sb.append("Allergy Description: ,");
				sb.append(allergyDescription+"\n\n");
			}
			results.close();
			statement.close();
		}
		catch (SQLException e)
		{
			System.err.println (e.toString());
			sb.append("Failed");
			dba.disconnect();
		}

		return sb;
	}

	/**
	 * Gets the medications of the patient
	 * 
	 * @param patientID
	 * @param sb
	 * @return
	 * @author Han Dong
	 */
	public StringBuffer getPatientMedications(int patientID, StringBuffer sb)
	{
		String medicationName = null;
		String medicationDescription = null;
		String medicationExpirationDate = null;
		String medicationRefillDate = null;

		sb.append("[Medications]\n");

		try 
		{
			//creates and executes query
			Statement statement = dba.connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM cmsc345.medications WHERE" +
					" patientid="+patientID);
			
			//appends all medication data to patient
			while(results.next())
			{
				medicationName = results.getString("medicationname");
				medicationDescription = results.getString("medicationDescription");
				medicationExpirationDate = results.getDate("medicationexpirationsdate").toString();
				medicationRefillDate = results.getDate("medicationrefilldate").toString();
				sb.append("Medication Name: ,");
				sb.append(medicationName+"\n");
				if(medicationDescription == null) {medicationDescription = "";}
				sb.append("Medication Description: ,");
				sb.append(medicationDescription+"\n");
				sb.append("Expiration Date: ,");
				sb.append(medicationExpirationDate+"\n");
				sb.append("Refill Date: ,");
				sb.append(medicationRefillDate+"\n\n");
			}
			results.close();
			statement.close();
		}
		catch (SQLException e)
		{
			System.err.println (e.toString());
			sb.append("Failed");
			dba.disconnect();
		}

		return sb;
	}

	/**
	 * Gets the appointment information of the patient
	 * 
	 * @param patientID
	 * @param sb
	 * @return
	 * @author Han Dong
	 */
	public StringBuffer getPatientAppointments(int patientID, StringBuffer sb)
	{
		String appDate = null;
		String appDescription = null;

		sb.append("[Appointments]\n");

		try 
		{
			//creates and executes query
			Statement statement = dba.connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM cmsc345.appointments WHERE" +
					" patientid="+patientID);
			
			//appends all appointment data to patient
			while(results.next())
			{
				appDate = results.getDate("appointmentdate").toString();
				appDescription = results.getString("appointmentdescription");
				sb.append("Appointment Date: ,");
				sb.append(appDate+"\n");
				if(appDescription == null) {appDescription = "";}
				sb.append("Appointment Description: ,");
				sb.append(appDescription+"\n\n");
			}
			results.close();
			statement.close();
		}
		catch (SQLException e)
		{
			System.err.println (e.toString());
			sb.append("Failed");
			dba.disconnect();
		}

		return sb;
	}

	/**
	 * Gets the test data of the patient
	 * 
	 * @param patientID
	 * @param sb
	 * @return
	 * @author Han Dong
	 */
	public StringBuffer getPatientTests(int patientID, StringBuffer sb)
	{
		String testName = null;
		String testDate = null;
		String testDescription = null;
		String testResult = null;

		sb.append("[Tests]\n");

		try 
		{
			//creates and executes query
			Statement statement = dba.connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM cmsc345.tests WHERE" +
					" patientid="+patientID);
			
			//appends all test data to patient
			while(results.next())
			{
				testName = results.getString("testname");
				testDate = results.getDate("testdate").toString();
				testDescription = results.getString("testdescription");
				testResult = results.getString("testresult");
				sb.append("Test Name: ,");
				sb.append(testName+"\n");
				sb.append("Test Date: ,");
				sb.append(testDate+"\n");
				if(testDescription == null) {testDescription = "";}
				sb.append("Test Description: ,");
				sb.append(testDescription+"\n");
				sb.append("Test Result: ,");
				sb.append(testResult+"\n\n");
			}
			results.close();
			statement.close();
		}
		catch (SQLException e)
		{
			System.err.println (e.toString());
			sb.append("Failed");
			dba.disconnect();
		}

		return sb;
	}
}
