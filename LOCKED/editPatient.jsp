<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit Patient</title>
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
  <%@include file="LinksInc.jsp" %>
</div>

<div id="content">
<div id="text">

<!-- ************ THIS IS YOUR AREA.... GO CRAZY HERE ************ -->



<h1>Patient Info</h1>
<p>Please enter your patient information</p>

<stripes:form beanclass="core.PatientBean" focus="">
		<stripes:errors/>
        <table>
            <tr>
                <td>First Name:</td>
                <td><stripes:text name="firstName" maxlength = "30"/></td>
            </tr>
            <tr>
                <td>Middle Name:</td>
                <td><stripes:text name="middleName" maxlength = "30"/></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><stripes:text name="lastName" maxlength = "30"/></td>
            </tr>
            <tr>
                <td>Date Of Birth:</td>
                <td><stripes:text name="month" size="3" maxlength = "2"/>/
			    <stripes:text name="day" size="3" maxlength = "2"/>/ 
			    <stripes:text name="year" size="5" maxlength ="4"/></td>
            </tr>
            <tr>
                <td>Weight:</td>
                <td><stripes:text name="weight" maxlength = "3"/></td>
            </tr>
            <tr>
                <td>Height:</td>
                <td><stripes:text name="height" maxlength = "10"/></td>
            </tr>
            <tr>
                <td>Sex (m/f):</td>
                <td><stripes:text name="sex" maxlength = "1"/></td>
            </tr>
            <tr>
                <td>Emergency Contact Name:</td>
                <td><stripes:text name="emergencyContactName" maxlength = "30"/></td>
            </tr>
            <tr>
                <td>Emergency Contact Phone:</td>
                <td><stripes:text name="emergencyContactPhone" maxlength = "10"/></td>
            </tr>
            <tr>
                <td>Insurance Provider Name:</td>
                <td><stripes:text name="insurance" maxlength = "30"/></td>
            </tr>
            <tr>
                <td>Insurance Number:</td>
                <td><stripes:text name="insuranceID" maxlength = "30"/></td>
            </tr>
            <tr>
                <td>Social Security Number:</td>
                <td><stripes:text name="SSN" maxlength = "9"/></td>
            </tr>
                             
            <tr>
                <td colspan="2">
                    <stripes:submit name="submit" value="submit"/>                    
                </td>
            </tr>
        </table>
    </stripes:form>

<!-- ********************* STOP HERE !!!! ********************* -->

</div>
<div id="footer"> 
  <%@include file="FooterInc.jsp" %>
</div>

</div>



</div>

</body>
</html>
