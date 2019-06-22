
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seat Option</title>
        <c:import url="/resources/Pages/Include/libLink.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/CSS/seat.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/CSS/cart.css">

    </head>
    <body>
        <div class="container-fluid">
        <c:import url="/resources/Pages/Include/header.jsp"/>    
        <div class="row">
            <div class="col-md-9">
                <div class="col-md-12 title">Choose Your Seat</div>
                <div class="col-md-12 subtitle">Depart</div>
                <div class="row choicemap">
                    <div class="col-md-3 flights">
                        <c:import url="flightDepart.jsp"/>         
                    </div>
                    <div class="col-md-9 seats">
                        <c:import url="seatMapDepart.jsp"/>  
                    </div>
                </div>
                   
                <c:if test="${sessionScope.tripType == 'roundTrip'}" >
                    <div class="col-md-12 subtitle">Return</div>
                    <div class="row choicemap">
                        <div class="col-md-3 flights">
                            <c:import url="flightReturn.jsp"/>     
                        </div>
                        <div class="col-md-9 seats">
                            <c:import url="seatMapReturn.jsp"/>   
                        </div>
                    </div>        
                </c:if>
                <c:import url="annotation.jsp"/>      
            </div>
            <div class="col-md-3">
                <c:import url="cart.jsp"/>   
            </div>
        </div>
        <c:import url="/resources/Pages/Include/backAndNext.jsp"/>                         
        
        
        <form id="passengerInfo" name="passengerInfo" action="passengerInfo" method="POST">
        </form>
        <form id="seatOption" name="seatOption" action="seatOption" method="POST">
        </form>
        
        </div>
                
<c:import url="countDownAndReloadScript.jsp"/>                
<script>
    function back() {
        window.location.href = '${pageContext.request.contextPath}';
    }
    function next() {
        document.getElementById("passengerInfo").submit();
    }
</script>
   
    </body>
</html>
