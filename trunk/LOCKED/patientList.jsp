<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: patientList
  -- Date Modified: 04/14/09 
  -- Author: Taylor Evans
  -- Description: This file displays the list of patients 
  -- created by the user.
  -->

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
	
	java.util.ArrayList<Integer> arrayOfPatients = new java.util.ArrayList<Integer>();
	core.PatientBean patient = new core.PatientBean();
	int userId = 0;
	
	try {
	session.setAttribute( "id", 0);
	userId = Integer.parseInt((String)session.getAttribute("id"));
	arrayOfPatients = core.PatientBean.lookupPatientsByUserID(userId);
	patient = new core.PatientBean();
	}catch (Exception e){
		
	}

	
	
	%>
<h1>User Patients</h1>

<table width="400" border="1" summary="This table includes all patients for the specific user">
  <tr>
    <td width="250" align="center">Patient Name</td>
    <td colspan="2"  align="center">Action</td>
  </tr>
  
  <% 
  	for (int i = 0; i < arrayOfPatients.size(); i++){
  		patient = new core.PatientBean(userId);	
  %>
  
  <tr>
  	<%String name = patient.getPatientFirstName() + " " 
  				  + patient.getPatientMiddleName() + " " 
  				  + patient.getPatientLastName();%>
  
    <td align="center"><a href="patientHome.jsp.jsp?value=<%patient.getPatientId();%>"> 
    						<% System.out.println(name);%> </a></td>
    		
    <td width="70" align="center"><a href="editPatient.jsp?value=<%patient.getPatientId();%>"> Edit </a></td>
    <td width="70" align="center"><a href="#">Delete</a></td>
  
  <%}%>
  </tr>
  <tr>
    <td colspan="3" align="center"><a href="editPatient.jsp?value=0">Add</a></td>
  </tr>
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
