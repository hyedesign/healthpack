package core;

import java.io.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class DownloadServlet extends HttpServlet
{
	private DBAccess dba = new DBAccess();
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    {
		 res.setContentType("application/octet-stream");
	     res.setHeader("Content-Disposition","attachment;filename=PatientInfo.csv");

	     try 
	     {
	    	 ServletOutputStream out = res.getOutputStream();
	    	 StringBuffer sb = getPatientInfo();

	    	 InputStream in = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));

	    	 byte[] outputByte = new byte[50000];
	    	 
	    	 //copy binary contect to output stream
	    	 while(in.read(outputByte, 0, 5000) != -1)
	    	 {
	    		 out.write(outputByte, 0, 5000);
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
	
	public StringBuffer getPatientInfo()
	{
		StringBuffer sb = new StringBuffer();
		dba.connect(); // connect to the database
		String patientName = "";
		Date patientDOB = null;
		int patientWeight = 0;
		int patientHeight = 0;
		int sex = 0;
		String emContactName = null;
		String emContactNum = null;
		String insProvider = null;
		String insID = null;
		//String patientssn = null;
		
		try 
		{
			Statement statement = dba.connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM cmsc345.patients WHERE" +
					" userid='3'");
			while(results.next())
			{
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
				sb.append("Emergency Contact: ,");
				sb.append(emContactName+"\n");
				
				sb.append("Emergency Contact Number: ,");
				sb.append(emContactNum+"\n");
				
				sb.append("Insurance Provider: ,");
				sb.append(insProvider+"\n");
				
				sb.append("Insurance ID: ,");
				sb.append(insID+"\n\n");
				sb.append("----------------------------------------------------\n\n");
					
			}
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
