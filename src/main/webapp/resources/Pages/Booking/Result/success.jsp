<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Successful</title>
        <c:import url="/resources/Pages/Include/libLink.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/CSS/header.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/CSS/bookingInfo.css">
    </head>
    <body>
        <div class="container-fluid">
            <c:import url="/resources/Pages/Include/header.jsp"/>
            <c:if test="${booking.bookingCode == null}">
                <div class="notif row">
                    <div id="alert" class="row alert">
                        <div>YOUR SESSION IS EXPIRE!</div>
                    </div>
                </div>
            </c:if>
            
            <c:if test="${booking.bookingCode != null}">
            <div class="col-md-6">
                <div id="alert" class="row alert">
                    <p>Booking successful!</p>
                    <p>Thank you for using Green Airline Online booking system.</p>
                    <p>You've booked ticket(s) successful with detail below.</p>
                </div>
            </div>


            <div class="bookInfo col-md-12">
                <div class="row"> 
                <div class="col-md-6">
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
                

                <div class="col-md-1"></div>
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
                    </tbody>
                    <tr>
                        <th class="subtitle" colspan="8" style="text-align: left; padding-left: 1em">Total</th>
                        <th class="subtitle">${utilService.formatVND(booking.totalCost)}</th>
                    </tr>
                </table>
                </div>
            </div>
            </div>
            </c:if>
        </div>
    </body>
</html>
