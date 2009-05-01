<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: addMedications
  -- Date Modified: 04/24/09 
  -- Author: Jon Conti-Vock
  -- Description: This file allows the user to add a new medication
  -- his or her's Medications.
  -->
  
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%> 
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add Medication</title>
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
<h1>Add Medication</h1>

<div></div>
<stripes:form beanclass="core.AddMedicationBean" focus="">
<stripes:errors/>
<table>
		<tr>
			<td>Medication Name:</td>
			<td><stripes:text name="medicationName"/></td>
		</tr>
		<tr>
			<td>Expiration Date (MM/DD/YYYY):</td>
			<td><stripes:text name="expirationMonth" size="1"/>/
			    <stripes:text name="expirationDay" size="1"/>/ 
			    <stripes:text name="expirationYear" size="2"/></td>
		</tr>

		<tr>
			<td>Refill Date (MM/DD/YYYY):</td>
			<td><stripes:text name="refillMonth" size = "1"/>/
			    <stripes:text name="refillDay" size="1"/>/ 
			    <stripes:text name="refillYear" size="2"/></td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><stripes:textarea name="medicationDescription"/></td>
		</tr>
		<tr>
			<td><stripes:submit name="submit" value="Submit"/>
			<p><a href="patientList.jsp">Return to List</a></p></td>
		</tr>
	</table>
</stripes:form>
<p><a href="patientHome.jsp"> Return </a> </p>
<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> <%@include file="FooterInc.jsp" %>
  </div>

</div>



</div>
</body></html>