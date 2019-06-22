<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="booking" class="col-md-8">
        <div>
            <h2>Booking</h2>
        </div>
    <form id="bookingForm" action="booking/seatOption" method="POST" class="col-md-11" onsubmit="validate()">
        <div class="form-group row">
            <div class="col-md-3 custom-control custom-radio">
                <input id="roundTrip" type="radio" name="tripType" value="roundTrip" class="custom-control-input" checked> 
                <label for="roundTrip" class="custom-control-label">Round Trip</label>
            </div>

            <div class="col-md-3 custom-control custom-radio">
                <input id="oneWay" type="radio" name="tripType" value="oneWay" class="custom-control-input"> 
                <label for="oneWay" class="custom-control-label">One Way</label>
            </div>
        </div>

        <div class="form-group row">
            <label for="departure" class="col-md-1 col-form-label">From</label>
            <div class="col-md-4">
                <select id="departure" class="custom-select" name="departure">
                    <c:forEach items="${listStation}" var="station">
                        <option value="${station.stationCode}">(${station.stationCode}) ${station.stationName}</option>
                    </c:forEach>
                </select>
                <div class="error"></div>
            </div>

            <label for="departDate" class="col-md-2 col-form-label">Depart</label>
            <div class="col-md-4 input-group date" data-provide="datepicker" id="departDate">
                <input name="departDate" type="text" id="departDateInput" class="form-control" placeholder="dd/mm/yyyy" style="width:100%">
                <div class="input-group-addon">
                    <span class="glyphicon glyphicon-th"></span>
                </div>
                <div class="error">${departDateError}</div>
            </div>

            <div class="col-md-1">
                <button type="submit" class="btn btn-primary" onclick="next()">Search</button>
            </div>
        </div>

        <div class="form-group row">
            <label for="arrival" class="col-md-1 col-form-label">To</label>
            <div class="col-md-4">
                <select id="arrival" class="custom-select" name="arrival">
                    <c:forEach items="${listStation}" var="station">
                        <option value="${station.stationCode}">(${station.stationCode}) ${station.stationName}</option>
                    </c:forEach>
                </select>
                <div class="error"></div>
            </div>

            <label for="returnDate" class="col-md-2 col-form-label">Return</label>
            <div class="col-md-4 input-group date" data-provide="datepicker" id="returnDate">
                <input name="returnDate" type="text" id="returnDateInput" class="form-control" placeholder="dd/mm/yyyy" style="width:100%">
                <div class="input-group-addon">
                    <span class="glyphicon glyphicon-th"></span>
                </div>
                <div class="error">${returnDateError}</div>
            </div>
        </div>                    
    </form>
</div>