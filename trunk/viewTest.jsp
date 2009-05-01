<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: vewTest
  -- Date Modified: 04/30/09 
  -- Author: Han Dong
  -- Description: This file allows user to view a test
  -->
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>View Test</title>
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
<h1>View Test</h1>
<stripes:form action="">
	<stripes:errors/>
	<table>
		<tr>
			<td>Test Name:</td>
			<td><p>${actionBean.testName}</p></td>
		</tr>
		<tr>
			<td>Test Result:</td>
			<td><p>${actionBean.testResult}</p></td>
		</tr>
		<tr>
			<td>Test Description:</td>
			<td><p>${actionBean.testDescription}</p></td>
		</tr>
		<tr>
			<td>Test Date:</td>
			<td>
	    		<p>${actionBean.testMonth}/${actionBean.testDay}/${actionBean.testYear}</p>
			</td>
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