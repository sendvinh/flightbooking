<%-- 
    Document   : manageStation
    Created on : Mar 25, 2019, 9:38:36 PM
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
        <h1>Hello Station</h1>
        <form action="search">
            <input type="text" name="station" placeholder="station code, station name ..." style="width: 20%"/>
            <input type="submit" value="Search"/>
        </form>
        <form action="add">
            <input type="submit" value="Add Station"/>
        </form>
        <table>
            <c:if test="${listStation!=null}">
                <thead>
                    <tr>
                        <th>Station Code</th>
                        <th>Station Name</th>
                        <th>City</th>
                        <th>Country</th>
                    </tr>
                </thead>
            </c:if>

            <tbody>
                <c:forEach var="s" items="${listStation}">
                    <tr>
                        <td>${s.stationCode}</td>
                        <td>${s.stationName}</td>
                        <td>${s.city}</td>
                        <td>${s.country}</td>
                        <td>
                            <form action="edit/${s.stationCode}">
                                <input type="submit" value="Edit"/>
                            </form>    
                        </td>

                        <td>
                            <form action="delete/${s.stationCode}" action="POST">
                                <input type="submit" value="Delete"/>
                            </form>
                        </td>
                    </tr>

                </c:forEach>
                <c:if test="${listStation.isEmpty()}">
                    <tr>
                        <td>No result!</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </body>
</html>
