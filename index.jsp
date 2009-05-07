<!-- Jam Packed Inventions -->
<!-- HealthPack v1.0
  -- File: index
  -- Date Modified: 04/14/09 
  -- Author: Taylor Evans
  -- Description: This file displays the projects 
  -- main page.
  --
  -- Edited  : 4/17/2009 by Jon Conti-Vock
  -- Changes : added personal information
  --		   fixed some spelling and grammar
  -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<title>Health Pack</title>
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

<h1> HealthPack Home Page </h1>

<h2> About Us </h2>

<h3> Our Company </h3>
<p> Jam Packed Inventions is a up and coming Software Engineering company created in February
of 2009. It was formed when computer scientists Taylor Evans, Jon Conti-Vock, Vahan Kristosuryan,
Alex Bassett, and Han Dong got together with the express desire to explore their mutual
interest in Software Engineering.</p>

<p>
<b><i> Taylor Evans </i></b> - Acting team facilitator for Jam Packed Inventions. Major coding
background is in Java and C++. Basic knowledge of C, Ada 95, and HTML. 
</p>

<p>
<b><i> Jon Conti-Vock </i></b> - Acting requirements lead for Jam Packed Inventions. Major coding
background is in Java and C++. Basic knowledge of JSP, SQL, HTML, C, Assembly, Prolog, Python, and Lisp. 
</p>

<p>
<b><i> Vahan Kristosturyan</i></b> -  Acting design lead for Jam Packed Inventions. Major coding background 
is in Java, SQL, HTML, C, Assembly, VB, and CSS. 
</p>

<p>
<b><i> Alex Bassett </i></b> - Acting implementation and code lead for Jam Packed Inventions. Major coding
background is in Java, SQL, HTML. Basic Knowledge of C and C++.
</p>

<p>
<b><i> Han Dong </i></b> - Acting delivery and documentation lead for Jam Packed Inventions. Major coding
background is in Java, C, Ruby, JavaScript. Basic knowledge of JSP, HTML, AJAX, CGI, PHP, SQL, and Regular Expressions.  
</p>

<h3> Our Product </h3>
<p> Towards the end of February of 2009, Jam Packed Inventions won a contract for its first
big name product from Richard Carback. The contract was to develop a piece of software to act
as a personal medical database to safely store and organize one and one's family's personal
medical information. By March of 2009, JPI had gotten a laid our contract listing the specified
requirements and developed a design for their aptly named HealthPack system.</p>

<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> 
  <%@include file="FooterInc.jsp" %>
</div>

</div>



</div>

</body>
</html>
