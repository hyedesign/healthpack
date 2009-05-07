<!-- Jam Packed Inventions -->
<!-- HealthPack v1.0
  -- File: logout.jsp
  -- Date Modified: 04/30/09 
  -- Author: Han Domg
  -- Description: This file logs the user out
  -- and returns him to index.
  -- Last edited by: Taylor Evans
  -->

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Log Out</title>
<link href="HealthPackStyle.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="container">
<div id="header"></div>
<div id="message">UserName</div>
<div id="navigation">
</div>

<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->
	<h1>Logging out of your account...</h1>
	<a href="index.jsp">Continue</a>
	<% 
		session.invalidate();
		response.sendRedirect("index.jsp");
	%>

<!-- ********************* STOP HERE !!!! ********************* -->

</div>

<div id="footer"> <%@include file="FooterInc.jsp" %></div>

</div>
</div>
</body></html>
