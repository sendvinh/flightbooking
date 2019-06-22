<%-- 
    Document   : manageFlight
    Created on : Apr 15, 2019, 4:43:53 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Manage Flight</h1>
        <form action="add">
            <input type="submit" value="Add Flight"/>
        </form>
        <form action="addListFlight">
            <input type="submit" value="Add List Flight"/>
        </form>
        <form action="search" method="GET">
            <label>Departure</label>
            <select name="departure">
                <c:forEach items="${listStation}" var="station">
                    <option value="${station.stationCode}">(${station.stationCode}) ${station.stationName}</option>
                </c:forEach>
            </select><br>

            <label>Arrival</label>
            <select name="arrival">
                <c:forEach items="${listStation}" var="station">
                    <option value="${station.stationCode}">(${station.stationCode}) ${station.stationName}</option>
                </c:forEach>
            </select><br>

            <label>Date</label>
            <input name="date" id="date" type="date" placeholder="date" value=""><br>

            <input type="submit" value="Search">
        </form>
        
        <c:if test="${listFlight != null}" >
        <table>
                <tr>
                    <th>Date</th>
                    <th>Departure</th>
                    <th>Arrival</th>
                    <th>Flight</th>
                        <c:forEach items="${listSeatType}" var="seatType">
                        <th>Available ${seatType.type} seat</th>
                        </c:forEach>
                </tr>

                <c:forEach items="${listFlight}" var="flight">
                    <tr>            
                        <td>
                            ${flight.getDepartDate()}<br>
                            ${flight.getDepartDay()}
                        </td>
                        <td>
                            ${flight.getDepartureTime()} ${flight.flightRoute.departure.stationCode}<br>
                            ${flight.flightRoute.departure.stationName}
                        </td>
                        <td>
                            ${flight.getArrivalTime()} ${flight.flightRoute.arrival.stationCode}<br>
                            ${flight.flightRoute.arrival.stationName}
                        </td>
                        <td>
                            ${flight.flightCode}<br>
                            ${flight.flightRoute.duration}
                        </td>
                        <c:forEach items="${listSeatType}" var="seatType">
                            <td>
                            ${seatDb.getNumberOfSeatOfFlight(flight.flightId, seatType.type,'AVAILABLE')}      
                            </td>
                        </c:forEach>
                        <td>
                            <form action="edit/${flight.flightId}">
                                <input type="submit" value="Edit"/>
                            </form>    
                        </td>

                        <td>
                            <form action="delete/${flight.flightId}" action="GET">
                                <input type="submit" value="Delete"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${listFlight.isEmpty()}" >
        <p>No flight found!</p>
        </c:if>
    </body>
</html>
