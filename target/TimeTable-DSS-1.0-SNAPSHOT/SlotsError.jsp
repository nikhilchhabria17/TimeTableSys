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
        <c:forEach var="entry" items="${Faculty}">
  Key: <c:out value="${entry.key}"/>
  Value: <c:forEach var="window" items="${entry.value}">
    <c:out value="${window}"/>     
</c:forEach>
<br>
    </c:forEach>
  
        <c:forEach var="entry" items="${Rooms}">
  Key: <c:out value="${entry.key}"/>
  Value: <c:forEach var="window" items="${entry.value}">
    <c:out value="${window}"/>     
</c:forEach>
  <br>
  </c:forEach>

    </body>
</html>
