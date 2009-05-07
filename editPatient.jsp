<!-- Jam Packed Inventions -->
<!-- Healthpack v1.0
  -- File: editPatient
  -- Date Modified: 04/30/09 
  -- Author: Alex Bassett
  -- Description: This file allows users to edit existing patients
  -- Last Edited By: Taylor Evans
  -->

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>Add Patient</title>
<link href="HealthPackStyle.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div id="container">
<div id="header"></div>
<div id="message">${sessionScope.username}</div>
<!----------------------- NAVIGATION  ----------------------->
<div id="navigation">  
  <jsp:include  page="LinksInc.jsp" flush="false"/>  
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
<c:if test="${empty sessionScope.patientid}">
	<jsp:forward page="patientList.jsp" />
</c:if>

<jsp:useBean id="patientInfo" scope="page" class="core.PatientInfoBean"/>
<jsp:setProperty name="patientInfo" property="patientId" value="${sessionScope.patientid}"/>

<h1>New Patient</h1>

<c:choose>
	<c:when test="${patientInfo.loaded}">
		<p>Fields marked with * are required</p>
		<stripes:form beanclass="core.EditPatientBean" focus="">
			<stripes:errors/>
	        <table>
	        	<stripes:hidden name="patientId" value= "${sessionScope.patientid}" /> 
	            <tr>
	                <td>*First Name:</td>
	                <td><stripes:text name="firstName" value="${patientInfo.firstName}"/></td>
	            </tr>
	            <tr>
	                <td>Middle Name:</td>
	                <td><stripes:text name="middleName"  value="${patientInfo.middleName}"/></td>
	            </tr>
	            <tr>
	                <td>*Last Name:</td>
	                <td><stripes:text name="lastName"  value="${patientInfo.lastName}"/> </td>
	            </tr> 
	         	<tr>  
	                <td>*Date Of Birth MM/DD/YYYY:</td>
	    			<td>
		    			<stripes:text name="birthMonth" size="1" value="${patientInfo.month}"/>
					    <stripes:text name="birthDay" size="1" value="${patientInfo.day}"/>
					    <stripes:text name="birthYear" size="2"  value="${patientInfo.year}"/>
					</td>
		        </tr>
	            <tr>
	                <td>*Weight (lbs):</td>
	                <td><stripes:text name="weight" value="${patientInfo.weight}"/></td> 
	            </tr>
	            <tr>
	                <td>*Height (in):</td>
	                <td><stripes:text name="height" value="${patientInfo.height}"/></td> 
	            </tr>
	            <tr>
	                <td>*Sex (m/f):</td>
	                <td><stripes:text name="patientSex" value="${patientInfo.sex}"/></td> 
	            </tr>
	            <tr>
	                <td>Emergency Contact Name:</td>
	                <td><stripes:text name="emergencyContactName" value="${patientInfo.ECName}"/></td> 
	            </tr>
	            <tr>
	                <td>Emergency Contact Phone Number:</td>
	                <td><stripes:text name="emergencyContactPhone" value="${patientInfo.ECPhone}"/></td> 
	            </tr>
	            <tr>
	                <td>Insurance Provider Name:</td>
	                <td><stripes:text name="insurance" value="${patientInfo.insurance}"/></td> 
	            </tr>
	            <tr>
	                <td>Insurance ID Number:</td>
	                <td><stripes:text name="insuranceID" value="${patientInfo.insuranceID}"/></td> 
	            </tr>
	            <tr>
	                <td>Social Security Number:</td>
	                <td><stripes:text name="SSN" value="${patientInfo.SSN}"/></td> 
	            </tr>         
	            <tr>
	                <td colspan="2">
	                    <stripes:submit name="submit" value="Submit"/>
	                    <stripes:reset name="reset" value="Reset" />                  
	                </td>
	            </tr>         
	        </table>
		</stripes:form>
	</c:when>
	<c:otherwise>
		<p>We couldn't access the patient data at this time</p>
		<a href="patientList.jsp">Return to Patient List</a>
	</c:otherwise>
</c:choose>
		

<!-- ********************* STOP HERE !!!! ********************* -->

</div> 
<div id="footer"> 
  <%@include file="FooterInc.jsp" %>
</div>
</div>
</div>

</body>
</html>