<%-- 
    Document   : managePromotion
    Created on : Apr 15, 2019, 4:39:51 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Manage Promotion!</h1>
        <form action="search">
            <input type="text" name="description" placeholder="description..." style="width: 20%"/>
            <input type="submit" value="Search"/>
        </form>
        <form action="add">
            <input type="submit" value="Add Promotion"/>
        </form>
        <table>
            <c:if test="${listPromotion!=null}">
                <thead>
                    <tr>
                        <th>Description</th>
                        <th>Type</th>
                        <th>Amount/Percent of Promotion</th>
                    </tr>
                </thead>
            </c:if>

            <tbody>
                <c:forEach var="promotion" items="${listPromotion}">
                    <tr>
                        <td>${promotion.description}</td>
                        <td>${promotion.type}</td>
                        <td>${promotion.valueOfPromotion}</td>
                        <td>
                            <form action="edit/${promotion.promotionId}">
                                <input type="submit" value="Edit"/>
                            </form>    
                        </td>
                        <td>
                            <form action="delete/${promotion.promotionId}">
                                <input type="submit" value="Delete"/>
                            </form>
                        </td>
                    </tr>

                </c:forEach>
                <c:if test="${listAircraft.isEmpty()}">
                    <tr>
                        <td>No result!</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </body>
    </body>
</html>
