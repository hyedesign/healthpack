<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@page import="core.User"%>
<%@page import="java.util.regex.Pattern" %>
<%@page import="java.util.regex.Matcher" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Verifying</title>
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

	<%
		User u = new User();
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String pass2 = request.getParameter("password2");
		String ptrStr = "(.*)@(.*)\\.(.*)";
		Pattern p = Pattern.compile(ptrStr);
		Matcher m = p.matcher(email);
		if(m.find() && pass.equals(pass2)) {
	%>
	<h1>Congratulations</h1>
	<h2>Your user information has been updated</h2>
	<a href="userHomepage.jsp">Continue to HomePage</a>
	<% } else { %>
	<h1>Error</h1>
	<p>Please make sure your email is of the following format XX@XX.XX</p>
	<p>Please check to see if both your passwords are correct</p>
	<% } %>

<!-- ********************* STOP HERE !!!! ********************* -->

</div>

<div id="footer"> <%@include file="FooterInc.jsp" %></div>

</div>
</div>
</body></html>
