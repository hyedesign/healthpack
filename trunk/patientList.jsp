<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: patientList
  -- Date Modified: 04/14/09 
  -- Author: Taylor Evans
  -- Description: This file displays the list of patients 
  -- created by the user.
  -- 
  -- last edited by Vahan
  -->

<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%> 
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>

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
<div id="message">UserName</div>
<!----------------------- NAVIGATION  ----------------------->
<div id="navigation">  
<jsp:include  page="LinksInc.jsp" flush="true"/>
</div>

<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->
<% 
	//TEMP ATTRIBUTES FOR TESTING
	session.setAttribute( "id", 3);
	session.getId();

	java.util.ArrayList<Integer> arrayOfPatients = new java.util.ArrayList<Integer>();
	core.Patient patient = new core.Patient();
	int userId = 0;
	
	
	try {
		userId = 3;//session.getAttribute("id");
		arrayOfPatients = core.Patient.lookupPatientsByUserID(userId);
		System.out.println("after SQL call");
	}catch (Exception e){
		System.out.println("\n ******* ERROR IN PATIENTLIST.JSP ******* \n");  
	}
	%>

<h1>User Patients</h1>
<p></p>
  <%   
  	for (int i = 0; i < arrayOfPatients.size(); i++){
  		patient = new core.Patient(arrayOfPatients.get(i));	
  		String PATID = new Integer(patient.getPatientId()).toString();
  		
  		String PATNAME = patient.PatientName();
  		
  		System.out.println("Patient: " + PATNAME + "  ID: " + PATID); 

  %>
	<form method="post" class="tableAll">
	  <div class="tableValue"><input type="hidden" name="patientID" value="<%=PATID%>" /><%=PATNAME%></div>
      <div class="tableButtons">
	  <input type="submit" name="View" id="View" value="View" onclick="this.form.action='patientHome.jsp'"/>
	  <input type="submit" name="Edit" id="Edit" value="Edit" onclick="this.form.action='editPatient.jsp'"/></div>
	</form>
<%}%>
  	    
  	    <form method="post">
  	    	<input type="hidden" name="patientID" value="0" />
  	    	<input type="submit" name="Add" id="Add" value="Add New Patient" onclick="this.form.action='editPatient.jsp'"/>
		</form>
<!-- ********************* STOP HERE !!!! ********************* -->

</div> 
<div id="footer"> 
  <%@include file="FooterInc.jsp" %>
</div>

</div>



</div>

</body>
</html>
