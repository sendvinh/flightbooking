<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    var timeout = false;
<c:forEach items="${listChooseDepartTicket}" var="ticket" varStatus="status">
    <c:if test="${ticket.timeOutSecond != 0}">
    var seconds${status.count} = document.getElementById("timeOut${ticket.ticketId}").textContent;
    var countdown${status.count} = setInterval(function() {
        seconds${status.count}--;
        document.getElementById("timeOut${ticket.ticketId}").textContent = seconds${status.count};
        if (seconds${status.count} <= 0) {
        clearInterval(countdown${status.count});
        document.getElementById("timeOut${ticket.ticketId}").textContent = 'Time out';
        $("#next").attr("disabled", true);
        }
    }, 1000);
    </c:if>
</c:forEach>
    //return
<c:forEach items="${listChooseReturnTicket}" var="ticket" varStatus="status">        
    <c:if test="${ticket.timeOutSecond != 0}">
    var rseconds${status.count} = document.getElementById("timeOut${ticket.ticketId}").textContent;
    var rcountdown${status.count} = setInterval(function() {
        rseconds${status.count}--;
        document.getElementById("timeOut${ticket.ticketId}").textContent = rseconds${status.count};
        if (rseconds${status.count} <= 0) {
        clearInterval(rcountdown${status.count});
        document.getElementById("timeOut${ticket.ticketId}").textContent = 'Time out';
        $("#next").attr("disabled", true);
        }
    }, 1000);
    </c:if>
</c:forEach>
</script>
