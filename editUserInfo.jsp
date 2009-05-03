<!-- Jam Packed Inventions  -->
<!-- Healthpack v0.2
  -- File: editUserInfo
  -- Date Modified: 04/14/09 
  -- Author: Taylor Evans
  -- Description: This file allows the user to edit its
  -- personal information.
  -- Last edited by: Han Dong
  -->
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>Edit User Info</title>
<link href="HealthPackStyle.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div id="container">
<div id="header"></div>
<div id="message">${sessionScope.username}</div>
<!----------------------- NAVIGATION  ----------------------->
<div id="navigation"> 
   <%@include file="LinksInc.jsp" %>
</div>

<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->
<c:if test="${empty sessionScope.userid}">
	<% response.sendRedirect("login.jsp"); %>
</c:if>

<h1>Edit User Info</h1>
<p>Please enter your information</p>
<p>Fields marked with * are required</p>
<stripes:form beanclass="core.UserInfoBean" focus="">
	<stripes:errors/>
	<table>
		<tr>
			<td>*First Name:</td>
			<td><stripes:text name="firstName" size="20" value="${actionBean.firstName}"/></td>
		</tr>
		<tr>
			<td>*Last Name:</td>
			<td><stripes:text name="lastName" size="20" value="${actionBean.lastName}"/></td>
		</tr>
		<tr>
			<td>*New Email:</td>
			<td><stripes:text name="email" size="20" value="${actionBean.email}"/></td>
		</tr>
		<tr>
			<td>Phone:</td>
			<td><stripes:text name="phone" size="20" value="${actionBean.phone}"/></td>
		</tr>
		<tr>
			<td>*New Password:</td>
			<td><stripes:password name="password" size="20" /></td>
		</tr>
		<tr>
			<td>*Re-enter New PW:</td>
			<td><stripes:password name="password2" size="20" /></td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><stripes:textarea name="description" cols="45" rows="5" value="${actionBean.description}"/></td>
		</tr>
		<tr>
			<td><stripes:submit name="submit" value="Submit" />
				<stripes:reset name="reset" value="Reset" /></td>
		</tr>
	</table>
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
