<!-- Jam Packed Inventions
  -- Healthpack v0.6
  -- File: addPatient
  -- Date Modified: 04/28/09 
  -- Author: Alex Bassett
  -- Description: This file allows users to add new patients
  -- 
  -- last edited by Alex
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
<div id="message"></div>
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
<c:if test="${sessionScope.userisdoctor}">
	<% response.sendRedirect("login.jsp"); %>
</c:if>

<h1>New Patient</h1>

<stripes:form beanclass="core.EditPatientBean" focus="">
		<stripes:errors/>
        <table>
        	<stripes:hidden name="userId" value= "${sessionScope.userid}" />     	
           <tr>
                <td>First Name:</td>
                <td><stripes:text name="firstName"/></td>
            </tr>
            <tr>
                <td>Middle Name:</td>
                <td><stripes:text name="middleName"/></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><stripes:text name="lastName"/> </td>
            </tr> 
         	<tr>  
                <td>Date Of Birth:</td>
    			<td>
	    			<stripes:text name="birthMonth" size="1"/>/
				    <stripes:text name="birthDay" size="1"/>/
				    <stripes:text name="birthYear" size="2"/>
				</td>
	        </tr>
            <tr>
                <td>Weight:</td>
                <td><stripes:text name="weight"/></td> 
            </tr>
            <tr>
                <td>Height (in):</td>
                <td><stripes:text name="height"/></td> 
            </tr>
            <tr>
                <td>Sex (m/f):</td>
                <td><stripes:text name="patientSex"/></td> 
            </tr>
            <tr>
                <td>Emergency Contact Name:</td>
                <td><stripes:text name="emergencyContactName"/></td> 
            </tr>
            <tr>
                <td>Emergency Contact Phone Number:</td>
                <td><stripes:text name="emergencyContactPhone"/></td> 
            </tr>
            <tr>
                <td>Insurance Provider Name:</td>
                <td><stripes:text name="insurance"/></td> 
            </tr>
            <tr>
                <td>Insurance ID Number:</td>
                <td><stripes:text name="insuranceID"/></td> 
            </tr>
            <tr>
                <td>Social Security Number:</td>
                <td><stripes:text name="SSN"/></td> 
            </tr>         
            <tr>
                <td colspan="2">
                    <stripes:submit name="submit" value="Submit"/>                    
                </td>
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