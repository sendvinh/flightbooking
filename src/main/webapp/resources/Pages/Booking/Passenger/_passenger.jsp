
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Passenger Info</title>
        <c:import url="/resources/Pages/Include/libLink.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/CSS/header.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/CSS/passenger.css">
          
       
    </head>
    <body>
        <div class="container-fluid">
        <c:import url="/resources/Pages/Include/header.jsp"/>     
        <c:import url="alert.jsp"/>  
        <c:import url="passengerForm.jsp"/>   
        <c:import url="/resources/Pages/Include/backAndNext.jsp"/>   
        <form id="seatOption" name="seatOption" action="seatOption" method="POST">
        </form>
        </div> 
        <c:import url="../countDownScript.jsp"/>     
    <script>
        <c:if test="${timeOut}">
            $("#next").attr("disabled", true);
        </c:if>
    </script>
    <script src="${pageContext.request.contextPath}/resources/script/passenger.js"></script>
</body>
</html>
