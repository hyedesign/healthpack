<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: editMedications
  -- Date Modified: 04/19/09 
  -- Author: Han Dong
  -- Description: This file allows the user to view and edit
  -- his or her's Medications.
  -->
  
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%> 
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

<div></div>
<stripes:form beanclass="core.MedicationBean" focus="">
<stripes:errors/>
<style type="text/css">
    input.error { background-color: yellow; }
</style>
<table>
		<tr>
			<td>Medication Name:</td>
			<td><stripes:text name="medicationName"/></td>
		</tr>
		<tr>
			<td>Expiration Date:</td>
			<td><stripes:text name="expirationMonth" size="1" maxlength = "2"/>/
			    <stripes:text name="expirationDay" size="1" maxlength = "2"/>/ 
			    <stripes:text name="expirationYear" size="2" maxlength ="4"/></td>
		</tr>

		<tr>
			<td>Refill Date:</td>
			<td><stripes:text name="refillMonth" size = "1" maxlength="2"/>/
			    <stripes:text name="refillDay" size="1" maxlength="2"/>/ 
			    <stripes:text name="refillYear" size="2" maxlength="4"/></td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><stripes:textarea name="description"/></td>
		</tr>
		<tr>
			<td><stripes:submit name="update" value="Update"/>
			    <stripes:reset name ="reset" value="Reset"/></td>
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
