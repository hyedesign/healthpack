<!-- Jam Packed Inventions 						-->
<!-- HealthPack v1.0							-->
<!-- File: register								-->
<!-- Date Modified: 04/17/09 					-->
<!-- Author: Alex Bassett						-->
<!-- Description: This file allows a user to 	-->
<!-- register as a doctor or regular user.		-->
<!-- Last Edited By: Taylor Evans				-->

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>Registration</title>
<link href="HealthPackStyle.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div id="container">
<div id="header"></div>
<div id="message"></div>
<!----------------------- NAVIGATION  ----------------------->
<div id="navigation">  
  <%@include file="LinksIncHOME.jsp" %>
 
</div>

<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->

<h1>Register</h1>
<p>Please enter your information</p>
<p>Fields marked with * are required</p>
<c:if test="${!empty actionBean}">
	<p>We're sorry, but this username has already been taken</p>
</c:if>
<stripes:form beanclass="core.RegisterBean" focus="">
	<stripes:errors/>
	<table>
		<tr>
			<td>*User name:</td>
			<td><stripes:text name="userName" size="20" /></td>
		</tr>
		<tr>
			<td>*Password:</td>
			<td><stripes:password name="userPassword" size="20" /></td>
		</tr>
		<tr>
			<td>*Re-enter PW:</td>
			<td><stripes:password name="userPassword2" size="20" /></td>
		</tr>
		<tr>
			<td>*Email:</td>
			<td><stripes:text name="userEmail" size="20" /></td>
		</tr>
		<tr>
			<td>*Re Enter Email:</td>
			<td><stripes:text name="userEmail2" size="20" /></td>
		</tr>
		<tr>
			<td>*First name:</td>
			<td><stripes:text name="userFirstName" size="20" /></td>
		</tr>
		<tr>
			<td>*Last name:</td>
			<td><stripes:text name="userLastName" size="20" /></td>
		</tr>
		<tr>
			<td>Phone:</td>
			<td><stripes:text name="userPhone" size="20" /></td>
		</tr>
	</table>
	<stripes:checkbox name="userIsDoctor" value="yes" />
	I would like to register as a doctor<br />
	<p><stripes:submit name="submit" value="Submit"/></p>
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
