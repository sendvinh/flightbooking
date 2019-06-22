
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="returnFlightOption" class="flightOption">
    <div class="flightTitle">${returnFlight.flightRoute.departure.stationCode} - ${returnFlight.flightRoute.arrival.stationCode} - ${returnFlight.getDepartDate()}</div>
    <c:if test="${listReturnFlight.isEmpty()}">
        <p>No flight found!</p>
    </c:if>
    <div id="listReturnFlight" class="flightTable">
        <c:forEach items="${listReturnFlight}" var="returnFlight" varStatus="theCount"> 
            <div id="returnFlight${returnFlight.flightId}" onclick="document.forms['returnFlight${returnFlight.flightId}'].submit()"
                 <c:if test="${returnFlight.flightId==returnFlightId}">
                     class="chosenFlight">
                 </c:if>
                 <c:if test="${returnFlight.flightId!=returnFlightId}">
                     <c:if test="${ticketDb.getNumberOfTicketOfFlight(returnFlight.flightId, 'AVAILABLE') > 0}">
                         class="flight">
                     </c:if>
                     <c:if test="${ticketDb.getNumberOfTicketOfFlight(returnFlight.flightId, 'AVAILABLE') == 0}">
                         class="noRoomFlight">
                     </c:if>
                 </c:if>
                 <table>      
                     <tr>
                         <td>
                             ${returnFlight.flightCode}
                         </td>

                         <td>
                             ${returnFlight.getDepartureTime()} -
                         </td>
                         <td>
                             ${returnFlight.getArrivalTime()}
                         </td>
                         <td>
                             <img src="${pageContext.request.contextPath}/resources/picture/returnIcon.png">
                             <form name="returnFlight${returnFlight.flightId}" action="" method="POST">
                                 <input name="returnFlight" type="hidden" value="${returnFlight.flightId}">
                             </form>
                         </td>
                     </tr>
                 </table>
            </div>
        </c:forEach> 
    </div>
</div>