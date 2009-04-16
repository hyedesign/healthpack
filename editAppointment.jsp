<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: editAppointment
  -- Date Modified: 04/14/09 
  -- Author: Han Dong
  -- Description: This file allows the user to view and edit 
  -- patient appointments
  -->
  
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit Appointment Information</title>
<link href="HealthPackStyle.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	 function validateForm() 
	 {
        var errorFree = true;
        if(isEmpty(document.getElementById("date").value)) 
		{
          alert('Please enter a valid appointment date.');
          errorFree = false;
        }
        if(isEmpty(document.getElementById("time").value)) 
		{
          alert('Please enter a valid appointment time.');
          errorFree = false;
        }
        if(isEmpty(document.getElementById("description").value)) 
		{
          alert('Please enter a valid description.');
          errorFree = false;
        }
        if(isEmpty(document.getElementById("remind").value)) 
		{
          alert('Please enter T/F.');
          errorFree = false;
        }
		if(isEmpty(document.getElementById("remind_date").value)) 
		{
          alert('Please enter a day to remind you of your appointment.');
          errorFree = false;
        }
        return errorFree;
      }

      function isEmpty(val) 
	  {
        return val.length == 0;
      }
</script>
</head>

<body>
<div id="container">
<div id="header"></div>
<div id="message">UserName</div>
<div id="navigation">
  <%@include file="LinksInc.jsp" %>
</div>




<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->


<h1>Edit Appointment Information</h1>

<h3> Previous Appointment Info </h3>

<div>Please enter your new appointment information</div>
<form name="edit" method="post" onsubmit="return validateForm()">
	<table border="0">
	<tr>
		<td><label>Date:</label></td>
		<td><input type="text" name="date" size=20 id="date"/></td>
	</tr>
	<tr>
		<td><label>Time:</label></td>
		<td><input type="text" name="time" size=20 id="time"/></td>
	</tr>
	<tr>
		<td><label>Description:</label></td>
		<td><input type="text" name="description" size=40 id="description"/></td>
	</tr>
	<tr>
		<td><label>Remind:</label></td>
		<td><input type="text" name="remind" size=2 id="remind"/></td>
	</tr>
	<tr>
		<td><label>Remind Date:</label></td>
		<td><input type="text" name="remind_date" size=20 id="remind_date"/></td>
	</tr>
	</table>
	<input type="submit" name="Submit" value="Submit" id="submit"/>
	<input type="reset" name="Reset" value="Reset" id="reset"/>
</form>

<p><a href="patientHome.html"> Return </a> </p>
<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> <%@include file="FooterInc.jsp" %>
  </div>

</div>



</div>
</body></html>
