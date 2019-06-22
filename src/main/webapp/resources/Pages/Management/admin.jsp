<%-- 
    Document   : admin
    Created on : Mar 24, 2019, 3:23:54 PM
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
        <h1>Hello World!, this is admin page</h1>
        <a href="<c:url value="/logout"/>">Log out</a><br>
        <button type="button" onclick="window.location.href = '/flightbooking/admin/manageAircraft/'">Manage Aircraft</button><br>  
        <button type="button" onclick="window.location.href = '/flightbooking/admin/manageStation/'">Manage Station</button><br>   
        <button type="button" onclick="window.location.href = '/flightbooking/admin/manageRoute/'">Manage Flight Route</button><br> 
        <button type="button" onclick="window.location.href = '/flightbooking/admin/manageFlight/'">Manage Flight</button><br>  
        <button type="button" onclick="window.location.href = '/flightbooking/admin/managePromotion/'">Manage Promotion</button><br> 
        <button type="button" onclick="window.location.href = '/flightbooking/admin/manageSeat/'">Manage Seat</button><br>  
        <button type="button" onclick="window.location.href = '/flightbooking/admin/manageSeat/'">Manage Seat Type</button><br>
        <button type="button" onclick="window.location.href = '/flightbooking/admin/manageSeat/'">Manage Ticket Type</button><br>
        <button type="button" onclick="window.location.href = '/flightbooking/admin/manageSeat/'">Manage Passenger Type</button><br>
    </body>
</html>
