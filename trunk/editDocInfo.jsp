<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: editDocInfo
  -- Date Modified: 04/20/09 
  -- Author: Taylor Evans
  -- Description: This file allows the doctor to edit his/her
  -- personal information.
  --
  -- Edited by Jon Conti-Vock
  -->
  
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>  
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>Edit Doctor Info</title>
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

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->

<h1>Edit Doctor Info</h1>
<p>Please enter your information</p>
<stripes:form beanclass="core.DocInfoBean" focus="">
	<stripes:errors/>
	<table border="0">
	<tr>
		<td>New Email</td>
		<td><stripes:text name="email" /></td>
	</tr>
	<tr>
		<td>New Password</td>
		<td><stripes:text name="password" /></td>
	</tr>
	<tr>
		<td>Re-enter New PW</td>
		<td><stripes:text name="password2" /></td>
	</tr>
	</table>
<p><stripes:submit name="submit" value="Submit" /></p>
</stripes:form>

<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> 
  <%@include file="FooterInc.jsp" %>
</div>

</div>



</div>

</body>
</html>
