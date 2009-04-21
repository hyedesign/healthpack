<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: editDoc
  -- Date Modified: 04/19/09 
  -- Author: Han Dong 
  -- Description: This file allows the user to edit a
  -- patients doctor. 
  --
  -- Edited by: Jon Conti-Vock
  -->

<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>  
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit Doctor Information</title>
<link href="HealthPackStyle.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    input.error { background-color: yellow; }
</style>
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


<h1>Edit Doctor Information</h1>
<div></div>
<stripes:form beanclass="core.DocBean" focus="">
	<stripes:errors/>
	<table border="0">
	<tr>
		<td><label>First Name: </label></td>
		<td><stripes:text name="fname" /></td>
	</tr>
	<tr>
		<td><label>Last Name: </label></td>
		<td><stripes:text name="lname" /></td>
	</tr>
	<tr>
		<td><label>Phone Number: </label></td>
		<td><stripes:text name="phone" /></td>
	</tr>
	<tr>
		<td><label>Email: </label></td>
		<td><stripes:text name="email" /></td>
	</tr>
	<tr>
		<td><label>Description:</label></td>
		<td><stripes:text name="description" /></td>
	</tr>
	</table>
	<stripes:submit name="Submit" value="Submit" />
	<stripes:reset name="Reset" value="Reset" />
</stripes:form>
<p><a href="patientHome.jsp"> Return </a> </p>
<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> <%@include file="FooterInc.jsp" %>
  </div>

</div>



</div>
</body></html>