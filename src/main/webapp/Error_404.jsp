
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error 404 Not Found!</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
    <center>
        <h1>Error 404 Not Found</h1>
        <img src="<c:out value=' ${pageContext.request.contextPath}/ROOT/images/Error_404.png'/>"/>
    </center>
    </body>
</html>
