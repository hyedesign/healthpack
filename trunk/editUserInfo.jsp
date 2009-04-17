<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: editUserInfo
  -- Date Modified: 04/14/09 
  -- Author: Taylor Evans
  -- Description: This file allows the user to edit its
  -- personal information.
  -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>Edit User Info</title>
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


<h1>Edit User Info</h1>
<p>Please enter your information</p>
<form name="editUserInfo" method="post" action="verifyUserInfo.jsp">
	<table border="0">
	<tr>
		<td>First Name</td>
		<td><input type="text" name="firstName" size=30 /></td>
	</tr>
	<tr>
		<td>Last Name</td>
		<td><input type="text" name="lastName" size=30 /></td>
	</tr>
	<tr>
		<td>New Email</td>
		<td><input type="text" name="email" size=20 /></td>
	</tr>
	<tr>
		<td>Description</td>
		<td><textarea name="textarea" cols="45" rows="5"></textarea></td>
	</tr>
	<tr>
		<td>New Password</td>
		<td><input type="password" name="password" size=20 /></td>
	</tr>
	<tr>
		<td>Re-enter New PW</td>
		<td><input type="password" name="password2" size=20 /></td>
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
