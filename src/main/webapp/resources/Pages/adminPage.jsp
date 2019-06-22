<%-- 
    Document   : adminPage
    Created on : Dec 25, 2018, 4:46:08 PM
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
        <h1>Hello World!</h1>
        <p>user: ${user}</p>
        <p>pass: ${password}</p>
    <c:forEach var="r" items="${listRole}">
        <p>role: ${r.getRoleName().toString()}</p>
    </c:forEach>
        <button class="btn btn-sm btn-primary" onclick="location.href='sendemail'">
                                send email</button>
    </body>
</html>
