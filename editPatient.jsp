
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
<jsp:include  page="LinksInc.jsp" flush="true"/>
</div>

<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->

<% 	
	// TEMPORARY USER INFORMATION
	session.setAttribute("id", 3);
	session.setAttribute("patientID", 2);

	//Variables

	String firstName = "";
	String middleName = "";
	String lastName = "";
	String birthMonth = "";
	String birthDay = "";
	String birthYear = "";
	String weight = "";
	String height = "";
	String sex = "";
	String emContact = "";
	String emContactPhone = "";
	String insurace = "";
	String insuranceID = "";
	String SSN = "";
	String BUTTONTEXT = "Submit";
	String userID = session.getAttribute("id").toString();
	
	int PATID = 0;
	boolean newPatient = true;
	core.Patient patient = new core.Patient();
	try{
		//Patient Id assigned
		PATID = Integer.parseInt(session.getAttribute("patientID").toString());
		newPatient = PATID == 0;
		
		if (!newPatient){
				//ALL VALUES
				patient = new core.Patient(PATID);
				firstName = patient.getFirstName();
				middleName = patient.getMiddleName();
				lastName = patient.getLastName();
				birthMonth = new Integer(patient.getPatientDOBMonth()).toString();
				birthDay = new Integer(patient.getPatientDOBDay()).toString();
				birthYear = new Integer(patient.getPatientDOBYear()).toString();
				weight = new Integer(patient.getWeight()).toString();
				height = new Integer(patient.getHeight()).toString();
				sex += patient.getPatientSex();
				emContact = patient.getEmergencyContactName();
				emContactPhone = patient.getEmergencyContactPhone();
				insurace = patient.getInsurance();
				insuranceID = patient.getInsuranceID();
				SSN = patient.getSSN();		
				BUTTONTEXT = "Update";
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
        	<stripes:hidden name="userId" value= "<%=userID%>" /> 
        	<stripes:hidden name="patientId" value="<%=PATID %>"/> 
        	
           <tr>
                <td>First Name:</td>
                <td><stripes:text name="firstName" maxlength = "30" value="<%= firstName%>" /></td>
            </tr>
            <tr>
                <td>Middle Name:</td>
                <td><stripes:text name="middleName" maxlength = "30" value="<%= middleName%>" /></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><stripes:text name="lastName" maxlength = "30" 	 value="<%= lastName%>" /> </td>
            </tr> 
         	<tr>  
                <td>Date Of Birth:</td>
    			<td>
	    			<stripes:text name="birthMonth" size="1" maxlength = "2"  value="<%= birthMonth%>" />/
				    <stripes:text name="birthDay" size="1" maxlength = "2"  value="<%= birthDay%>" />/
				    <stripes:text name="birthYear" size="2" maxlength = "4"  value="<%= birthYear%>" />
				</td>
	        </tr>
            <tr>
                <td>Weight:</td>
                <td><stripes:text name="weight" maxlength = "3"   value="<%= weight%>" /></td> 
            </tr>
            <tr>
                <td>Height:</td>
                <td><stripes:text name="height" maxlength = "10"   value="<%= height%>" /></td> 
            </tr>
            <tr>
                <td>Sex (m/f):</td>
                <td><stripes:text name="patientSex" maxlength = "1"  value="<%= sex %>" /></td> 
            </tr>
            <tr>
                <td>Emergency Contact Name:</td>
                <td><stripes:text name="emergencyContactName" maxlength = "30"  value="<%= emContact%>" /></td> 
            </tr>
            <tr>
                <td>Emergency Contact Phone:</td>
                <td><stripes:text name="emergencyContactPhone" maxlength = "10" value="<%= emContactPhone%>" /></td> 
            </tr>
            <tr>
                <td>Insurance Provider Name:</td>
                <td><stripes:text name="insurance" maxlength = "30"  value="<%= insurace%>" /></td> 
            </tr>
            <tr>
                <td>Insurance Number:</td>
                <td><stripes:text name="insuranceID" maxlength = "30"  value="<%= insuranceID%>" /></td> 
            </tr>
            <tr>
                <td>Social Security Number:</td>
                <td><stripes:text name="SSN" maxlength = "9"  value="<%= SSN%>" /></td> 
            </tr>         
            <tr>
                <td colspan="2">
                    <stripes:submit name="submit" value="<%= BUTTONTEXT%>"/>                    
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
