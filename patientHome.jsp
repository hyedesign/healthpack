<!-- Jam Packed Inventions 									-->
<!-- HealthPack v1.0										-->
<!-- File: patientHome										-->
<!-- Date Modified: 04/30/09 								-->
<!-- Author: Taylor Evans									-->
<!-- Description: This file displays the specific user's 	-->
<!-- patient's homepage.									-->
<!-- Last edited by: Taylor Evans							-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%> 
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>Patient Home</title>
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

<h1>Patient Info</h1>
<h2>${actionBean.patientFirstName} ${actionBean.patientLastName}</h2>

<c:choose>
	<c:when test="${!sessionScope.userisdoctor}">
		<a href="editPatient.jsp">Edit Patient Information</a><br/>
		<a href="addDocToPatient.jsp">Change Patient's Doctor</a><br/>
	</c:when>
	<c:otherwise>
		<stripes:form beanclass="core.EditDocNoteBean">
			<stripes:submit name="submit" value="Edit This Patient's Note"/>
		</stripes:form>
	</c:otherwise>
</c:choose>

<table border="1">
	<tr>
		<td colspan="2"><b><u>REMINDERS:</u></b></td>
	</tr>
	<c:forEach items="${actionBean.appointmentReminders}" var="remindMe" varStatus="loop">
		<c:if test="${remindMe}">
			<tr>
				<td>${actionBean.appointmentDates[ loop.index ]}</td>
				<td>${actionBean.appointmentDescriptions[ loop.index ]}</td>
			</tr>
		</c:if>
	</c:forEach>
	<tr>
		<td colspan="2"><b><u>PATIENT INFORMATION</u></b></td>
	</tr>
	<tr>
		<td>First Name:</td>
		<td>${actionBean.patientFirstName}</td>
	</tr>
	<tr>
		<td>Middle Name:</td>
		<td>${actionBean.patientMiddleName}</td>
	</tr>
	<tr>
		<td>Last Name:</td>
		<td>${actionBean.patientLastName}</td>
	</tr>
	<tr>
		<td>Date Of Birth:</td>
		<td>${actionBean.patientDOB}</td>
	</tr>
	<tr>
		<td>Weight:</td>
		<td>${actionBean.patientWeight}</td>
	</tr>
	<tr>
		<td>Height:</td>
		<td>${actionBean.patientHeight}</td>
	</tr>
	<tr>
		<td>Sex:</td>
		<td>${actionBean.patientSex}</td>
	</tr>
	<tr>
		<td>Emergency Contact Name:</td>
		<td>${actionBean.patientEmergencyContactName}</td>
	</tr>
	<tr>
		<td>Emergency Contact Number:</td>
		<td>${actionBean.patientEmergencyContactNumber}</td>
	</tr>
	<tr>
		<td>Insurance Provider:</td>
		<td>${actionBean.patientInsuranceProvider}</td>
	</tr>
	<tr>
		<td>Insurance ID Number:</td>
		<td>${actionBean.patientInsuranceID}</td>
	</tr>
	<tr>
		<td>Social Security Number:</td>
		<td>${actionBean.patientSSN}</td>
	</tr>
	<tr>
		<td colspan="2"><b><u>DOCTOR'S INFORMATION</u></b></td>
	</tr>
	<tr>
		<td>Name</td>
		<td>${actionBean.doctorName}</td>
	</tr>
	<tr>
		<td>Phone</td>
		<td>${actionBean.doctorPhone}</td>
	</tr>
	<tr>
		<td>Description</td>
		<td>${actionBean.doctorDescription}</td>
	</tr>
		<tr>
		<td colspan="2"><b><u>MESSAGE FROM THE DOCTOR:</u></b></td>
	</tr>
	<tr>
		<td colspan="2">${actionBean.doctorNote}</td>
	</tr>
</table>

<p>&nbsp;</p>
<h2>APPOINTMENTS</h2>
<c:if test="${!sessionScope.userisdoctor}">
	<a href="addAppointment.jsp">Add New Appointment</a>
</c:if>
<table border="1">
	<tr>
		<td><b>Date</b></td>
		<td><b>Description</b></td>
		<td>&nbsp;</td>
	</tr>
	<c:forEach items="${actionBean.appointmentIDs}" var="id1" varStatus="loop1">
		<tr>
			<td>${actionBean.appointmentDates[ loop1.index ]}</td>
			<td>${actionBean.appointmentDescriptions[ loop1.index ]}</td>
			<td>
				<c:if test="${!sessionScope.userisdoctor}">
					<stripes:form beanclass="core.EditAppointmentBean">
						<stripes:hidden name="appointmentID" value="${id1}"/>
						<stripes:submit name="submit" value="Edit"/>
						<stripes:submit name="delete" value="Delete"/>
					</stripes:form>
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table>


<p>&nbsp;</p>
<h2>ALLERGIES</h2>
<c:if test="${!sessionScope.userisdoctor}">
	<a href="addAllergies.jsp">Add New Allergy</a>
</c:if>
<table border="1">
	<tr>
		<td><b>Name</b></td>
		<td><b>Description</b></td>
		<td>&nbsp;</td>
	</tr>
	<c:forEach items="${actionBean.allergyIDs}" var="id2" varStatus="loop2">
		<tr>
			<td>${actionBean.allergyNames[ loop2.index ]}</td>
			<td>${actionBean.allergyDescriptions[ loop2.index ]}</td>
			<td>
				<c:if test="${!sessionScope.userisdoctor}">
					<stripes:form beanclass="core.EditAllergyBean">
						<stripes:hidden name="allergyID" value="${id2}"/>
						<stripes:submit name="submit" value="Edit"/>
						<stripes:submit name="delete" value="Delete"/>
					</stripes:form>
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table>

<p>&nbsp;</p>
<h2>MEDICATIONS</h2>
<c:if test="${!sessionScope.userisdoctor}">
	<a href="addMedication.jsp">Add New Medication</a>
</c:if>
<table border="1">
	<tr>
		<td><b>Name</b></td>
		<td><b>Description</b></td>
		<td><b>Refill Date</b></td>
		<td><b>Expiration Date</b></td>
		<td>&nbsp;</td>
	</tr>
	<c:forEach items="${actionBean.medicationIDs}" var="id3" varStatus="loop3">
		<tr>
			<td>${actionBean.medicationNames[ loop3.index ]}</td>
			<td>${actionBean.medicationDescriptions[ loop3.index ]}</td>
			<td>${actionBean.medicationRefillDates[ loop3.index ]}</td>
			<td>${actionBean.medicationExpirationDates[ loop3.index ]}</td>
			<td>
				<c:if test="${!sessionScope.userisdoctor}">
					<stripes:form beanclass="core.EditMedicationBean">
						<stripes:hidden name="medicationId" value="${id3}"/>
						<stripes:submit name="submit" value="Edit"/>
						<stripes:submit name="delete" value="Delete"/>
					</stripes:form>
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table>

<p>&nbsp;</p>
<h2>TESTS</h2>
<c:if test="${!sessionScope.userisdoctor}">
	<a href="addTest.jsp">Add New Test</a>
</c:if>
<table border="1">
	<tr>
		<td><b>Name</b></td>
		<td><b>Description</b></td>
		<td><b>Results</b></td>
		<td><b>Date</b></td>
		<td>&nbsp;</td>
	</tr>
	<c:forEach items="${actionBean.testIDs}" var="id4" varStatus="loop4">
		<tr>
			<td>${actionBean.testNames[ loop4.index ]}</td>
			<td>${actionBean.testDescriptions[ loop4.index ]}</td>
			<td>${actionBean.testResults[ loop4.index ]}</td>
			<td>${actionBean.testDates[ loop4.index ]}</td>
			<td>
				<c:if test="${!sessionScope.userisdoctor}">
					<stripes:form beanclass="core.GetTestBean">
						<stripes:hidden name="testID" value="${id4}"/>
						<stripes:submit name="submit" value="Edit"/>
						<stripes:submit name="delete" value="Delete"/>
					</stripes:form>
				</c:if>
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
