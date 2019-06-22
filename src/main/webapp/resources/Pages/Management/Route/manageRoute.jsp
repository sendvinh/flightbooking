<%-- 
    Document   : manageRoute
    Created on : Mar 28, 2019, 8:35:01 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Manage Route!</h1>
        <form action="add">
            <input type="submit" value="Add Route"/>
        </form>
        <form action="search">
            <label for="departure">Departure:</label>
            <select id="departure" name="departure">
                <option value="">
                    ---------------
                </option>
                <c:forEach var="station" items="${listStation}">
                    <option value="${station.stationCode}">
                        ${station.stationCode} - ${station.stationName}
                    </option>
                </c:forEach>
            </select><br>
            <label for="arrival">Arrival:</label>
            <select id="arrival" name="arrival">
                <option value="">
                    ---------------
                </option>
                <c:forEach var="station" items="${listStation}">
                    <option value="${station.stationCode}">
                        ${station.stationCode} - ${station.stationName}
                    </option>
                </c:forEach>
            </select><br>
            <input type="submit" value="Search"/>
        </form>
        
        <table>
            <c:if test="${listRoute!=null}">
                <thead>
                    <tr>
                        <th>Departure</th>
                        <th>Arrival</th>
                        <th>Distance</th>
                        <th>Duration</th>
                        <th>Standard Price</th>
                    </tr>
                </thead>
            </c:if>

            <tbody>
                <c:forEach var="r" items="${listRoute}">
                    <tr>
                        <td>${r.departure.stationCode} - ${r.departure.stationName}</td>
                        <td>${r.arrival.stationCode} - ${r.arrival.stationName}</td>
                        <td>${r.distance}</td>
                        <td>${r.duration}</td>
                        <td>${r.standardPrice}</td>
                        <td>
                            <form action="edit/${r.flightRouteId}">
                                <input type="submit" value="Edit"/>
                            </form>    
                        </td>

                        <td>
                            <form action="delete/${r.flightRouteId}" action="GET">
                                <input type="submit" value="Delete"/>
                            </form>
                        </td>
                    </tr>

                </c:forEach>
                <c:if test="${listRoute.isEmpty()}">
                    <tr>
                        <td>No result!</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </body>
</html>
