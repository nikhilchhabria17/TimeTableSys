
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
        <c:choose>            
    <c:when test="${days!=null && slots!=null && faculty!=null}">
        <c:set var="count" value="0" scope="page" />
        <div class="alert alert-danger alert-dismissible">  
        <c:forEach var="cellData"  items="${days}" >            
            <c:out value="${cellData}-${slots[count]}-${faculty[count]}"></c:out>           
            &nbsp;&nbsp;&nbsp;&nbsp;
            <c:set var="count" value="${count + 1}" scope="page" />
            </c:forEach>
            <a href="#" class="close" style="float:right" data-bs-dismiss="alert" aria-label="close">&times;</a>
        </div> 
    </c:when>    
    <c:otherwise>
    </c:otherwise>
</c:choose>
    </body>
    
</html>
