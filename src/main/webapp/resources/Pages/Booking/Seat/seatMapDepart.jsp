<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${!listDepartFlight.isEmpty()}">
        <div id="departSeatOption" class="seatOption">
        <div class="seatTitle">${departFlight.flightCode} - ${departFlight.getDepartureTime()} - ${departFlight.getArrivalTime()}</div>
        <div id="listDepartSeat" class="plane">
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
                            <c:forEach items="${listDepartTicket}" varStatus="theCount" begin="${3-i}" step="4">
                                <td>
                                    <c:if test="${listDepartTicket[theCount.index].status=='AVAILABLE'}">
                                        <p id="departSeat${listDepartTicket[theCount.index].ticketId}" 
                                           name="${listDepartTicket[theCount.index].seatCode}" 
                                           class="${listDepartTicket[theCount.index].seatType.type}-${listDepartTicket[theCount.index].status}"
                                           onclick="document.forms['departSeat${listDepartTicket[theCount.index].ticketId}'].submit()">
                                        ${listDepartTicket[theCount.index].seatCode}
                                        </p>
                                        <form name="departSeat${listDepartTicket[theCount.index].ticketId}" action="" method="POST">
                                            <input name="availableDepartSeat" type="hidden" value="${listDepartTicket[theCount.index].ticketId}">
                                        </form>
                                    </c:if>
                                    <c:if test="${listDepartTicket[theCount.index].status!='AVAILABLE'}">
                                        <c:if test="${listDepartTicket[theCount.index].isInListTicket(listChooseDepartTicket)==true}">
                                        <p id="departSeat${listDepartTicket[theCount.index].ticketId}" 
                                           name="${listDepartTicket[theCount.index].seatCode}" 
                                           class="TEMPORARY"
                                           onclick="document.forms['departSeat${listDepartTicket[theCount.index].ticketId}'].submit()">
                                        ${listDepartTicket[theCount.index].seatCode}
                                        </p>
                                        <form name="departSeat${listDepartTicket[theCount.index].ticketId}" action="" method="POST">
                                            <input name="temporaryDepartSeat" type="hidden" value="${listDepartTicket[theCount.index].ticketId}">
                                        </form>
                                        </c:if>
                                        <c:if test="${listDepartTicket[theCount.index].isInListTicket(listChooseDepartTicket)==false}">
                                        <p id="departSeat${listDepartTicket[theCount.index].ticketId}" 
                                           name="${listDepartTicket[theCount.index].seatCode}" 
                                           class="${listDepartTicket[theCount.index].seatType.type}-${listDepartTicket[theCount.index].status}">
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
        <div id="departNotif" class="notif">${departNotif}</div>
        </c:if>
