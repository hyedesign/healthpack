<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: editTest
  -- Date Modified: 04/23/09 
  -- Author: Han Dong
  -- Description: This file allows user to edit tests
  -->
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>Edit Test</title>
<link href="HealthPackStyle.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    input.error { background-color: yellow; }
</style>
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
<h1>Edit Test</h1>
<p>Please enter test information</p>
<stripes:form beanclass="core.EditTestBean" focus="">
	<stripes:errors/>
	<table>
		<tr>
			<td>Patient ID:</td>
			<td><stripes:text name="patientID" size="20" /></td>
		</tr>
		<tr>
			<td>Test Name:</td>
			<td><stripes:text name="testName" size="20" /></td>
		</tr>
		<tr>
			<td>Test Result:</td>
			<td><stripes:text name="testResult" size="20" /></td>
		</tr>
		<tr>
			<td>Test Description:</td>
			<td><stripes:textarea name="testDescription" cols="45" rows="5" /></td>
		</tr>
		<tr>
			<td>Test Date:</td>
			<td>
	    		<stripes:text name="testMonth" size="1" maxlength = "2" />/
				<stripes:text name="testDay" size="1" maxlength = "2" />/
				<stripes:text name="testYear" size="2" maxlength = "4" />
			</td>
		</tr>
		<tr>
			<td><stripes:submit name="submit" value="Submit" />
				<stripes:reset name="reset" value="Reset" /></td>
		</tr>
	</table>
</stripes:form>
	

</div>
<div id="footer"> 
<%@include file="FooterInc.jsp" %>
  </div>

</div>



</div>

</body>
</html>