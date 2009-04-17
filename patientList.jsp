<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: patientList
  -- Date Modified: 04/14/09 
  -- Author: Taylor Evans
  -- Description: This file displays the list of patients 
  -- created by the user.
  -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>Patient List</title>
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

<h1>User Patients</h1>
<table border="0">
<tr>
	<td><a href="editPatient.html">Add</a></td>
	<td><a href="editPatient.html">Edit</a></td>
	<td><a href="#">Delete</a></td>
</tr>
       </table>
<table border="1">
<tr>
	<td><b>PatientName</b></td>
</tr>
<tr>
	<td><a href="patientHome.html">John Smith</a></td>
</tr>
</table>



<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> 
<p>This Site is brought to you by Jam Packed Inventions Inc.</p>
  </div>

</div>



</div>

</body>
</html>
