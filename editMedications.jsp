<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: editMedications
  -- Date Modified: 04/14/09 
  -- Author: Han Dong
  -- Description: This file allows the user to view and edit
  -- his or her's Medications.
  -->
  
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit Medications</title>
<link href="HealthPackStyle.css" rel="stylesheet" type="text/css" />
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
<h1>Edit Medications</h1>

<h3> Current Medications </h3>
<ul>
    <li>Medication 1</li>
    <li>Medication 2</li>
    <li>Medication 3</li>
</ul>

<div></div>
<form name="edit" method="post" >
	<label>Select a Medication to Edit: </label>
	<script type="text/javascript">
	document.write('<select name="med" id="med">');
	var med = ["med_1", "med_2", "med_3"];
	for(var i = 0; i < med.length; i ++)
	{
		document.write('<option value="' + med[i] + '" onclick="hello(this.value)" id="'+med[i]+'">' + med[i] + '</option>');
	}
	document.write('</select>');
	</script>
</form>

<p><a href="patientHome.html"> Return </a> </p>
<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> <%@include file="FooterInc.jsp" %>
  </div>

</div>



</div>
</body></html>
