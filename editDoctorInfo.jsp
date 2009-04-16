<!-- Jam Packed Inventions
  -- Healthpack v0.2
  -- File: editDoctorInfo
  -- Date Modified: 04/14/09 
  -- Author: Han Dong
  -- Description: This file allows the user to edit the 
  -- information about a patient's doctors.
  -->
  
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit Doctors</title>
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

<form name="edit" method="post" >
<table width="473" border="0">
<tr>
<th> Name</th>
<th width="62">  Phone Number  </th>
<th width="111">  Email  </th>
<th width="84">  Comment  </th>
<th width="64"></th>
</tr>
<tr>
<td>Taylor Evans</td>
<td>123-4567</td>
<td>spam@gmail.com</td>
<td>Get a shave</td>
<td><input type="button" value="Delete"/> <input type="button" value="Edit" onclick="this.form.action='EditDoc.jsp';this.form.submit()"/></td>
</tr>
<tr>
<td>Jonathan Conti-Vock</td>
<td>123-4567</td>
<td>spam@gmail.com</td>
<td>Get a shave and a haircut</td>
<td><input type="button" value="Delete"/> <input type="button" value="Edit" onclick="this.form.action='EditDoc.jsp';this.form.submit()"/></td>
</tr>
<tr>
<td>Alex Bassett</td>
<td>123-4567</td>
<td>spam@gmail.com</td>
<td>Use your first name</td>
<td><input type="button" value="Delete"/> <input type="button" value="Edit" onclick="this.form.action='EditDoc.jsp';this.form.submit()"/></td>
</tr>
<tr>
<td>Vahan Kristostruyan</td>
<td>123-4567</td>
<td>spam@gmail.com</td>
<td>Invite us to your wedding</td>
<td><input type="button" value="Delete"/> <input type="button" value="Edit" onclick="this.form.action='EditDoc.jsp';this.form.submit()"/></td>
</tr>
</table> 
</form>


<p><a href="patientHome.html"> Return </a> </p>
<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> <%@include file="FooterInc.jsp" %>
  </div>

</div>



</div>
</body></html>
