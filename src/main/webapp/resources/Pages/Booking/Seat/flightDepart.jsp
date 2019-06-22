
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="departFlightOption" class="flightOption">
    <div class="flightTitle">${departFlight.flightRoute.departure.stationCode} - ${departFlight.flightRoute.arrival.stationCode} - ${departFlight.getDepartDate()}</div>
    <c:if test="${listDepartFlight.isEmpty()}">
        <p>No flight found!</p>
    </c:if>
    <div id="listDepartFlight" class="flightTable">
        <c:forEach items="${listDepartFlight}" var="departFlight" varStatus="theCount"> 
            <div id="departFlight${departFlight.flightId}" onclick="document.forms['departFlight${departFlight.flightId}'].submit()"
                 <c:if test="${departFlight.flightId==departFlightId}">
                     class="chosenFlight">
                 </c:if>
                 <c:if test="${departFlight.flightId!=departFlightId}">
                     <c:if test="${ticketDb.getNumberOfTicketOfFlight(departFlight.flightId, 'AVAILABLE') > 0}">
                         class="flight">
                     </c:if>
                     <c:if test="${ticketDb.getNumberOfTicketOfFlight(departFlight.flightId, 'AVAILABLE') == 0}">
                         class="noRoomFlight">
                     </c:if>
                 </c:if>
                 <table>      
                     <tr>
                         <td>
                             ${departFlight.flightCode}
                         </td>

                         <td>
                             ${departFlight.getDepartureTime()} -
                         </td>
                         <td>
                             ${departFlight.getArrivalTime()}
                         </td>
                         
                         <td>
                             <img src="${pageContext.request.contextPath}/resources/picture/departIcon.png">
                             <form name="departFlight${departFlight.flightId}" action="" method="POST">
                                 <input name="departFlight" type="hidden" value="${departFlight.flightId}">
                             </form>
                         </td>
                     </tr>
                 </table>
            </div>
        </c:forEach> 
    </div>
</div>