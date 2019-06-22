<%-- 
    Document   : stationForm
    Created on : Mar 27, 2019, 8:22:46 PM
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
        <h1>Station Form</h1>
        <form:form model-attribute="station" method="POST">
            <label for="stationCode">Station Code:</label>
            <input id="stationCode" name="stationCode" type="text" placeholder="Station Code" value="${station.stationCode}"><br>
            <label for="stationName">Station Name:</label>
            <input id="stationName" name="stationName" type="text" placeholder="Station Name" value="${station.stationName}"><br>
            <label for="city">City:</label>
            <input id="city" name="city" type="text" placeholder="City" value="${station.city}"><br>
            <label for="country">Country:</label>
            <input id="country" name="country" type="text" placeholder="Country" value="${station.country}"><br>
            <input type="submit" value="Add"/>
        </form:form>
    </body>
</html>
