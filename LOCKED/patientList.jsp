<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: patientList
  -- Date Modified: 04/28/09 
  -- Author: Taylor Evans
  -- Description: This file displays the list of patients 
  -- created by the user.
  -- 
  -- last edited by Alex
  -->

<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%> 
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>Patient List</title>
<link href="HealthPackStyle.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div id="container">
<div id="header"></div>
<div id="message">${sessionScope.username}</div>
<!----------------------- NAVIGATION  ----------------------->
<div id="navigation">  
<jsp:include  page="LinksInc.jsp" flush="true"/>
</div>

<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->
<c:if test="${empty sessionScope.userid}">
	<% response.sendRedirect("login.jsp"); %>
</c:if>

<jsp:useBean id="patientList" scope="page" class="core.PatientListBean"/>
<jsp:setProperty name="patientList" property="userId" value="${sessionScope.userid}"/>
<c:set var="names" value="${patientList.patientNames}" scope="page"/>
<c:set var="Ids" value="${patientList.patientIds}" scope="page"/>

<h1>Patient List</h1>

<c:if test="${!sessionScope.userisdoctor}">
	<a href="addPatient.jsp">Add New Patient</a>
</c:if>

<table border="1">
	<c:forEach items="${names}" var="patientName" varStatus="loop">
		<tr>
			<td>${patientName}</td>
			<td>
				<stripes:form beanclass="core.PatientSelectBean">
					<stripes:hidden name="patientId" value="${Ids[loop.index]}"/>
					<c:choose>
						<c:when test="${!sessionScope.userisdoctor}">
							<stripes:submit name="edit" value="Edit"/>
							<stripes:submit name="delete" value="Delete"/>
						</c:when>
						<c:otherwise>
							<stripes:submit name="edit" value="View"/>
						</c:otherwise>
					</c:choose>
				</stripes:form >
			</td>
		</tr>
	</c:forEach>
</table>

<!-- ********************* STOP HERE !!!! ********************* -->

</div> 
<div id="footer"> 
  <%@include file="FooterInc.jsp" %>
</div>
</div>
</div>

</body>
</html>