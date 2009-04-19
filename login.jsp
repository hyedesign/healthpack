<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: login
  -- Date Modified: 04/14/09 
  -- Author: Alex Bassett
  -- Description: This file allows the user to log into the
  -- system.
  -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
<p>Please enter your login information</p>
<form name="login" method=post action="makeSession.jsp">
	<table border="0">
	<tr>
		<td>Username</td>
		<td><input type="text" name="username" size=20 /></td>
	</tr>
	<tr>
		<td>Password</td>
		<td><input type="password" name="password" size=20 /></td>
	</tr>
	</table>
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
