<%-- 
    Document   : aircraftForm
    Created on : Apr 15, 2019, 4:39:35 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Aircraft Form</h1>
        <form:form model-attribute="aircraft" method="POST">
            <label for="aircraftCode">Aircraft Code:</label>
            <input id="aircraftCode" name="aircraftCode" type="text" placeholder="Aircraft Code" value="${aircraft.aircraftCode}"><br>
            <label for="brandName">Station Name:</label>
            <input id="brandName" name="brandName" type="text" placeholder="Brand Name" value="${aircraft.brandName}"><br>
            <label for="numOfSeat">City:</label>
            <input id="numOfSeat" name="numOfSeat" type="text" placeholder="Number of seat" value="${aircraft.numOfSeat}"><br>
            <label for="yearOfManufacture">Country:</label>
            <input id="yearOfManufacture" name="yearOfManufacture" type="text" placeholder="Year of manufacture" value="${aircraft.yearOfManufacture}"><br>
            <input type="submit" value="Add"/>
        </form:form>
    </body>
</html>
