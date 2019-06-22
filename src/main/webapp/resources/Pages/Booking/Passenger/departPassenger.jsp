<form:form name="passengerInfo" method="post" action="confirm" modelAttribute="modelWrapper">
        <c:if test="${!listChooseDepartTicket.isEmpty()}">
            <h2>Depart Ticket:</h2>
            <div id="departTickets">
                    <c:forEach var="ticket" items="${modelWrapper.listDepartTicket}" varStatus="status">
                        <p>${ticket.flight.flightCode} 
                            - ${ticket.flight.flightRoute.departure.stationName}
                            - ${ticket.flight.flightRoute.arrival.stationName} 
                            - ${ticket.flight.getDepartDate()} ${ticket.flight.getDepartureTime()}
                            - Seat ${ticket.seatCode}
                        </p>
                        <label>Type:</label>
                        <select name="listDepartTicket[${status.index}].passengerType.id" id="passengerType${status.count}" required="required">
                            <c:forEach var="passengerType" items="${listPassengerType}">                         
                                <option value="${passengerType.id}"
                                        <c:if test="${ticket.passengerType.id == passengerType.id}">
                                            selected
                                        </c:if>>
                                    ${passengerType.type}
                                </option>
                            </c:forEach>
                        </select><br>  
                        <label>Identity Number:</label>
                        <input name="listDepartTicket[${status.index}].idCard" value="${ticket.idCard}" id="idCard${status.count}" type="text" placeholder="idCard" ><br>
                        <label>First Name:</label>
                        <input name="listDepartTicket[${status.index}].firstName" value="${ticket.firstName}" id="firstName${status.count}" type="text" placeholder="firstName" ><br>
                        <label>Last Name:</label>
                        <input name="listDepartTicket[${status.index}].lastName" value="${ticket.lastName}" id="lastName${status.count}" type="text" placeholder="lastName" ><br>
                        <label>Gender:</label>
                        <select name="listDepartTicket[${status.index}].gender" id="gender${status.count}" required="required">
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
                        </select><br>
                        <label>Date of Birth:</label>
                        <input name="listDepartTicket[${status.index}].dateOfBirth" value="${ticket.dateOfBirth}" id="dateOfBirth${status.count}" type="date" placeholder="dateOfBirth" ><br>
                        <label>Country:</label>
                        <input name="listDepartTicket[${status.index}].country" value="${ticket.country}" id="country${status.count}" type="text" placeholder="country" ><br>
                        <label>Infant attached:</label>
                        <input name="listDepartTicket[${status.index}].attachedInfant" value="true" type="radio"
                               <c:if test="${ticket.attachedInfant == 'true'}">
                                   checked
                               </c:if>> Yes
                        <input name="listDepartTicket[${status.index}].attachedInfant" value="false" type="radio"
                               <c:if test="${ticket.attachedInfant == 'false'}">
                                   checked
                               </c:if>> No<br>
                        <label>Infant Name:</label>
                        <input name="listDepartTicket[${status.index}].infantName" value="${ticket.infantName}" id="infantName${status.count}" type="text" placeholder="infantName" ><br>
                    </c:forEach>
                
            </div>
        </c:if>


        <c:if test="${sessionScope.tripType == 'roundTrip' && !listChooseReturnTicket.isEmpty()}">
            <div id="returnTickets">
                <h2>Return Ticket:</h2>
                    <c:forEach var="ticket" items="${modelWrapper.listReturnTicket}" varStatus="status">
                        <p>${ticket.flight.flightCode} 
                            - ${ticket.flight.flightRoute.departure.stationName}
                            - ${ticket.flight.flightRoute.arrival.stationName} 
                            - ${ticket.flight.getDepartDate()} ${ticket.flight.getDepartureTime()}
                            - Seat ${ticket.seatCode}
                        </p>
                        <label>Type:</label>
                        <select name="listReturnTicket[${status.index}].passengerType.id" id="passengerType${status.count}" required="required">
                            <c:forEach var="passengerType" items="${listPassengerType}">                         
                                <option value="${passengerType.id}"
                                        <c:if test="${ticket.passengerType.id == passengerType.id}">
                                            selected
                                        </c:if>>
                                    ${passengerType.type}
                                </option>
                            </c:forEach>
                        </select><br>  
                        <label>Identity Number:</label>
                        <input name="listReturnTicket[${status.index}].idCard" value="${ticket.idCard}" id="idCard${status.count}" type="text" placeholder="idCard" ><br>
                        <label>First Name:</label>
                        <input name="listReturnTicket[${status.index}].firstName" value="${ticket.firstName}" id="firstName${status.count}" type="text" placeholder="firstName" ><br>
                        <label>Last Name:</label>
                        <input name="listReturnTicket[${status.index}].lastName" value="${ticket.lastName}" id="lastName${status.count}" type="text" placeholder="lastName" ><br>
                        <label>Gender:</label>
                        <select name="listReturnTicket[${status.index}].gender" id="gender${status.count}" required="required">
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
                        </select><br>
                        <label>Date of Birth:</label>
                        <input name="listReturnTicket[${status.index}].dateOfBirth" value="${ticket.dateOfBirth}" id="dateOfBirth${status.count}" type="date" placeholder="dateOfBirth" ><br>
                        <label>Country:</label>
                        <input name="listReturnTicket[${status.index}].country" value="${ticket.country}" id="country${status.count}" type="text" placeholder="country" ><br>
                        <label>Infant attached:</label>
                        <input name="listReturnTicket[${status.index}].attachedInfant" value="true" type="radio"
                               <c:if test="${ticket.attachedInfant == 'true'}">
                                   checked
                               </c:if>> Yes
                        <input name="listReturnTicket[${status.index}].attachedInfant" value="false" type="radio"
                               <c:if test="${ticket.attachedInfant == 'false'}">
                                   checked
                               </c:if>> No<br>
                        <label>Infant Name:</label>
                        <input name="listReturnTicket[${status.index}].infantName" value="${ticket.infantName}" id="infantName${status.count}" type="text" placeholder="infantName" ><br>
                    </c:forEach>
            </div>
        </c:if>
            
            <div id="customer">
            <h2>Customer</h2>
            <label>First Name:</label>
            <input id="customerFirstName" name="customer.firstName" type="text" placeholder="firstName" value="${customer.firstName}"><br>
            <label>Last Name:</label>
            <input id="customerLastName" name="customer.lastName" type="text" placeholder="lastName" value="${customer.lastName}"><br>
            <label>Identity Number:</label>
            <input id="customerIdCard" name="customer.idCard" type="text" placeholder="indentityNumber" value="${customer.idCard}" required><br>
            <label>Email:</label>
            <input id="customerEmail" name="customer.email" type="email" placeholder="email" value="${customer.email}" required><br>
            <label>Phone:</label>
            <input id="customerPhone" name="customer.phone" type="phone" placeholder="phone" value="${customer.phone}" required><br>   
            </div>
        
        </form:form>
