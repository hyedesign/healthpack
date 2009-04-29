<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: patientHome
  -- Date Modified: 04/28/09 
  -- Author: Taylor Evans
  -- Description: This file displays the specific user's 
  -- patient's homepage.
  --
  -- Last edited by: Alex
  -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%> 
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>Patient Home</title>
<link href="HealthPackStyle.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div id="container">
<div id="header"></div>
<div id="message">${sessionScope.username}</div>
<!----------------------- NAVIGATION  ----------------------->
<div id="navigation">  
  <%@include file="LinksInc.jsp" %>  
</div>

<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->
<c:if test="${empty sessionScope.userid}">
	<% response.sendRedirect("login.jsp"); %>
</c:if>

<h1>Patient Info</h1>
<h2>${actionBean.patientFirstName} ${actionBean.patientLastName}</h2>

<a href="editPatient.jsp">Edit Patient Info</a>
<table border="1">
	<tr>
		<td colspan="2"><b><u>MESSAGE FROM THE DOCTOR:</u></b></td>
	</tr>
	<tr>
		<td colspan="2">: ${actionBean.patientNote}</td>
	</tr>
	<tr>
		<td colspan="2"><b><u>REMINDERS:</u></b></td>
	</tr>
	<tr>
		<td>Annual Checkup - 05/05/05</td>
	</tr>
	<tr>
		<td colspan="2"><b><u>PATIENT INFORMATION</u></b></td>
	</tr>
	<tr>
		<td>First Name:</td>
		<td>${actionBean.patientFirstName}</td>
	</tr>
	<tr>
		<td>Middle Name:</td>
		<td>${actionBean.patientMiddleName}</td>
	</tr>
	<tr>
		<td>Last Name:</td>
		<td>${actionBean.patientLastName}</td>
	</tr>
	<tr>
		<td>Date Of Birth:</td>
		<td>${actionBean.patientDOB}</td>
	</tr>
	<tr>
		<td>Weight:</td>
		<td>${actionBean.patientWeight}</td>
	</tr>
	<tr>
		<td>Height:</td>
		<td>${actionBean.patientHeight}</td>
	</tr>
	<tr>
		<td>Sex:</td>
		<td>${actionBean.patientSex}</td>
	</tr>
	<tr>
		<td>Emergency Contact Name:</td>
		<td>${actionBean.patientEmergencyContactName}</td>
	</tr>
	<tr>
		<td>Emergency Contact Number:</td>
		<td>${actionBean.patientEmergencyContactNumber}</td>
	</tr>
	<tr>
		<td>Insurance Provider:</td>
		<td>${actionBean.patientInsuranceProvider}</td>
	</tr>
	<tr>
		<td>Insurance ID Number:</td>
		<td>${actionBean.patientInsuranceID}</td>
	</tr>
	<tr>
		<td>Social Security Number:</td>
		<td>${actionBean.patientSSN}</td>
	</tr>
</table>

<p>&nbsp;</p>
<h2>APPOINTMENTS</h2>
<table border="1">
<tr>
	<td><b>DATE</b></td>
	<td><b>DESCRIPTION</b></td>
	<td>&nbsp;</td>
</tr>
<tr>
	<td>03/08/09</td>
	<td>Dental</td>
	<td>VIEW | EDIT | DELETE</td>
</tr>
</table>
<a href="editAppointment.jsp">Edit Appointments</a>

<p>&nbsp;</p>
<h2>ALLERGIES</h2>
<table border="1">
<tr>
	<td><b>DESCRIPTION</b></td>
	<td>&nbsp;</td>
</tr>
<tr>
	<td>Pollen</td>
	<td>VIEW | EDIT | DELETE</td>
</tr>
</table>

<a href="editAllergies.jsp">Edit Allergies</a>

<p>&nbsp;</p>
<h2>MEDICATIONS</h2>
<table border="1">
<tr>
	<td><b>DATE</b></td>
	<td><b>DESCRIPTION</b></td>
	<td>&nbsp;</td>
</tr>
<tr>
	<td>03/08/09</td>
	<td>Alieve</td>
	<td>VIEW | EDIT | DELETE</td>
</tr>
</table>

<a href="editMedications.jsp">Edit Medications</a>

<p>&nbsp;</p>
<h2>DOCTORS</h2>
<table border="1">
<tr>
	<td><b>DATE</b></td>
	<td><b>NAME</b></td>
	<td>SPECIALTY</td>
	<td>&nbsp;</td>
</tr>
<tr>
	<td>03/08/09</td>
	<td>Dr. No</td>
	<td>Pissing off 007</td>
	<td>VIEW | EDIT | DELETE</td>
</tr>
</table>

<a href="editDoctorInfo.jsp"> Edit Doctors </a>



<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> 
  <%@include file="FooterInc.jsp" %>
</div>

</div>



</div>

</body>
</html>
