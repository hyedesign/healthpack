<!-- Jam Packed Inventions -->
<!-- File: addDocToPatient
  -- Date Modified: 04/26/09 
  -- Author: Vahan Kristosturyan 
  -- Description: Adds the doctor to a patient from a drop down box.
  --
  -->
  
 <%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>Change Doctor for Patient</title>
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
<h1>Patient Doctor</h1>
<p>Please Choose a doctor from the drop down list. This will make it 
possible for the doctor to see your patient's data</p>

<jsp:useBean id="docs" class="core.ChangeDocForPatientBean" scope="application" />
<stripes:form beanclass="core.ChangeDocForPatientBean" focus="">
	SELECT A DOCTOR:
	<stripes:select name="docId" size="1">
		<stripes:options-collection collection="${docs.allDoctors}" label="name" value="id"/>
	</stripes:select>
	<stripes:submit name="update" value="Update" />
	<stripes:submit name="cancel" value="Cancel" />
</stripes:form>

</div>
<div id="footer"> 
<%@include file="FooterInc.jsp" %>
  </div>
</div>
</div>
</body>
</html>
