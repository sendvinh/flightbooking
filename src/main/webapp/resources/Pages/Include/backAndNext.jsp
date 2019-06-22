<%-- 
    Document   : backAndNext
    Created on : Jun 18, 2019, 2:51:33 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<div class="row backnext">
    
<div class="col-md-2">
    <button id="back" type="button" class="btn btn-success" onclick="back()">Back</button>
</div>  
<div class="col-md-8"></div>
<div class="col-md-2">
    <c:if test="${sessionScope.tripType == 'oneWay'}" >
        <c:if test="${!listChooseDepartTicket.isEmpty()}">
            <button id="next" type="button" class="btn btn-success float-right" onclick="next()">Next</button>
        </c:if>
    </c:if>
    <c:if test="${sessionScope.tripType == 'roundTrip'}" >
        <c:if test="${!listChooseDepartTicket.isEmpty() || !listChooseReturnTicket.isEmpty()}">
            <button id="next" type="button" class="btn btn-success float-right" onclick="next()">Next</button>
        </c:if>
    </c:if>
</div>            
</div>  
