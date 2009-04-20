<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: register
  -- Date Modified: 04/17/09 
  -- Author: Alex Bassett
  -- Description: This file allows a user to 
  -- register as a doctor or regular user.
  -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>Registration</title>
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

<h1>Register</h1>
<p>Please enter your information</p>
<form name="register" method=post action="verifyRegInfo.jsp">
	<table border="0">
	<tr>
		<td>Username</td>
		<td><input type="text" name="username" size=30 /></td>
	</tr>
	<tr>
		<td>Password</td>
		<td><input type="password" name="password" size=32 /></td>
	</tr>
	<tr>
		<td>Re-enter PW</td>
		<td><input type="password" name="password2" size=32 /></td>
	</tr>
	<tr>
		<td>Firstname</td>
		<td><input type="text" name="firstname" size=30 /></td>
	</tr>
	<tr>
		<td>Lastname</td>
		<td><input type="text" name="lastname" size=30 /></td>
	</tr>
	<tr>
		<td>Email</td>
		<td><input type="text" name="email" size=30 /></td>
	</tr>
	<tr>
		<td>Re Enter Email</td>
		<td><input type="text" name="email2" size=30 /></td>
	</tr>
	<tr>
		<td>Phone</td>
		<td><input type="text" name="phone" size=30 /></td>
	</tr>
	</table>
<input type="checkbox" name="doctor" value="yes" />
I would like to register as a doctor<br />
<p><input type=submit /></p>
</form>
<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> 
  <%@include file="FooterInc.jsp" %>
  </div>

</div>



</div>

</body>
</html>
