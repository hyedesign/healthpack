<!-- Jam Packed Inventions 								-->
<!-- HealthPack v1.0									-->
<!-- File: editTest										-->
<!-- Date Modified: 04/23/09 							-->
<!-- Author: Han Dong									-->
<!-- Description: This file allows user to edit tests	-->
<!-- Last Edited By: Taylor Evans						-->

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

<h1>Edit Test</h1>
<p>Please enter test information</p>
<stripes:form beanclass="core.EditTestBean" focus="">
	<stripes:errors/>
	<table>
	<stripes:hidden name="testID" value="${actionBean.testID }"/>
		<tr>
			<td>*Test Name:</td>
			<td><stripes:text name="testName" size="20" value="${actionBean.testName}"/></td>
		</tr>
		<tr>
			<td>*Test Result:</td>
			<td><stripes:text name="testResult" size="20" value="${actionBean.testResult}"/></td>
		</tr>
		<tr>
			<td>Test Description:</td>
			<td><stripes:textarea name="testDescription" cols="45" rows="5" value="${actionBean.testDescription}"/></td>
		</tr>
		<tr>
			<td>*Test Date(mm/dd/yyyy):</td>
			<td>
	    		<stripes:text name="testMonth" size="1" value="${actionBean.testMonth}"/>/
				<stripes:text name="testDay" size="1" value="${actionBean.testDay}"/>/
				<stripes:text name="testYear" size="2" value="${actionBean.testYear}"/>
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