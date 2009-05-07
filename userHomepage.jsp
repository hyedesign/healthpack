<!-- Jam Packed Inventions 							-->
<!-- HealthPack v1.0								-->
<!-- File: userHomepage								-->
<!-- Date Modified: 04/14/09 						-->
<!-- Author: Taylor Evans							-->
<!-- Description: This file displays the user's 	-->
<!-- basic homepage.								-->
<!-- Last Edited By: Taylor Evans					-->

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>User Page</title>
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

<h1>User Information</h1>
<p>Welcome to HealthPack ${sessionScope.username}</p>
<c:choose>
	<c:when test="${sessionScope.userisdoctor}">
		<p>You are logged in with doctor level privileges</p>
	</c:when>
	<c:otherwise>
		<p>You are logged in with user level privileges</p>
	</c:otherwise>
</c:choose>

<stripes:form beanclass="core.GetUserInfoBean" focus="">
<stripes:hidden name="userID" value= "${sessionScope.userid}" /> 
<stripes:submit name="submit" value="Edit User Information" />
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
  