
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%> 
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit Patient</title>
<link href="HealthPackStyle.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    input.error { background-color: yellow; }
</style>
</head>

<body>

<div id="container">
<div id="header"></div>
<div id="message">UserName</div>
<!----------------------- NAVIGATION  ----------------------->
<div id="navigation">  
	<%@ include file='LinksInc.jsp' %>
</div>

<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->

<% 	
	
	int PATID = 0;
	boolean newPatient = true;
	core.PatientBean patient = new core.PatientBean();
	try{
		// TODO
		// NEED A WAY TO GET PATIENT ID TO THE PAGE !!!
		PATID = Integer.parseInt(session.getAttribute("patientID").toString());
		newPatient= PATID == 0;
		if (!newPatient){
				patient = new core.PatientBean(PATID);
			}
	}catch(Exception e){
		System.out.println("error parsing patient id");
		newPatient = true;
	}
	%>

<h1>Patient Info</h1>
<p>Please enter your patient information</p>

<stripes:form beanclass="core.PatientBean" focus="">
		<stripes:errors/>
        <table>
        	<stripes:hidden name="userId" value="<% out.print(session.getAttribute("id"));%>" /> 
        	<stripes:hidden name="patientId" value="
        	<%	if(newPatient)
        	 		out.print('0'); 
        		else 
        			out.print(new Integer(PATID).toString());%>" /> 
           <tr>
                <td>First Name:</td>
                <td><stripes:text name="firstName" maxlength = "30" <%	if (!newPatient)out.print("value='"+ 
                														patient.getFirstName() +"'"); %>/></td>
            </tr>
            <tr>
                <td>Middle Name:</td>
                <td><stripes:text name="middleName" maxlength = "30" 
                													<%	if (!newPatient)out.print("value='"+ 
                														patient.getMiddleName() +"'"); %>/></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><stripes:text name="lastName" maxlength = "30" 	
                													<%	if (!newPatient)out.print("value='"+ 
                														patient.getLastName() +"'"); %>/></td>
            </tr> 
         	<tr>  
                <td>Date Of Birth:</td>
    			<td>
	    			<stripes:text name="birthMonth" size="1" maxlength = "2" 
	    															<%	if (!newPatient)out.print("value='"+ 
                														patient.getBirthMonth() +"'"); %>/>/
				    <stripes:text name="birthDay" size="1" maxlength = "2" 
				    												<%	if (!newPatient)out.print("value='"+ 
                														patient.getBirthDay() +"'"); %>/>/ 
				    <stripes:text name="birthYear" size="2" maxlength = "4" 
				    												<%	if (!newPatient)out.print("value='"+ 
                														patient.getBirthYear() +"'"); %>/>
				</td>
	        </tr>
            <tr>
                <td>Weight:</td>
                <td><stripes:text name="weight" maxlength = "3" 
																	<%	if (!newPatient)out.print("value='"+ 
                														patient.getWeight() +"'"); %>/></td>
            </tr>
            <tr>
                <td>Height:</td>
                <td><stripes:text name="height" maxlength = "10" 
																	<%	if (!newPatient)out.print("value='"+ 
                														patient.getHeight() +"'"); %>/></td>
            </tr>
            <tr>
                <td>Sex (m/f):</td>
                <td><stripes:text name="patientSex" maxlength = "1" 
																	<%	if (!newPatient)out.print("value='"+ 
                														patient.getPatientSex() +"'"); %>/></td>
            </tr>
            <tr>
                <td>Emergency Contact Name:</td>
                <td><stripes:text name="emergencyContactName" maxlength = "30" 
                													<%	if (!newPatient)out.print("value='"+ 
                														patient.getEmergencyContactName() +"'"); %>/></td>
            </tr>
            <tr>
                <td>Emergency Contact Phone:</td>
                <td><stripes:text name="emergencyContactPhone" maxlength = "10"
                													<%	if (!newPatient)out.print("value='"+ 
                														patient.getEmergencyContactPhone() +"'"); %>/></td>
            </tr>
            <tr>
                <td>Insurance Provider Name:</td>
                <td><stripes:text name="insurance" maxlength = "30"
                													<%	if (!newPatient)out.print("value='"+ 
                														patient.getInsurance() +"'"); %>/></td>
            </tr>
            <tr>
                <td>Insurance Number:</td>
                <td><stripes:text name="insuranceID" maxlength = "30"
                													<%	if (!newPatient)out.print("value='"+ 
                														patient.getInsuranceID() +"'"); %>/></td>
            </tr>
            <tr>
                <td>Social Security Number:</td>
                <td><stripes:text name="SSN" maxlength = "9"
                													<%	if (!newPatient)out.print("value='"+ 
                														patient.getSSN() +"'"); %>/></td>
            </tr>         
            <tr>
                <td colspan="2">
                    <stripes:submit name="submit" value="
        	<%	if(newPatient)
        	 		out.print("Submit"); 
        		else 
        			out.print("Update");%>"/>                    
                </td>
            </tr>
        </table>
    </stripes:form>

<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> 
  <%@include file="FooterInc.jsp" %>
</div>

</div>



</div>

</body>
</html>
