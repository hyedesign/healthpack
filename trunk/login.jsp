<!-- Jam Packed Inventions 										-->
<!-- HealthPack v1.0											-->
<!-- File: login												-->
<!-- Date Modified: 04/14/09 									-->
<!-- Author: Alex Bassett										-->
<!-- Description: This file allows the user to log into the		-->
<!-- system.													-->
<!-- Last Edited By: Alex Bassett								-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>Log In</title>
<link href="HealthPackStyle.css" rel="stylesheet" type="text/css" />
</head>

<body>


<div id="container">
<div id="header"></div>
<div id="message"></div>
<!----------------------- NAVIGATION  ----------------------->
<div id="navigation">  
  <%@include file="LinksIncHOME.jsp" %>
  
</div>

<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->


<h1>Login</h1>
<c:if test="${!empty actionBean}">
	<h2>We couldn't find a matching user. Please Try again</h2>
</c:if>
<stripes:form beanclass="core.LoginBean" focus="">
	<table border="0">
		<tr>
			<td>Username</td>
			<td>
				<stripes:text name="userName"/>
				<stripes:errors field="userName"/>
			</td>
		</tr>
		<tr>
			<td>Password</td>
			<td>
				<stripes:password name="userPassword"/>
				<stripes:errors field="userPassword"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<stripes:submit name="submit" value="Submit"/>                    
			</td>
		</tr>
	</table>
</stripes:form>
<p>Need to register? <a href="register.jsp">Click Here!</a></p>


<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> 
  <%@include file="FooterInc.jsp" %>
</div>

</div>



</div>

</body>
</html>
