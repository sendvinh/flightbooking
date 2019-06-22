<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>




<form:form name="passengerInfo" method="post" action="confirm" modelAttribute="modelWrapper">
<div id="tickets" class="row">
    <div class="col-md-12">
        <table class="table tableFormat">
            <thead>
            <th colspan="4">Passenger Information</th>
            </thead>
            <tbody>
            <tr>
                <th style="width: 35%" class="subtitle">Passenger</th>
                <th style="width: 30%" class="subtitle">Infant</th>
                <th style="width: 25%" class="subtitle center">Ticket</th>
                <th style="width: 10%" class="subtitle"></th>
            </tr>
            <c:if test="${modelWrapper.listDepartTicket.isEmpty()!=null && !modelWrapper.listDepartTicket.isEmpty()}">
                <tr>
                    <td colspan="4" class="subtitle">${departFlight.flightRoute.departure.stationName} - ${departFlight.flightRoute.arrival.stationName}</td>
                </tr>
            </c:if>    
                
            <c:forEach var="ticket" items="${modelWrapper.listDepartTicket}" varStatus="status">
            
            <tr>
                <td>
                    <div class="input-group input-group-sm info">
                        <div class="input-group-prepend col-md-5 labelcontain">
                            <label class="input-group-text label" for="departPassengerType${status.count}">Object</label>
                        </div>
                        <select name="listDepartTicket[${status.index}].passengerType.id" class="custom-select custom-select-sm col-md-7" id="departPassengerType${status.count}" required="required">
                        <c:forEach var="passengerType" items="${listPassengerType}">                         
                            <option value="${passengerType.id}"
                                    <c:if test="${ticket.passengerType.id == passengerType.id}">
                                        selected
                                    </c:if>>
                                ${passengerType.type}
                            </option>
                        </c:forEach>
                        </select>
                    </div>

                    <div class="input-group input-group-sm info">
                        <div class="input-group-prepend col-md-5 labelcontain">
                            <label class="input-group-text label" for="departGender${status.count}">Gender</label>
                        </div>
                        <select name="listDepartTicket[${status.index}].gender" class="custom-select custom-select-sm col-md-7" id="departGender${status.count}" required="required">
                            <option value="MALE"
                                    <c:if test="${ticket.gender == 'MALE'}">
                                        selected
                                    </c:if>> Male 
                            </option>
                            <option value="FEMALE"
                                    <c:if test="${ticket.gender == 'FEMALE'}">
                                        selected
                                    </c:if>> Female 
                            </option>
                        </select>
                    </div>
                            
                    <div class="input-group input-group-sm info">
                        <div class="input-group-prepend col-md-5 labelcontain">
                            <lable class="input-group-text label" for="departFirstName${status.count}">First name</lable>
                        </div>
                        <input name="listDepartTicket[${status.index}].firstName" class="form-control form-control-sm col-md-7 firstName" value="${ticket.firstName}" id="departFirstName${status.count}" type="text">
                    </div>
                    <div class="input-group input-group-sm">
                        <div class="col-md-5">
                        </div>
                        <c:set var="departFirstNameError" value="${requestScope['departFirstNameError'.concat(status.index)]}" />
                        <div class="error" id="vDepartFirstName${status.count}">${departFirstNameError}</div>
                    </div>
                        
                    <div class="input-group input-group-sm info">
                        <div class="input-group-prepend col-md-5 labelcontain">
                            <lable class="input-group-text label" for="departLastName${status.count}">Last name</lable>
                        </div>
                        <input name="listDepartTicket[${status.index}].lastName" class="form-control form-control-sm col-md-7 lastName" value="${ticket.lastName}" id="departLastName${status.count}" type="text">
                    </div>
                    <div class="input-group input-group-sm">
                        <div class="col-md-5">
                        </div>
                        <c:set var="departLastNameError" value="${requestScope['departLastNameError'.concat(status.index)]}" />
                        <div class="error" id="vDepartLastName${status.count}">${departLastNameError}</div>
                    </div>
                        
                    <div class="input-group input-group-sm info">
                        <div class="input-group input-group-sm date" data-provide="datepicker" id="dateOfBirth${status.count}">
                           <div class="input-group-prepend col-md-5 labelcontain">
                                <lable class="input-group-text label" for="departDOB${status.count}">Date of birth</lable>
                            </div>
                            <input name="listDepartTicket[${status.index}].dateOfBirth" class="form-control form-control-sm col-md-7 birth" id="departDOB${status.count}" value="${ticket.getStringOfDateOfBirth()}" type="text">
                            <div class="input-group-addon">
                                <span class="glyphicon glyphicon-th"></span>
                            </div>
                        </div>
                    </div>
                    <div class="input-group input-group-sm">
                        <div class="col-md-5">
                        </div>
                        <c:set var="departDOBError" value="${requestScope['departDOBError'.concat(status.index)]}" />
                        <div class="error" id="vDepartDOB${status.count}">${departDOBError}</div>
                    </div>
                </td>
                <td>     
                    <div class="input-group input-group-sm info attachedInfantContain">
                        <div class="input-group-prepend col-md-5 labelcontain">
                            <lable class="input-group-text label">Infant</lable>
                        </div>
                        <div class="form-control form-control-sm col-md-7 attachedInfant">
                            <input name="listDepartTicket[${status.index}].attachedInfant" value="true" type="radio" id="departInfantTrue${status.count}"
                           <c:if test="${ticket.attachedInfant == 'true'}">
                               checked
                           </c:if>>
                            <lable>Yes</lable>
                            <input name="listDepartTicket[${status.index}].attachedInfant" value="false" type="radio" id="departInfantFalse${status.count}"
                           <c:if test="${ticket.attachedInfant == 'false'}">
                               checked
                           </c:if>>
                            <lable>No</lable>
                        </div>
                    </div>
                    <div class="input-group input-group-sm info">
                        <div class="input-group-prepend col-md-5 labelcontain">
                            <lable class="input-group-text label" for="departInfantName${status.count}">Infant name</lable>
                        </div>
                        <input name="listDepartTicket[${status.index}].infantName" class="form-control form-control-sm col-md-7 infantName" value="${ticket.infantName}" id="departInfantName${status.count}" type="text" >
                    </div>
                    <div class="input-group input-group-sm">
                        <div class="col-md-5">
                        </div>
                        <c:set var="departInfantError" value="${requestScope['departInfantError'.concat(status.index)]}" />
                        <div class="error" id="vDepartInfantName${status.count}">${departInfantError}</div>
                    </div>
                </td>
                <td class="center">
                    <div class="ticketInfo">
                        <div>${ticket.flight.flightCode} 
                            - ${ticket.flight.flightRoute.departure.stationCode}
                            -> ${ticket.flight.flightRoute.arrival.stationCode}</div> 
                        <div>${ticket.flight.getDepartDate()} - ${ticket.flight.getDepartureTime()}</div>
                        <div>${ticket.seatType.type} - Seat ${ticket.seatCode}</div>
                    </div>
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
                    </div>
                </td>
            </tr>
            </c:forEach>
            <c:if test="${modelWrapper.listReturnTicket.isEmpty()!=null && !modelWrapper.listReturnTicket.isEmpty()}">
                <tr>
                    <td colspan="4" class="subtitle">${returnFlight.flightRoute.departure.stationName} - ${returnFlight.flightRoute.arrival.stationName}</td>
                </tr>
            </c:if>
            <c:forEach var="ticket" items="${modelWrapper.listReturnTicket}" varStatus="status">
                <tr>
                <td>
                    <div class="input-group input-group-sm info">
                        <div class="input-group-prepend col-md-5 labelcontain">
                            <label class="input-group-text label" for="returnPassengerType${status.count}">Object</label>
                        </div>
                        <select class="custom-select custom-select-sm col-md-7" name="listReturnTicket[${status.index}].passengerType.id" id="returnPassengerType${status.count}" >
                        <c:forEach var="passengerType" items="${listPassengerType}">                         
                            <option value="${passengerType.id}"
                                    <c:if test="${ticket.passengerType.id == passengerType.id}">
                                        selected
                                    </c:if>>
                                ${passengerType.type}
                            </option>
                        </c:forEach>
                        </select>
                    </div>

                    <div class="input-group input-group-sm info">
                        <div class="input-group-prepend col-md-5 labelcontain">
                            <label class="input-group-text label" for="returnGender${status.count}">Gender</label>
                        </div>
                        <select class="custom-select custom-select-sm col-md-7" name="listReturnTicket[${status.index}].gender" id="returnGender${status.count}" >
                            <option value="MALE"
                                    <c:if test="${ticket.gender == 'MALE'}">
                                        selected
                                    </c:if>> Male 
                            </option>
                            <option value="FEMALE"
                                    <c:if test="${ticket.gender == 'FEMALE'}">
                                        selected
                                    </c:if>> Female 
                            </option>
                        </select>
                    </div>
                            
                    <div class="input-group input-group-sm info">
                        <div class="input-group-prepend col-md-5 labelcontain">
                            <lable class="input-group-text label" for="returnFirstName${status.count}">First name</lable>
                        </div>
                        <input class="form-control form-control-sm col-md-7 firstName" name="listReturnTicket[${status.index}].firstName" value="${ticket.firstName}" id="returnFirstName${status.count}" type="text" >
                        <div class="invalid-feedback"></div>
                    </div>
                    <div class="input-group input-group-sm">
                        <div class="col-md-5">
                        </div>
                        <c:set var="returnFirstNameError" value="${requestScope['returnFirstNameError'.concat(status.index)]}" />
                        <div class="error" id="vReturnFirstName${status.count}">${returnFirstNameError}</div>
                    </div>
                        
                    <div class="input-group input-group-sm info">
                        <div class="input-group-prepend col-md-5 labelcontain">
                            <lable class="input-group-text label" for="returnLastName${status.count}">Last name</lable>
                        </div>
                        <input class="form-control form-control-sm col-md-7 lastName" name="listReturnTicket[${status.index}].lastName" value="${ticket.lastName}" id="returnLastName${status.count}" type="text" >
                        <div class="invalid-feedback"></div>
                    </div>
                    <div class="input-group input-group-sm">
                        <div class="col-md-5">
                        </div>
                        <c:set var="returnLastNameError" value="${requestScope['returnLastNameError'.concat(status.index)]}" />
                        <div class="error" id="vReturnLastName${status.count}">${returnLastNameError}</div>
                    </div>
                        
                    <div class="input-group input-group-sm info">
                        <div class="input-group input-group-sm date" data-provide="datepicker" id="dateOfBirth${status.count}">
                           <div class="input-group-prepend col-md-5 labelcontain">
                                <lable class="input-group-text label" for="returnDOB${status.count}">Date of birth</lable>
                            </div>
                            <input class="form-control form-control-sm col-md-7 birth" name="listReturnTicket[${status.index}].dateOfBirth" id="returnDOB${status.count}" value="${ticket.getStringOfDateOfBirth()}" type="text" >
                            <div class="input-group-addon">
                                <span class="glyphicon glyphicon-th"></span>
                            </div>
                        </div>
                    </div>
                    
                    <div class="input-group input-group-sm">
                        <div class="col-md-5">
                        </div>
                        <c:set var="returnDOBError" value="${requestScope['returnDOBError'.concat(status.index)]}" />
                        <div class="error" id="vReturnDOB${status.count}">${returnDOBError}</div>
                    </div>
                </td>
                <td>     
                    <div class="input-group input-group-sm info attachedInfantContain">
                        <div class="input-group-prepend col-md-5 labelcontain">
                            <lable class="input-group-text label">Infant</lable>
                        </div>
                        <div class="form-control form-control-sm col-md-7 attachedInfant">
                            <input name="listReturnTicket[${status.index}].attachedInfant" value="true" type="radio" id="returnInfantTrue${status.count}" 
                           <c:if test="${ticket.attachedInfant == 'true'}">
                               checked
                           </c:if>>
                            <lable>Yes</lable>

                            <input name="listReturnTicket[${status.index}].attachedInfant" value="false" type="radio" id="returnInfantFalse${status.count}" 
                           <c:if test="${ticket.attachedInfant == 'false'}">
                               checked
                           </c:if>>
                            <lable>No</lable>
                        </div>
                    </div>
                    <div class="input-group input-group-sm info">
                        <div class="input-group-prepend col-md-5 labelcontain">
                            <lable class="input-group-text label" for="returnInfantName${status.count}">Infant name</lable>
                        </div>
                        <input class="form-control form-control-sm col-md-7 infantName" name="listReturnTicket[${status.index}].infantName" value="${ticket.infantName}" id="returnInfantName${status.count}" type="text" >
                    </div>
                    <div class="input-group input-group-sm">
                        <div class="col-md-5">
                        </div>
                        <c:set var="returnInfantError" value="${requestScope['returnInfantError'.concat(status.index)]}" />
                        <div class="error" id="vReturnInfantName${status.count}">${returnInfantError}</div>
                    </div>

                </td>
                <td class="center">
                    <div class="ticketInfo">
                        <div>${ticket.flight.flightCode} 
                            - ${ticket.flight.flightRoute.departure.stationCode}
                            -> ${ticket.flight.flightRoute.arrival.stationCode}</div> 
                        <div>${ticket.flight.getDepartDate()} - ${ticket.flight.getDepartureTime()}</div>
                        <div>${ticket.seatType.type} - Seat ${ticket.seatCode}</div>
                    </div>
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
                    </div>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
    
<div id="customer" class="row">
    <div class="col-md-7">
    <table class="table tableFormat">
            <thead>
            <tr>
                <th colspan="2">Booker Information</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                <td style="width:45%">     
                    <div class="input-group input-group-sm info">
                        <div class="input-group-prepend col-md-5 labelcontain">
                            <lable class="input-group-text label" for="customerFirstName">First name</lable>
                        </div>
                        <input id="customerFirstName" name="customer.firstName" type="text" placeholder="firstName" value="${customer.firstName}" class="form-control form-control-sm col-md-7 firstName">
                    </div>
                    <div class="input-group input-group-sm">
                        <div class="col-md-5">
                        </div>
                        <div class="error" id="customerFirstNameError">${customerFirstNameError}</div>
                    </div>
                        
                    <div class="input-group input-group-sm info">
                        <div class="input-group-prepend col-md-5 labelcontain">
                            <lable class="input-group-text label" for="customerLastName">Last name</lable>
                        </div>
                        <input id="customerLastName" name="customer.lastName" type="text" placeholder="lastName" value="${customer.lastName}" class="form-control form-control-sm col-md-7 lastName">
                    </div>
                    <div class="input-group input-group-sm">
                        <div class="col-md-5">
                        </div>
                        <div class="error" id="customerLastNameError">${customerLastNameError}</div>
                    </div>
                </td>
                <td style="width:55%">     
                    <div class="input-group input-group-sm info">
                        <div class="input-group-prepend col-md-5 labelcontain">
                            <lable class="input-group-text label" for="customerEmail">Email</lable>
                        </div>
                        <input id="customerEmail" name="customer.email" type="email" placeholder="email" value="${customer.email}" class="form-control form-control-sm col-md-7 email">
                    </div>
                    <div class="input-group input-group-sm">
                        <div class="col-md-5">
                        </div>
                        <div class="error" id="customerEmailError">${customerEmailError}</div>
                    </div>
                        
                    <div class="input-group input-group-sm info">
                        <div class="input-group-prepend col-md-5 labelcontain">
                            <lable class="input-group-text label" for="customerLastName">Phone</lable>
                        </div>
                        <input id="customerPhone" name="customer.phone" type="phone" placeholder="phone" value="${customer.phone}" class="form-control form-control-sm col-md-7 phone">
                    </div>
                    <div class="input-group input-group-sm">
                        <div class="col-md-5">
                        </div>
                        <div class="error" id="customerPhoneError">${customerPhoneError}</div>
                    </div>
                </td>
                </tr>
            </tbody>
    </table>
    </div>
</div>        
        
</form:form>
<c:forEach var="ticket" items="${modelWrapper.listDepartTicket}" varStatus="status">
    <form name="deleteTicket${ticket.ticketId}" action="" method="POST">
        <input name="deleteDepartTicket" type="hidden" value="${ticket.ticketId}">
    </form>
</c:forEach>
<c:forEach var="ticket" items="${modelWrapper.listReturnTicket}" varStatus="status">
    <form name="deleteTicket${ticket.ticketId}" action="" method="POST">
        <input name="deleteReturnTicket" type="hidden" value="${ticket.ticketId}">
    </form>
</c:forEach>