<!-- Jam Packed Inventions
  -- Healthpack v0.2
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
<div id="message">UserName</div>
<div id="navigation">
  <%@include file="LinksInc.jsp" %>
</div>




<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->


<h1>Edit Appointment Information</h1>

<stripes:form beanclass="core.AppointmentBean" focus="">
<stripes:errors/>
<style type="text/css">
    input.error { background-color: yellow; }
</style>
<table>
		<tr>
			<td>Appointment Date</td>
		</tr>
		<tr>
			<td>(MM/DD/YYYY):</td>
			<td><stripes:text name="appointmentMonth" size="1" maxlength = "2"/>/
			    <stripes:text name="appointmentDay" size="1" maxlength = "2"/>/ 
			    <stripes:text name="appointmentYear" size="2" maxlength ="4"/></td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><stripes:textarea name="description"/></td>
		</tr>
		<tr>
			<td colspan="3"><stripes:checkbox name="reminder"/> Would you like to be reminded of this appointment?</td>
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
<!-- Jam Packed Inventions
  -- Healthpack v0.2
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
<div id="message">UserName</div>
<div id="navigation">
  <%@include file="LinksInc.jsp" %>
</div>




<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->


<h1>Edit Appointment Information</h1>

<stripes:form beanclass="core.AppointmentBean" focus="">
<stripes:errors/>
<table>
		<tr>
			<td>Appointment Date</td>
		</tr>
		<tr>
			<td>(MM/DD/YYYY):</td>
			<td><stripes:text name="appointmentMonth" size="1" maxlength = "2"/>/
			    <stripes:text name="appointmentDay" size="1" maxlength = "2"/>/ 
			    <stripes:text name="appointmentYear" size="2" maxlength ="4"/></td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><stripes:textarea name="description"/></td>
		</tr>
		<tr>
			<td colspan="3"><stripes:checkbox name="reminder"/> Would you like to be reminded of this appointment?</td>
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
