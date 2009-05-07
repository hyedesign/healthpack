<!-- Jam Packed Inventions -->
<!-- HealthPack v1.0
  -- File: editAppointment
  -- Date Modified: 04/19/09 
  -- Author: Han Dong
  -- Description: This file allows the user to view and edit 
  -- patient appointments
  --
  -- Last Edited by: Taylor Evans
  -->

<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>   
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit Appointment Information</title>
<link href="HealthPackStyle.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="container">
<div id="header"></div>
<div id="message">${sessionScope.username}</div>
<div id="navigation">
  <%@include file="LinksInc.jsp" %>
</div>




<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->
<c:if test="${empty sessionScope.userid}">
	<jsp:forward page="login.jsp" />
</c:if>
<c:if test="${sessionScope.userisdoctor}">
	<jsp:forward page="login.jsp" />
</c:if>

<h1>Edit Appointment Information</h1>

<stripes:form beanclass="core.AppointmentBean" focus="">
<stripes:errors/>
<table>
	<stripes:hidden name="appointmentID" value="${actionBean.appointmentID }"/>
		<tr>
			<td>*Appointment Date</td>
		</tr>
		<tr>
			<td>(MM/DD/YYYY):</td>
			<td><stripes:text name="appointmentMonth" size="1" value="${actionBean.appointmentMonth}"/>/
			    <stripes:text name="appointmentDay" size="1" value="${actionBean.appointmentDay}"/>/ 
			    <stripes:text name="appointmentYear" size="2" value="${actionBean.appointmentYear}"/></td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><stripes:textarea name="description" value="${actionBean.description}"/></td>
		</tr>
		<tr>
			<td colspan="3"><stripes:checkbox name="reminder" value="true" checked="${actionBean.reminder }"/> Would you like to be reminded of this appointment?</td>
		</tr>
		<tr>
			<td><stripes:submit name="submit" value="Submit"/>
			    <stripes:reset name ="reset" value="Reset"/></td>
		</tr>
	</table>
</stripes:form>
<p><a href="patientList.jsp"> Return to List </a> </p>
<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> <%@include file="FooterInc.jsp" %>
  </div>

</div>



</div>
</body></html>
