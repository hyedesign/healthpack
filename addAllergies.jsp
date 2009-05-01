<!-- Jam Packed Inventions
  -- Healthpack v0.3
  -- File: addAllergies
  -- Date Created: 04/24/09 
  -- Author: Taylor Evans
  -- Description: This file allows the user add 
  -- his or her's allergies
  -- Last Edited by: Taylor Evans    
  -->
  
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%> 
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit Allergies</title>
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


<h1>Add Allergies</h1>

<div></div>
<stripes:form beanclass="core.AllergiesBean" focus="">
<stripes:errors/>
<table>
		<tr>
			<td>Allergy Name:</td>
			<td><stripes:text name="allergyName"/></td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><stripes:textarea name="description" /></td>
		</tr>
		<tr>
			<td><stripes:submit name="submit" value="Submit"/>
		</tr>
	</table>
</stripes:form>

<p><a href="patientList.jsp"> Return to List </a> </p>
<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> <%@include file="FooterInc.jsp" %>
  </div>

</div>

</div>
</body></html>
