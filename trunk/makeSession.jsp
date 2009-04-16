<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Making a Session</title>
<link href="HealthPackStyle.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="container">
<div id="header"></div>
<div id="message"></div>
<div id="navigation">
</div>

<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->

	<%
		User u = new User();
		String username = request.getParameter( "username" );
		String password = request.getParameter( "password" );
		if(!u.lookupUser(username, password))
			response.sendRedirect( "login.html" );
		session.setAttribute( "userName", u.userName );
		response.sendRedirect( "patientList.jsp" );
	%>

	<h1>Verifying your login information</h1>
	<p>Verifying your credentials and setting up your session</p>

<!-- ********************* STOP HERE !!!! ********************* -->

</div>

<div id="footer"> <%@include file="FooterInc.jsp" %></div>

</div>
</div>
</body></html>
