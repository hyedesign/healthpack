<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: Test.jsp
  -- Date Modified: 04/30/09 
  -- Author: Han Dong
  -- Description: Driver for testing Test functionality 
  -->
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>User Page</title>
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
<h1>Test Manager</h1>

<stripes:form action="addTest.jsp" focus="">
	<stripes:submit name="submit" value="Add" />
</stripes:form> 

<stripes:form beanclass="core.ViewTestBean" focus="">
	<input type="hidden" name="testID" value="<%=2%>" />
	<stripes:submit name="submit" value="View" />
</stripes:form> 

<stripes:form beanclass="core.GetTestBean" focus="">
	<input type="hidden" name="testID" value="<%=2%>" />
	<stripes:submit name="submit" value="Edit" />
</stripes:form> 

<stripes:form beanclass="core.DeleteTestBean" focus="">
	<input type="hidden" name="testID" value="<%=2%>" />
	<stripes:submit name="submit" value="Delete" />
</stripes:form>
	
<!-- ********************* STOP HERE !!!! *****	*************** -->

</div>
<div id="footer"> 
  <%@include file="FooterInc.jsp" %>
</div>

</div>



</div>

</body>
</html>
  