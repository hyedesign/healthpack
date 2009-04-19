<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: editDocNote
  -- Date Modified: 04/14/09 
  -- Author: Taylor Evans 
  -- Description: This file allows the a doctor to edit his
  -- or her note to a patient.
  -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>Edit Doctor Note</title>
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


<p>Please enter your note for the patient</p>
<form name="editDocNote" method=post action="verifyDocNote.jsp">
<table border="0">
<tr>
	<td>Doctor's Note</td>
	<td><input type="text" name="docnote" size=20 /></td>
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
