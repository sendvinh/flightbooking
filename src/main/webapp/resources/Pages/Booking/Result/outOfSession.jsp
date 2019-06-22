<%-- 
    Document   : outOfSession
    Created on : Jun 19, 2019, 2:53:30 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Out of session</title>
        <c:import url="/resources/Pages/Include/libLink.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/CSS/header.css">
    </head>
    <body>
        <div class="container-fluid">
                <c:import url="/resources/Pages/Include/header.jsp"/>
                <div class="notif row">
                    <div id="alert" class="row alert">
                        <div>YOUR SESSION IS EXPIRE!</div>
                    </div>
                </div>
        </div>
    </body>
</html>
