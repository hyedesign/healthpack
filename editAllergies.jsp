<!-- Jam Packed Inventions
  -- Healthpack v0.3
  -- File: editAllergies
  -- Date Modified: 04/14/09 
  -- Author: Han Dong 
  -- Description: This file allows the user to view and edit 
  -- his or her's allergies   
  -->
  
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit Allergies</title>
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


<h1>Edit Allergies</h1>

<h3> Current Allergies </h3>
<ul>
    <li>Allergy 1</li>
    <li>Allergy 2</li>
    <li>Allergy 3</li>
</ul>

<div></div>
<form name="edit" method="post" >
	<label for="month">Select an Allergy:</label>
	<script type="text/javascript">
	document.write('<select name="allergies" id="allergies">');
	var allergies = ["Allergy 1", "Allergy 2", "Allergy 3"];
	for(var i = 0; i < allergies.length; i ++)
	{
		document.write('<option value="' + allergies[i] + '" onclick="hello(this.value)">' + allergies[i] + '</option>');
	}
	document.write('</select>');
	</script>
</form>

<p><a href="patientHome.html"> Return </a> </p>
<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> <%@include file="FooterInc.jsp" %>
  </div>

</div>



</div>
</body></html>
