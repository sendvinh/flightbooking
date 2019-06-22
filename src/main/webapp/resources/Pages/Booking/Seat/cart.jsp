<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="cart" class="col-md-12 row cart">
    <div class="col-md-12 title">
        Your cart
    </div>
<c:if test="${sessionScope.tripType == 'oneWay' && listChooseDepartTicket.isEmpty()}">
    <div class="col-md-12 empty">
        Your cart is empty
    </div>
</c:if>
<c:if test="${sessionScope.tripType == 'roundTrip' && listChooseDepartTicket.isEmpty() && listChooseReturnTicket.isEmpty()}">
<div class="col-md-12 empty">
    Your cart is empty
</div>
</c:if>
  
<c:if test="${!listChooseDepartTicket.isEmpty() || !listChooseReturnTicket.isEmpty()}">
            <!--depart tickets-->
            <c:forEach items="${listChooseDepartTicket}" var="ticket">
                <div class="col-md-12 ticket">
                    <div class="row">
                <div class="col-md-8 ticketInfo">
                    <div>${ticket.flight.flightCode} 
                        - ${ticket.flight.flightRoute.departure.stationCode}
                        -> ${ticket.flight.flightRoute.arrival.stationCode}</div> 
                    <div>${ticket.flight.getDepartDate()} - ${ticket.flight.getDepartureTime()}</div>
                    <div>${ticket.seatType.type} - Seat ${ticket.seatCode}</div>
                </div>
                <div class="col-md-4 ticketTime">
                    <div id="timeOut${ticket.ticketId}" class="time">
                        <c:if test="${ticket.timeOutSecond == 0}">
                            Time out
                        </c:if>
                        <c:if test="${ticket.timeOutSecond != 0}">
                            ${ticket.timeOutSecond}
                        </c:if>
                    </div>
                    <div class="deletebutton">
                        <img src="${pageContext.request.contextPath}/resources/picture/delete.png"
                             onclick="document.forms['deleteTicket${ticket.ticketId}'].submit()">
                        <form name="deleteTicket${ticket.ticketId}" action="" method="POST">
                            <input name="deleteDepartTicket" type="hidden" value="${ticket.ticketId}">
                        </form>
                    </div>
                </div>
                </div>    
                </div>        
            </c:forEach>
            <!--return tickets-->
            <c:forEach items="${listChooseReturnTicket}" var="ticket">
                <div class="col-md-12 ticket">
                    <div class="row">
                <div class="col-md-8 ticketInfo">
                    <div>${ticket.flight.flightCode} 
                        - ${ticket.flight.flightRoute.departure.stationCode}
                        -> ${ticket.flight.flightRoute.arrival.stationCode}</div> 
                    <div>${ticket.flight.getDepartDate()} - ${ticket.flight.getDepartureTime()}</div>
                    <div>${ticket.seatType.type} - Seat ${ticket.seatCode}</div>
                </div>
                <div class="col-md-4 ticketTime">
                    <div id="timeOut${ticket.ticketId}" class="time">
                        ${ticket.timeOutSecond}
                    </div>
                    <div class="deletebutton">
                        <img src="${pageContext.request.contextPath}/resources/picture/delete.png"
                             onclick="document.forms['deleteTicket${ticket.ticketId}'].submit()">
                        <form name="deleteTicket${ticket.ticketId}" action="" method="POST">
                            <input name="deleteReturnTicket" type="hidden" value="${ticket.ticketId}">
                        </form>
                    </div>
                </div>
                    </div>
                </div>      
            </c:forEach>
        
</c:if>
</div>