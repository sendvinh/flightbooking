<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Green Airline</title>
        <c:import url="/resources/Pages/Include/libLink.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/CSS/home.css">
    </head>
    <body>
        <div class="container-fluid">
        <c:import url="/resources/Pages/Include/header.jsp"/>    
        <c:import url="banner.jsp"/>      
        <div id="loginOrBooking" class="row">
            <c:import url="loginForm.jsp"/>    
            <c:import url="bookingForm.jsp"/>    
        </div>
        <c:import url="/resources/Pages/Include/footer.jsp"/>  
        </div>
        <script src="${pageContext.request.contextPath}/resources/script/home.js"></script>

    </body>
</html>
