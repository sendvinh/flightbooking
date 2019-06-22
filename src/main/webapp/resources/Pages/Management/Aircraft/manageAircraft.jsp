<%-- 
    Document   : manageAircraft
    Created on : Apr 15, 2019, 4:39:20 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aircraft</title>
    </head>
    <body>
        <h1>Manage Aircraft</h1>
        <form action="search">
            <input type="text" name="aircraft" placeholder="aircraft code, brand name ..." style="width: 20%"/>
            <input type="submit" value="Search"/>
        </form>
        <form action="add">
            <input type="submit" value="Add Aircraft"/>
        </form>
        <table>
            <c:if test="${listAircraft!=null}">
                <thead>
                    <tr>
                        <th>Aircraft Code</th>
                        <th>Brand Name</th>
                        <th>Number of seat</th>
                        <th>Year of manufacture</th>
                        <th>Picture</th>
                    </tr>
                </thead>
            </c:if>

            <tbody>
                <c:forEach var="craft" items="${listAircraft}">
                    <tr>
                        <td>${craft.aircraftCode}</td>
                        <td>${craft.brandName}</td>
                        <td>${craft.numOfSeat}</td>
                        <td>${craft.yearOfManufacture}</td>
                        <td>${craft.picture}</td>
                        <td>
                            <form action="edit/${craft.aircraftCode}">
                                <input type="submit" value="Edit"/>
                            </form>    
                        </td>

                        <td>
                            <form action="delete/${craft.aircraftCode}" action="GET">
                                <input type="submit" value="Delete"/>
                            </form>
                        </td>
                    </tr>

                </c:forEach>
                <c:if test="${listAircraft.isEmpty()}">
                    <tr>
                        <td>No result!</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </body>
</html>
