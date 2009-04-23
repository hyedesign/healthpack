<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: patientHome
  -- Date Modified: 04/14/09 
  -- Author: Taylor Evans
  -- Description: This file displays the specific user's 
  -- patient's homepage.
  -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>Patient Home</title>
<link href="HealthPackStyle.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div id="container">
<div id="header"></div>
<div id="message">UserName</div>
<!----------------------- NAVIGATION  ----------------------->
<div id="navigation">  
  <%@include file="LinksInc.jsp" %>  
</div>

<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->

<h1>Patient Info</h1>
<h2>JOHN SMITH</h2>
<% 
int PATID = Integer.parseInt(request.getParameter("patientID").toString()); 

System.out.println("PATIENT ID : "+ PATID);

%>

<table border="1">
<tr>
	<td colspan=2><b><u>MESSAGE FROM THE DOCTOR:</u></b></td>
</tr>
<tr>
	<td>Doctor's Note</td>
	<td>Take Meds</td>
</tr>
<tr>
</tr>
<tr>
	<td colspan=2><b><u>REMINDERS:</u></b></td>
</tr>
<tr>
	<td>Appointment</td>
	<td>Annual Checkup - 05/05/05</td>
</tr>
<tr>
</tr>
<tr>
	<td colspan=2><b><u>PATIENT INFORMATION</u></b></td>
</tr>
<tr>
	<td>First Name:</td>
	<td>John</td>
</tr>
<tr>
	<td>Last Name:</td>
	<td>Smith</td>
</tr>
<tr>
	<td>Date Of Birth:</td>
	<td>01/01/01</td>
</tr>
<tr>
	<td>Weight:</td>
	<td>150 lbs</td>
</tr>
<tr>
	<td>Height:</td>
	<td>5ft 7in</td>
</tr>
<tr>
	<td>Sex:</td>
	<td>Male</td>
</tr>
<tr>
	<td>Phone Number:</td>
	<td>(123)456-7890</td>
</tr>
<tr>
	<td>Emergency Contact Name:</td>
	<td>Jane</td>
</tr>
<tr>
	<td>Emergency Contact Number:</td>
	<td>9-1-1</td>
</tr>
<tr>
	<td>Insurance Provider:</td>
	<td>AETNA</td>
</tr>
<tr>
	<td>Insurance DN Number:</td>
	<td>21342346G153</td>
</tr>
<tr>
	<td>Social Security Number:</td>
	<td>111-22-3333</td>
</tr>
</table>
<a href="editPatient.jsp">Edit Patient Info</a>
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
