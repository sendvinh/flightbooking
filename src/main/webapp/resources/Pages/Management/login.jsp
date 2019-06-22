<%-- 
    Document   : login
    Created on : Mar 24, 2019, 4:10:49 PM
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
        <h1>Hello Security!</h1>
        <form action="<c:url value='j_spring_security_check' />" method='POST'>

            <label for="username"></label>
            <input type="text" id="username" name="username" placeholder="Enter Username" required><br>


            <label for="password"></label> 
            <input type="password" id="password" name="password" placeholder="Enter Password" required><br>

            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
            <input type="submit"
                   class="btn btn-block btn-primary btn-default" value="Log in">

        </form>
    </body>
</html>
