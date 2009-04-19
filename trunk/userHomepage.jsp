<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: userHomepage
  -- Date Modified: 04/14/09 
  -- Author: Taylor Evans
  -- Description: This file displays the user's 
  -- basic homepage.
  -->

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


<h1>User Information</h1>

	<table border="0">
	<tr>
		<td>Last Login:</td>
		<td>##/##/## at ##:##:##</td>
	</tr>
	<tr>
		<td>Email:</td>
		<td>User@HealthPack.net</td>
	</tr>
	</table>
<p><a href="editUserInfo.jsp">Edit User Info</a></p>
<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> 
  <%@include file="FooterInc.jsp" %>
</div>

</div>



</div>

</body>
</html>
  