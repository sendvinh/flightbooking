<%-- 
    Document   : routeForm
    Created on : Mar 28, 2019, 8:35:22 PM
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
        <form:form model-attribute="flightRoute" method="POST">
            <label for="departure">Departure:</label>
            <select id="departure" name="departure.stationCode">
                <c:forEach var="s" items="${listStation}">
                    <option value="${s.stationCode}"
                            <c:if test="${s.stationCode == route.departure.stationCode}">
                                selected="true"
                            </c:if>
                        >
                        ${s.stationCode} - ${s.stationName}
                    </option>
                </c:forEach>
            </select><br>
            <label for="arrival">Arrival</label>
            <select id="arrival" name="arrival.stationCode">
                <c:forEach var="s" items="${listStation}">
                    <option value="${s.stationCode}"
                            <c:if test="${s.stationCode == route.arrival.stationCode}">
                                selected="true"
                            </c:if>
                        >
                        ${s.stationCode} - ${s.stationName}
                    </option>
                </c:forEach>
            </select><br>
            <label for="distance">Distance:</label>
            <input id="distance" name="distance" type="number" placeholder="distance" value="${route.distance}"><br>
            <label for="standardPrice">Standard Price:</label>
            <input id="standardPrice" name="standardPrice" type="number" placeholder="standared price" value="${route.standardPrice}"><br>
            <label for="duration">Duration:</label>
            <input id="duration" name="duration" type="number" placeholder="duration" value="${route.duration}"><br>
            <input type="submit" value="Add"/>
        </form:form>
    </body>
</html>
