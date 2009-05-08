<!-- Jam Packed Inventions 										-->
<!-- HealthPack v1.0											-->
<!-- File: editDocNote											-->
<!-- Date Modified: 04/27/09 									-->
<!-- Author: Taylor Evans 										-->
<!-- Description: This file allows the a doctor to edit his		-->
<!-- or her note to a patient.									-->
<!-- Edited by: Jon Conti-Vock 									-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit Doctor Note</title>
<link href="HealthPackStyle.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div id="container">
<div id="header"></div>
<div id="message">${sessionScope.username}</div>
<!----------------------- NAVIGATION  ----------------------->
<div id="navigation">  
  <%@include file="LinksInc.jsp" %>
</div>
<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->
<c:if test="${empty sessionScope.userid}">
	<jsp:forward page="login.jsp" />
</c:if>
<c:if test="${!sessionScope.userisdoctor}">
	<jsp:forward page="login.jsp" />
</c:if>

<p>Please enter your note for the patient</p>
<stripes:form beanclass="core.AddDocNoteBean" focus="">
	<stripes:hidden name="patientID" value="${actionBean.patientID}"/>
	<stripes:errors/>
	<table border="0">
		<tr>
			<td>Doctor's Note</td>
			<td><stripes:textarea name="description" value="${actionBean.description}"/></td>
		</tr>
		<tr>
            <td colspan="2">
            	<stripes:submit name="submit" value="Submit"/>
            	<stripes:reset name ="reset" value="Reset"/></td>
        </tr>
	</table>
</stripes:form>
<a href="patientList.jsp"> Return to List </a>
<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> 
  <%@include file="FooterInc.jsp" %>  
</div>
</div>
</div>
</body>
</html>