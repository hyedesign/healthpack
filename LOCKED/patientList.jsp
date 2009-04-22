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
  <%@include file="LinksInc.jsp" %>
</div>

<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->
<% 
	//TEMP ATTRIBUTES FOR TESTING
	session.setAttribute( "id", 3);


	java.util.ArrayList<Integer> arrayOfPatients = new java.util.ArrayList<Integer>();
	core.Patient patient = new core.Patient();
	int userId = 0;
	
	
	try {
		System.out.println("before userId is assigned");
		userId = 3;//session.getAttribute("id");
		System.out.println("after userId is assigned");
		arrayOfPatients = core.Patient.lookupPatientsByUserID(userId);
		System.out.println("after SQL call");
	}catch (Exception e){
		System.out.println("\n ******* ERROR IN PATIENTLIST.JSP ******* \n");  
	}

	
	
	%>
<h1>User Patients</h1>

  <%   
  	for (int i = 0; i < arrayOfPatients.size(); i++){
  		patient = new core.Patient(arrayOfPatients.get(i));	
  		String PATID = new Integer(patient.getPatientId()).toString();
  		
  		String PATNAME = patient.PatientName();
  %>
  <table width="431" border="1">
		<stripes:form beanclass="core.PatientListBean" focus="">
		    <tr>
		      <td width="270"><%=PATNAME%>  <stripes:hidden name="patientId" value= "<%=PATID%>" /> </td>
		      <td width="44"><stripes:submit name="view" value="View"/></td>
		      <td width="37"><stripes:submit name="edit" value="Edit"/> </td>
		      <td width="52"><stripes:submit name="delete" value="Delete"/> </td>
		    </tr>
		</stripes:form>  

 	<%}%>

  
		<stripes:form beanclass="core.PatientListBean" focus="">
		    <tr>
		     <td colspan="4" align="center"><stripes:submit name="add" value="Add New Patient"/></td>
		    </tr>
		</stripes:form>
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
