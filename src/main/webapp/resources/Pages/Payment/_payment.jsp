<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment</title>
        <c:import url="/resources/Pages/Include/libLink.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/CSS/payment.css">
    </head>
    <body>
    <div class="container-fluid">
        <div class="contain row">
        <div class="banner col-md-12">
            <img src="${pageContext.request.contextPath}/resources/picture/payment.jpg"  width="798">
        </div>
        <div class="order col-md-12">
            <div class="row">    
            <div class="col-md-3 paymentLogo">
                <img src="${pageContext.request.contextPath}/resources/picture/paynapas.png">
            </div>
            <c:if test="${outOfSession}">
                <div class="col-md-6 center notif">
                    Your session is time out!
                </div>
            </c:if>
            <c:if test="${!outOfSession}">
            <div class="col-md-6 center">
            <table>
                <tr>
                    <th style="width: 35%"></th>
                    <th style="width: 65%"></th>
                </tr>
                <tr>
                    <th colspan="2">Order Information</th>
                </tr>
                <tr>
                    <td>Order code</td>
                    <td>${booking.bookingCode}</td>
                </tr>
                <tr>
                    <td>Amount</td>
                    <td>${utilService.formatVND(booking.getTotalCost())}</td>
                </tr>
                <tr>
                    <td>Producer</td>
                    <td>Green Airline</td>
                </tr>
            </table>
            </div>
            </c:if>
            <div class="col-md-3"></div>
        </div>
        </div>
        <c:if test="${!outOfSession}">
        <div class="payment col-md-12">
            <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6 center">
                
            <form id="payment" name="payment" method="POST" action="pay">
            <table>
                <tr>
                    <th style="width: 35%"></th>
                    <th style="width: 65%"></th>
                </tr>
                <tr>
                    <th colspan="2">Payment Information</th>
                </tr>
            
                <tr>
                    <td><label for="bankCode">Bank name</label></td>
                    <td> 
                    <select id="bankCode" class="form-control-sm custom-select" name="bankCode">
                        <c:forEach items="${listBank}" var="bank">
                            <option value="${bank.bankCode}">${bank.bankName}</option>
                        </c:forEach>
                    </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="accountNo">Account number</label></td>
                    <td> 
                    <input class="form-control-sm" name="accountNo" id="accountNo" type="text" placeholder="account number" style="width: 100%">
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><div class="error">${accountNoError}</div></td>
                </tr>
                <tr>
                    <td><label for="owner">Owner</label></td>
                    <td> 
                    <input class="form-control-sm" name="owner" id="owner" type="text" placeholder="owner name" style="width: 100%">
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><div class="error">${ownerError}</div></td>
                </tr>
                <tr>
                    <td><label>Expire time</label></td>
                    <td> 
                    <select id="expMonth" name="expMonth" class="form-control-sm" style="width: 20%">
                        <c:forEach items="${listMonth}" var="month">
                            <option value="${month}">${month}</option>
                        </c:forEach>
                    </select> 
                    <span style="width: 15%">mm</span>
                    <select id="expYear" name="expYear" class="form-control-sm" style="width: 50%">
                        <c:forEach items="${listYear}" var="year">
                            <option value="${year}">${year}</option>
                        </c:forEach>
                    </select> 
                    <span style="width: 15%">yyyy</span>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><div class="error">${errorMessage}</div></td>
                </tr>
                <tr>
                    <td></td>
                    <td><button class="btn btn-success" type="submit" name="submit" id="submit" style="width: 5em">Pay</button></td>
                </tr>

            </table>
            </form>
            </div>
            <div class="col-md-3"></div>
            </div>
        </div>
        </c:if>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/resources/script/payment.js"></script>
    </body>
</html>
