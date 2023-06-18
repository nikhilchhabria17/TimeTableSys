
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="ErrorPage.jsp"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body> 
        <h3>List of Errors </h3>
        <table class="table table-striped">
            <tr>
                <td>Days</td>                
                <td>Slots</td>                
                <td>Faculty</td>                
            </tr>
        <c:choose>            
    <c:when test="${days!=null && slots!=null && faculty!=null}">
        <c:set var="count" value="0" scope="page" />        
        <c:forEach var="cellData"  items="${days}" >            
            <tr>
                <td>${cellData}</td>
                <td>${slots[count]}</td>
                <td>${faculty[count]}</td>                        
            </tr>
            <c:set var="count" value="${count + 1}" scope="page" />
            </c:forEach>                        
        </div> 
    </c:when>    
    <c:otherwise>
    </c:otherwise>
</c:choose>
    </body>
    
</html>
