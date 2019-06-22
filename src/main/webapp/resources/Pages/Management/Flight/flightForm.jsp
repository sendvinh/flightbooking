<%-- 
    Document   : flightForm
    Created on : Apr 15, 2019, 4:44:01 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/webjars/bootstrap-datetimepicker/2.4.2/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.3.1/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/momentjs/2.24.0/moment.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.1.3/js/bootstrap.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap-datetimepicker/2.4.2/js/bootstrap-datetimepicker.min.js"></script>
    </head>
    <body>
        <h1>Flight Form</h1>
        <form:form model-attribute="flight" method="POST">
            <label for="flightCode">Flight Code:</label>
            <input id="flightCode" name="flightCode" type="text" placeholder="Flight Code" value="${flight.flightCode}"><br>
            <label for="aircraft">Aircraft:</label>
            <select name="aircraft.aircraftCode">
                <c:forEach var="aircraft" items="${listAircraft}">                         
                    <option value="${aircraft.aircraftCode}"
                            <c:if test="${flight.aircraft.aircraftCode == aircraft.aircraftCode}">
                                selected
                            </c:if>>
                        ${aircraft.aircraftCode}
                    </option>
                </c:forEach>
            </select><br>
            <label for="flightRoute">Flight Route:</label>
            <select name="flightRoute.flightRouteId">
                <c:forEach var="flightRoute" items="${listFlightRoute}">                         
                    <option value="${flightRoute.flightRouteId}"
                            <c:if test="${flight.flightRoute.flightRouteId == flightRoute.flightRouteId}">
                                selected
                            </c:if>>
                        ${flightRoute.departure.stationName} --> ${flightRoute.arrival.stationName}
                    </option>
                </c:forEach>
            </select><br>
            
            <div class='input-group date' id='departTime'>
                <label for="departTime" >Depart time:</label>
                <input name='departTime' type='text' value="${flight.departTime}"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>

                                            

            <div class='input-group date' id='arriveTime'>
                <label for="arriveTime" >Arrive time:</label>
                <input name='arriveTime' type='text' value="${flight.arriveTime}"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>

            <label for="promotionId">List promotion:</label><br>
            <c:forEach var="promotion" items="${listPromotion}" varStatus="status"> 
                <input name="promotionId" type="checkbox"  value="${promotion.promotionId}"> ${promotion.description}<br>
            </c:forEach>
                
            <label for="businessSeat">Number of business seat:</label><br>    
           
            <select name="businessSeat">
                <c:forEach var = "i" begin = "1" end = "12"> 
                    <option value="${i}">${i}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Add"/>
        </form:form>
<!--    flight code
    aircraft
    flight route
    depart datetime
    arrive datetime
    list promotion-->
    <script type="text/javascript">
        $(function () {
            $('#departTime').datetimepicker({format: 'yyyy-mm-dd hh:ii'});
            $('#arriveTime').datetimepicker({format: 'yyyy-mm-dd hh:ii'});
        });
    </script>
    
</body>
</html>
