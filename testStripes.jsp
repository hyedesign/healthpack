<!-- A test of Stripes -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Doctor Information</title>
<link href="HealthPackStyle.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    input.error { background-color: red; }
</style>
</head>

<body>

<div id="container">
<div id="header"></div>
<div id="message">UserName</div>
<!----------------------- NAVIGATION  ----------------------->
<div id="navigation">  
  
   <ul>
    <li><a href="userHomepage.html">User Information</a></li>
    <li><a href="patientList.jsp">Patients</a></li>
    <li><a href="export.jsp">Export</a></li>
    <li><a href="logout.jsp">Log Out</a></li>
  </ul>
 
</div>

<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->

<h1>Form</h1>
<!-- TESTING !!!!!! -->
<stripes:form beanclass="core.CalculatorActionBean" focus="">
		<stripes:errors/>
        <table>
            <tr>
                <td>Number 1:</td>
                <td><stripes:text name="numberOne"/></td>
            </tr>
            <tr>
                <td>Number 2:</td>
                <td><stripes:text name="numberTwo"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <stripes:submit name="addition" value="Add"/>                    
                </td>
            </tr>
            <tr>
                <td>Result:</td>
                <td>${actionBean.result}</td>
            </tr>
        </table>
    </stripes:form>

<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> 
<p>This Site is brought to you by Jam Packed Inventions Inc.</p>
  </div>

</div>



</div>

</body>
</html>
