<!-- Jam Packed Inventions -->
<!-- HealthPack v1.0
  -- File: editTest
  -- Date Modified: 04/27/09 
  -- Author: Han Dong
  -- Description: This file allows user to add new tests
  -- Last Edited By: Han Dong
  -->
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>Edit Test</title>
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

<c:if test="${empty sessionScope.userid}">
	<jsp:forward page="login.jsp" />
</c:if>
<c:if test="${sessionScope.userisdoctor}">
	<jsp:forward page="login.jsp" />
</c:if>

<h1>Add Test</h1>
<p>Please enter test information</p>
<stripes:form beanclass="core.AddTestBean" focus="">
	<stripes:errors/>
	<table>
		<tr>
			<td>*Test Name:</td>
			<td><stripes:text name="testName" size="20" /></td>
		</tr>
		<tr>
			<td>*Test Result:</td>
			<td><stripes:text name="testResult" size="20" /></td>
		</tr>
		<tr>
			<td>Test Description:</td>
			<td><stripes:textarea name="testDescription" cols="45" rows="5" /></td>
		</tr>
		<tr>
			<td>*Test Date: (MM/DD/YYYY)</td>
			<td>
	    		<stripes:text name="testMonth" size="1" />/
				<stripes:text name="testDay" size="1" />/
				<stripes:text name="testYear" size="2" />
			</td>
		</tr>
		<tr>
			<td><stripes:submit name="submit" value="Submit" /></td>
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