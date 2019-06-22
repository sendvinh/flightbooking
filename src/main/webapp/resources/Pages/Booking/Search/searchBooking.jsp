<%-- 
    Document   : searchBooking
    Created on : Mar 16, 2019, 9:26:30 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <c:import url="/resources/Pages/Include/libLink.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/CSS/header.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/CSS/bookingInfo.css">
    </head>
    <body>
        <div class="container-fluid">
        <c:import url="/resources/Pages/Include/header.jsp"/>
        <div class="searchForm col-md-12">
            <div class="row">
            <div class="col-md-12">
            <form id="searchForm" action="" method="GET">

                    <div class="row input">  
                    <label for="bookingCode" class="col-md-2">Booking code*: </label>
                    <input name="bookingCode" id="bookingCode" class="form-control col-md-4" type="text">
                    <div class="error col-md-5">${bookingCodeError}</div>
                    </div>    


                    <div class="row input">
                    <label for="email" class="col-md-2">Email*:</label>
                    <input name="email" id="email" class="form-control col-md-4" type="text">
                    <div class="error col-md-5">${emailError}</div>
                    </div> 


                    <div class="row input">
                    <label for="phone" class="col-md-2">Phone*:</label>
                    <input name="phone" id="phone" class="form-control col-md-4" type="text">
                    <div class="error col-md-5">${phoneError}</div>
                    </div> 

                    <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8 error">
                        ${errorMsg}
                    </div>
                    </div> 

                    <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-4">
                        <button class="btn btn-success" type="submit" name="submit" id="submit" style="width: 5em">Search</button>
                    </div>
                </div>
            </form>
            </div>
            </div>
        </div>
        <c:if test="${booking!=null}">
            <div class="bookInfo col-md-12">
                <div class="row">
                <div class="col-md-5">
                    <table class="table tableFormat">
                    <thead>
                            <tr>
                                <th colspan="2" class="title">Booker Information</th>
                            </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Name</td>
                        <td>${booking.customer.firstName}, ${booking.customer.lastName}</td>
                    </tr>

                    <tr>
                        <td>Email</td>
                        <td>${booking.customer.email}</td>
                    </tr>

                    <tr>
                        <td>Phone number</td>
                        <td>${booking.customer.phone}</td>
                    </tr>
                    </tbody>
                    </table>
                </div> 

                <div class="col-md-2"></div>
                <div class="col-md-5">
                <table class="table tableFormat">
                    <tr>
                        <th>Booking Code</th>
                        <td>${booking.bookingCode}</td>
                    </tr>
                    <c:if test="${booking.paymentMethod == 'PAY_LATER'}">
                    <tr>
                        <th>Payment Code</th>
                        <td>${booking.paymentCode}</td>
                    </tr>
                    </c:if>
                </table>
                </div>   
            </div>
            </div>
                    
            <div class="ticketInfo col-md-12">
                <div class="row">
                    <div class="col-md-12">
                <table class="table tableFormat">
                    <thead>
                        <tr>
                            <th colspan="9" class="title">Booking Information</th>
                        </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th class="subtitle">Passenger</th>
                        <th class="subtitle">Object</th>
                        <th class="subtitle">Infant</th>
                        <th class="subtitle">Flight</th>
                        <th class="subtitle">Seat</th>
                        <th class="subtitle">Depart time</th>
                        <th class="subtitle">Payment</th>
                        <th class="subtitle">Status</th>
                        <th class="subtitle">Cost</th>
                    </tr>
                    <c:if test="${booking.listTicket!=null && !booking.listTicket.isEmpty()}">
                        <c:forEach var="ticket" items="${booking.listTicket}" varStatus="status">
                            <tr>
                            <td class="info">${ticket.firstName}, ${ticket.lastName}</td>
                            <td class="info">${ticket.passengerType.type}</td>
                            <td class="info">
                                <c:if test="${ticket.attachedInfant == true}">
                                    ${ticket.infantName}
                                </c:if>
                                <c:if test="${ticket.attachedInfant == false}">
                                    None
                                </c:if>
                            </td> 
                            <td class="info">
                                ${ticket.flight.flightCode}<br>
                                ${ticket.flight.flightRoute.departure.stationCode} -
                                ${ticket.flight.flightRoute.arrival.stationCode}
                            </td>
                            <td class="info">${ticket.seatType.type}<br>Seat ${ticket.seatCode}
                            <td class="info">${ticket.flight.getDepartureTime()}<br>${ticket.flight.getDepartDate()}</td>
                            <td class="info">${booking.paymentMethod}<br>${booking.paymentStatus}</td>
                            <td class="info">${booking.state}</td>
                            <td class="info">${utilService.formatVND(ticket.price)}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${booking.listTicket==null || booking.listTicket.isEmpty()}">
                    <tr><td colspan="9" style="text-align: center">Ticket(s) is(are) out of date</td></tr>
                    </c:if>
                    </tbody>
                    <tr>
                        <th class="subtitle" colspan="8" style="text-align: left; padding-left: 1em">Total</th>
                        <th class="subtitle">${utilService.formatVND(booking.totalCost)}</th>
                    </tr>
                </table>
                    </div>
                </div>
            </div>
            <c:if test="${booking.paymentStatus == 'WAIT_FOR_PAY' && booking.state == 'IN_PROGRESS'}">
            <div class="pay row">
                <div class="col-md-4"></div>
                <div class="col-md-2">
                    <form action="searchBooking/pay" method="POST">
                        <input name="paymentMethod" type="hidden" value="PAY_LATER">
                        <input name="bookingId" type="hidden" value="${booking.bookingId}">
                        <button class="btn btn-success" type="submit" name="submit" id="submit" style="width: 80%">Pay later</button>
                    </form>
                </div>
                <div class="col-md-2">
                    <form action="searchBooking/pay" method="POST">
                        <input name="paymentMethod" type="hidden" value="PAY_ONLINE">
                        <input name="bookingId" type="hidden" value="${booking.bookingId}">
                        <button class="btn btn-success" type="submit" name="submit" id="submit" style="width: 80%">Pay online</button>
                    </form>
                </div>
                <div class="col-md-4"></div>
            </div>
            </c:if>
        </c:if>

    <script src="${pageContext.request.contextPath}/resources/script/searchbooking.js"></script>
    </body>
</html>
