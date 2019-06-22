<%-- 
    Document   : promotionForm
    Created on : Apr 15, 2019, 4:40:04 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Promotion Form!</h1>
        <form:form model-attribute="promotion" method="POST">
            <label for="description">Description:</label>
            <input id="description" name="description" type="text" placeholder="Description" value="${promotion.description}"><br>
            <label for="type">Promotion Type: </label>
            <select id="type" name="type">
                <option value="PERCENT"
                        <c:if test="${promotion.type == 'PERCENT'}">
                            selected="true"
                        </c:if>>
                        PERCENT
                </option>
                <option value="AMOUNT"
                        <c:if test="${promotion.type == 'AMOUNT'}">
                            selected="true"
                        </c:if>>
                        AMOUNT
                </option>
            </select><br>
            <label for="valueOfPromotion">Value Of Promotion:</label>
            <input id="valueOfPromotion" name="valueOfPromotion" type="number" step="0.01" placeholder="Percent/Amount of promotion" value="${promotion.valueOfPromotion}"><br>
            <input type="submit" value="Add"/>
        </form:form>
    </body>
</html>
