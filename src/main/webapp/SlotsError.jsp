<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@page errorPage="ErrorPage.jsp"%>--%>

<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <table class="table table-striped">
            <thead><th colspan="2">Errors in Faculty Assigned</th></thead>
        <c:forEach var="entry" items="${Faculty}">
            <tr>     
                <td>
                    <c:out value="${entry.key}"/></td>                <td>

                <c:forEach var="window" items="${entry.value}">
    <c:out value="${window}"/>     
</c:forEach>                </td>

            </tr>            
    </c:forEach>
            </table>
        <br>
        <table class="table table-striped">
            <thead><th colspan="2">Errors in Rooms Assigned</th></thead>
        <c:forEach var="entry" items="${Rooms}">
            <tr>
                <td> <c:out value="${entry.key}"/></td>
      <td>          
  <c:forEach var="window" items="${entry.value}">
      
    <c:out value="${window}"/>           
</c:forEach>
          </td>
            </tr>
  
  </c:forEach>
        </table>
    </body>
</html>
