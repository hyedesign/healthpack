<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: editPatient
  -- Date Modified: 04/14/09 
  -- Author: Taylor Evans
  -- Description: This file allows user to edit its patient
  -- information
  -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>Edit Patient</title>
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
<h2>Patient Name</h2>
<p>Please enter your patient information</p>
<form name="editUserInfo" method=post action="verifyPatientInfo.jsp">
	<table border="0">
	<tr>
		<td>First Name:</td>
		<td><input type="text" name="firstname" size=20 /></td>
	</tr>
	<tr>
		<td>Last Name:</td>
		<td><input type="text" name="lastname" size=20 /></td>
	</tr>
	<tr>
		<td>Date Of Birth:</td>
		<td><input type="text" name="DOB" size=20 /></td>
	</tr>
	<tr>
		<td>Weight:</td>
		<td><input type="text" name="weight" size=20 /></td>
	</tr>
	<tr>
		<td>Height:</td>
		<td><input type="text" name="height" size=20 /></td>
	</tr>
	<tr>
		<td>Sex:</td>
		<td><input type="text" name="sex" size=20 /></td>
	</tr>
	<tr>
		<td>Phone Number:</td>
		<td><input type="text" name="phoneNumber" size=20 /></td>
	</tr>
	<tr>
		<td>Emergency Contact Name:</td>
		<td><input type="text" name="contact" size=20 /></td>
	</tr>
	<tr>
		<td>Emergency Contact Number:</td>
		<td><input type="text" name="contactNum" size=20 /></td>
	</tr>
	<tr>
		<td>Insurance Provider:</td>
		<td><input type="text" name="insurance" size=20 /></td>
	</tr>
	<tr>
		<td>Insurance DN Number:</td>
		<td><input type="text" name="insuranceNum" size=20 /></td>
	</tr>
	<tr>
		<td>Social Security Number:</td>
		<td><input type="text" name="SSN" size=20 /></td>
	</tr>
	</table>
<p><input type=submit /></p>
</form>
<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> 
  <%@include file="FooterInc.jsp" %>
</div>

</div>



</div>

</body>
</html>
