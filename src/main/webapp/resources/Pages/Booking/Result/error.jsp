<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        <c:import url="/resources/Pages/Include/libLink.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/CSS/header.css">
    </head>
    <body>
        <div class="container-fluid">
                <div class="notif row">
                    <div id="alert" class="row alert">
                        <div>ERROR DURING BOOKING! PLEASE TRY AGAIN!</div>
                    </div>
                </div>
        </div>
    </body>
</html>