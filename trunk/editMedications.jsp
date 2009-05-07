<!-- Jam Packed Inventions -->
<!-- HealthPack v1.0
  -- File: editMedications
  -- Date Modified: 04/29/09 
  -- Author: Han Dong
  -- Description: This file allows the user to view and edit
  -- his or her's Medications.
  --
  -- Last Edited by: Jon Conti-Vock
  -->
  
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%> 
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit Medications</title>
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

<h1>Edit Medications</h1>

<div></div>
<stripes:form beanclass="core.AddMedicationBean" focus="">
<stripes:errors/>
<stripes:hidden name="medicationId" value="${actionBean.medicationId}"/>
<table>
		<tr>
			<td>*Medication Name:</td>
			<td><stripes:text name="medicationName" value="${actionBean.medicationName}"/></td>
		</tr>
		<tr>
			<td>*Expiration Date (MM/DD/YYYY):</td>
			<td><stripes:text name="expirationMonth" size="1" value="${actionBean.expirationMonth}"/>/
			    <stripes:text name="expirationDay" size="1" value="${actionBean.expirationDay}"/>/ 
			    <stripes:text name="expirationYear" size="2" value="${actionBean.expirationYear}"/></td>
		</tr>

		<tr>
			<td>*Refill Date (MM/DD/YYYY):</td>
			<td><stripes:text name="refillMonth" size = "1" value="${actionBean.refillMonth}"/>/
			    <stripes:text name="refillDay" size="1" value="${actionBean.refillDay}"/>/ 
			    <stripes:text name="refillYear" size="2" value="${actionBean.refillYear}"/></td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><stripes:textarea name="description" value="${actionBean.description}"/></td>
		</tr>
		<tr>
			<td><stripes:submit name="submit" value="Submit"/>
			    <stripes:reset name ="reset" value="Reset"/></td>
		</tr>
	</table>
</stripes:form>
<p><a href="patientHome.jsp"> Return </a> </p>
<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> <%@include file="FooterInc.jsp" %>
  </div>

</div>



</div>
</body></html>
