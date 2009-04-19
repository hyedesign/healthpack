<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: editDoc
  -- Date Modified: 04/14/09 
  -- Author: Han Dong 
  -- Description: This file allows the user to edit a
  -- patients doctor. 
  -->
  
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit Doctor Information</title>
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


<h1>Edit Doctor Information</h1>

<div></div>
<form name="edit" method="post" >
	<table border="0">
	<tr>
		<td><label>First Name: </label></td>
		<td><input type="text" name="fname" size=20 id="fname"/></td>
	</tr>
	<tr>
		<td><label>Last Name: </label></td>
		<td><input type="text" name="lname" size=20 id="lname"/></td>
	</tr>
	<tr>
		<td><label>Phone Number: </label></td>
		<td><input type="text" name="phone" size=20 id="phone"/></td>
	</tr>
	<tr>
		<td><label>Email: </label></td>
		<td><input type="text" name="email" size=20 id="email"/></td>
	</tr>
	<tr>
		<td><label>Description:</label></td>
		<td><input type="text" name="description" size=40 id="description"/></td>
	</tr>
	</table>
	<input type="submit" name="Submit" value="Submit" id="submit"/>
	<input type="reset" name="Reset" value="Reset" id="reset" onclick="document.getElementById('edit').reset();"/>
</form>
<p><a href="patientHome.html"> Return </a> </p>
<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> <%@include file="FooterInc.jsp" %>
  </div>

</div>



</div>
</body></html>
