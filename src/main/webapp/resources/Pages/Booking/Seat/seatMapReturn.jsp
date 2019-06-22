<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${!listReturnFlight.isEmpty()}">
    <div id="returnSeatOption" class="seatOption">
        <div class="seatTitle">${returnFlight.flightCode} - ${returnFlight.getDepartureTime()} - ${returnFlight.getArrivalTime()}</div>
        <div id="listReturnSeat" class="plane">
            <div class="head">
                <table class="number">
                    <tr>
                        <td>4</td>
                    </tr>
                    <tr>
                        <td>3</td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td>2</td>
                    </tr>
                    <tr>
                        <td>1</td>
                    </tr>
                </table>
            </div>
            <div class="cabin">
                <table class="seatTable">
                    <c:forEach var="i" begin="0" end="3">
                        <c:if test="${i==2}">
                            <tr><td><p class="aisle"></p></td></tr>
                        </c:if>
                        <tr>
                        <c:forEach items="${listReturnTicket}" varStatus="theCount" begin="${3-i}" step="4">
                            <td>
                            <c:if test="${listReturnTicket[theCount.index].status=='AVAILABLE'}">
                                <p id="returnSeat${listReturnTicket[theCount.index].ticketId}" 
                                   name="${listReturnTicket[theCount.index].seatCode}" 
                                   class="${listReturnTicket[theCount.index].seatType.type}-${listReturnTicket[theCount.index].status}"
                                   onclick="document.forms['returnSeat${listReturnTicket[theCount.index].ticketId}'].submit()">
                                    ${listReturnTicket[theCount.index].seatCode}
                                </p>
                                <form name="returnSeat${listReturnTicket[theCount.index].ticketId}" action="" method="POST">
                                    <input name="availableReturnSeat" type="hidden" value="${listReturnTicket[theCount.index].ticketId}">
                                </form>
                            </c:if>
                            <c:if test="${listReturnTicket[theCount.index].status!='AVAILABLE'}">
                                <c:if test="${listReturnTicket[theCount.index].isInListTicket(listChooseReturnTicket)==true}">
                                    <p id="returnSeat${listReturnTicket[theCount.index].ticketId}" 
                                       name="${listReturnTicket[theCount.index].seatCode}" 
                                       class="TEMPORARY"
                                       onclick="document.forms['returnSeat${listReturnTicket[theCount.index].ticketId}'].submit()">
                                        ${listReturnTicket[theCount.index].seatCode}
                                    </p>
                                    <form name="returnSeat${listReturnTicket[theCount.index].ticketId}" action="" method="POST">
                                        <input name="temporaryReturnSeat" type="hidden" value="${listReturnTicket[theCount.index].ticketId}">
                                    </form>
                                </c:if>
                                <c:if test="${listReturnTicket[theCount.index].isInListTicket(listChooseReturnTicket)==false}">
                                    <p id="returnSeat${listReturnTicket[theCount.index].ticketId}" 
                                       name="${listReturnTicket[theCount.index].seatCode}" 
                                       class="${listReturnTicket[theCount.index].seatType.type}-${listReturnTicket[theCount.index].status}">
                                        X
                                    </p>
                                </c:if>
                            </c:if>
                            </td>
                        </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div style="clear: both;">
            </div>
        </div>
    </div>
    <div id="returnNotif" class="notif">${returnNotif}</div>
</c:if>
