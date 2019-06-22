
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm</title>
        <c:import url="/resources/Pages/Include/libLink.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/CSS/confirm.css">
    </head>
    <body>
        <div class="container-fluid">
        <c:import url="/resources/Pages/Include/header.jsp"/>
        
        <div id="customerInfo" class="row">
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
                <td>${customer.firstName}, ${customer.lastName}</td>
            </tr>
           
            <tr>
                <td>Email</td>
                <td>${customer.email}</td>
            </tr>
            
            <tr>
                <td>Phone number</td>
                <td>${customer.phone}</td>
            </tr>
            </tbody>
            </table>
            </div>
            <div class="col-md-7">
                <div id="alert" class="row alert">
                    <p>The tickets that have <span style="color: red; font-size: 0.8em">Time out</span> text  are out of holding time. 
                        Please remove them from the ordered ticket list before continue.</p>
                    <p>Please check Booker Information, Passenger Information carefully then choose your payment method before finish booking.</p>
                </div>
            </div>
        </div>
        
        <div id="ticketsInfo" class="row">
            <div class="col-md-12">
            <table class="table tableFormat">
                <thead>
                    <tr>
                        <th colspan="5" class="title">Booking Information</th>
                    </tr>
                </thead>
                <tbody>
                <tr>
                    <th class="subtitle" style="width: 15%">Passenger</th>
                    <th class="subtitle" style="width: 10%">Flight</th>
                    <th class="subtitle center" style="width: 50%">Cost</th>
                    <th class="subtitle center" style="width: 12.5%">Total</th>
                    <th class="subtitle" style="width: 12.5%"></th>
                </tr>
                <c:if test="${!listChooseDepartTicket.isEmpty()}">
                    <tr>
                        <td colspan='5' class="subtitle" style="font-weight: 550">${listChooseDepartTicket.get(0).flight.flightRoute.departure.stationName} - 
                            ${listChooseDepartTicket.get(0).flight.flightRoute.arrival.stationName} -
                            ${listChooseDepartTicket.get(0).flight.getDepartDate()}
                        </td>
                    </tr>
                    <c:forEach var="ticket" items="${listChooseDepartTicket}" varStatus="status">
                    <tr>
                        <td class="subtitle">
                            ${ticket.passengerType.type}<br>
                            ${ticket.firstName}, ${ticket.lastName}<br>
                            <c:if test="${ticket.attachedInfant == true}">
                                (+ Infant)
                            </c:if> 
                        </td>
                        <td>
                            ${ticket.flight.flightCode}<br>
                            ${ticket.flight.getDepartureTime()}<br>
                            ${ticket.seatType.type}<br>
                            Seat ${ticket.seatCode}
                        </td>
                        <td>
                            <table>
                            <tr>
                                <td style="width: 52%">Class ${ticket.seatType.type}</td>
                                <td style="width: 16%" class="number">${utilService.formatVND(ticket.standardCost())}</td>
                                <td style="width: 16%" class="number">${utilService.formatVND(utilService.tax(ticket.standardCost()))}</td>
                                <td style="width: 16%" class="number">${utilService.formatVND(utilService.afterTax(ticket.standardCost()))}</td>
                            </tr>

                            <c:if test = "${ticket.passengerType.ratio != 1}">
                            <tr>
                                <td>Type ${ticket.passengerType.type}</td>
                                <td class="number">${utilService.formatVND(ticket.passengerTypeCost())}</td>
                                <td class="number">${utilService.formatVND(utilService.tax(ticket.passengerTypeCost()))}</td>
                                <td class="number">${utilService.formatVND(utilService.afterTax(ticket.passengerTypeCost()))}</td>
                            </tr>  
                            </c:if>
                            <c:if test ="${ticket.attachedInfant == true}">
                            <tr>
                                <td>Attached infant</td>
                                <td class="number">${utilService.formatVND(ticket.infantCost())}</td>
                                <td class="number">${utilService.formatVND(utilService.tax(ticket.infantCost()))}</td>
                                <td class="number">${utilService.formatVND(utilService.afterTax(ticket.infantCost()))}</td>
                            </tr>  
                            </c:if>

                            <c:forEach items="${ticket.getFlight().getListPromotion()}" var="promotion">
                                <tr>
                                    <td>${promotion.description}</td>
                                    <c:if test = "${promotion.type == 'AMOUNT'}">
                                        <td class="number">${utilService.formatVND(promotion.valueOfPromotion)}</td>
                                        <td class="number">0</td>
                                        <td class="number">${utilService.formatVND(promotion.valueOfPromotion)}</td>
                                    </c:if>    
                                    <c:if test = "${promotion.type == 'PERCENT'}">
                                        <td class="number">${utilService.formatVND(ticket.standardCost()*promotion.valueOfPromotion)}</td>
                                        <td class="number">0</td>
                                        <td class="number">${utilService.formatVND(ticket.standardCost()*promotion.valueOfPromotion)}</td>
                                    </c:if>   
                                </tr>
                            </c:forEach>                                                     
                            <tr>
                                <td>Total</td>
                                <td class="number">${utilService.formatVND(ticket.calculateTotalCostBeforeTax())}</td>
                                <td class="number">${utilService.formatVND(ticket.calculateTax())}</td>
                                <td class="number">${utilService.formatVND(ticket.calculateTotalCostAfterTax())}</td>
                            </tr>
                            </table>
                        </td>
                        <td class="number">
                            ${utilService.formatVND(ticket.calculateTotalCostAfterTax())}
                        </td>
                        <td class="center">
                            <div class="ticketTime">
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
                                </div>
                                <form name="deleteTicket${ticket.ticketId}" action="" method="POST">
                                    <input name="deleteDepartTicket" type="hidden" value="${ticket.ticketId}">
                                </form>
                            </div>
                        </td>
                    </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${listChooseReturnTicket.isEmpty()!=null && !listChooseReturnTicket.isEmpty()}">
                    <tr>
                        <td colspan='5' class="subtitle" style="font-weight: 550">${listChooseReturnTicket.get(0).flight.flightRoute.departure.stationName} - 
                            ${listChooseReturnTicket.get(0).flight.flightRoute.arrival.stationName} -
                            ${listChooseReturnTicket.get(0).flight.getDepartDate()}
                        </td>
                    </tr>
                    <c:forEach var="ticket" items="${listChooseReturnTicket}" varStatus="status">
                    <tr>
                        <td class="subtitle">
                            ${ticket.passengerType.type}<br>
                            ${ticket.firstName}, ${ticket.lastName}<br>
                            <c:if test="${ticket.attachedInfant == true}">
                                (+ Infant)
                            </c:if> 
                        </td>
                        <td>
                            ${ticket.flight.flightCode}<br>
                            ${ticket.flight.getDepartureTime()}<br>
                            ${ticket.seatType.type}<br>
                            Seat ${ticket.seatCode}
                        </td>
                        <td>
                            <table>
                            <tr>
                                <td style="width: 52%">Class ${ticket.seatType.type}</td>
                                <td style="width: 16%" class="number">${utilService.formatVND(ticket.standardCost())}</td>
                                <td style="width: 16%" class="number">${utilService.formatVND(utilService.tax(ticket.standardCost()))}</td>
                                <td style="width: 16%" class="number">${utilService.formatVND(utilService.afterTax(ticket.standardCost()))}</td>
                            </tr>

                            <c:if test = "${ticket.passengerType.ratio != 1}">
                            <tr>
                                <td>Type ${ticket.passengerType.type}</td>
                                <td class="number">${utilService.formatVND(ticket.passengerTypeCost())}</td>
                                <td class="number">${utilService.formatVND(utilService.tax(ticket.passengerTypeCost()))}</td>
                                <td class="number">${utilService.formatVND(utilService.afterTax(ticket.passengerTypeCost()))}</td>
                            </tr>  
                            </c:if>
                            <c:if test ="${ticket.attachedInfant == true}">
                            <tr>
                                <td>Attached infant</td>
                                <td class="number">${utilService.formatVND(ticket.infantCost())}</td>
                                <td class="number">${utilService.formatVND(utilService.tax(ticket.infantCost()))}</td>
                                <td class="number">${utilService.formatVND(utilService.afterTax(ticket.infantCost()))}</td>
                            </tr>  
                            </c:if>

                            <c:forEach items="${ticket.getFlight().getListPromotion()}" var="promotion">
                                <tr>
                                    <td>${promotion.description}</td>
                                    <c:if test = "${promotion.type == 'AMOUNT'}">
                                        <td class="number">${utilService.formatVND(promotion.valueOfPromotion)}</td>
                                        <td class="number">0</td>
                                        <td class="number">${utilService.formatVND(promotion.valueOfPromotion)}</td>
                                    </c:if>    
                                    <c:if test = "${promotion.type == 'PERCENT'}">
                                        <td class="number">${utilService.formatVND(ticket.standardCost()*promotion.valueOfPromotion)}</td>
                                        <td class="number">0</td>
                                        <td class="number">${utilService.formatVND(ticket.standardCost()*promotion.valueOfPromotion)}</td>
                                    </c:if>   
                                </tr>
                            </c:forEach>                                                     
                            <tr>
                                <td>Total</td>
                                <td class="number">${utilService.formatVND(ticket.calculateTotalCostBeforeTax())}</td>
                                <td class="number">${utilService.formatVND(ticket.calculateTax())}</td>
                                <td class="number">${utilService.formatVND(ticket.calculateTotalCostAfterTax())}</td>
                            </tr>
                            </table>
                        </td>
                        <td class="number">
                            ${utilService.formatVND(ticket.calculateTotalCostAfterTax())}
                        </td>
                        <td class="center">
                            <div class="ticketTime">
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
                                </div>
                                <form name="deleteTicket${ticket.ticketId}" action="" method="POST">
                                    <input name="deleteReturnTicket" type="hidden" value="${ticket.ticketId}">
                                </form>
                            </div>
                        </td>
                    </tr>
                    </c:forEach>
                </c:if>
                <tr>
                    <th colspan='3'>Total</th>
                    <th class="number">${utilService.formatVND(bookingCost)}</th>
                    <th></th>
                </tr>
                </tbody>
            </table>
            </div>
        </div>
                    
        
        
        <div id="paymentInfo" class="row">
            <div class="col-md-12">
            <form id="paymentMethod" name="paymentMethod" method="POST" action="finishBooking">
            <table class="table tableFormat">
                <thead>
                <tr>
                    <th colspan="2" class="title">Payment method</th>
                </tr>
                </thead>
                <tr>
                    <td>
                        <input type="radio" name="paymentMethod" value="PAY_ONLINE">
                        <img src="${pageContext.request.contextPath}/resources/picture/paynapas.png">
                    </td>
                    <td class="paymentMethodInfo">
                        <div>
                          <strong>We accept:</strong>
                        </div>

                        <div>- Domestic bank cards issued by 34 Vietnam banks. You will be prompted to enter your card details on the next page.</div>                                     
                        <div>Selecting this form of payment, you should have registered Internet Banking Services.</div>                                     

                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="paymentMethod" value="PAY_LATER" checked="true">
                        <img src="${pageContext.request.contextPath}/resources/picture/paylater.png">
                    </td>
                    <td class="paymentMethodInfo">
                        <div>
                          <strong>Pay later at the agents of Green Airline include:</strong>
                        </div>
  
                          <div>- Ticket counters at the stations and ticketing agents of Green Airline.</div>
                          <div>- VIB's payment addresses.</div>
                          <div>- VNPost's payment addresses.</div>
                          <div>- Payment via Internet Banking/Mobile banking/ATM</div>
    
                    </td>
                </tr>
            </table>
            

            </form>
            </div>
        </div>
            
        <form id="passengerInfo" name="passengerInfo" action="passengerInfo" method="POST">
        </form>
            
        <c:import url="/resources/Pages/Include/backAndNext.jsp"/>                
        <c:import url="../countDownScript.jsp"/>     
        <script>
            <c:if test="${timeOut}">
                $("#next").attr("disabled", true);
            </c:if>
        </script>
        <script src="${pageContext.request.contextPath}/resources/script/confirm.js"></script>
    </body>
</html>
