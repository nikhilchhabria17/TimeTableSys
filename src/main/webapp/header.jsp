
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="Error_404.jsp"%>
<!DOCTYPE html>
<html>
    <head>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        
        <link href="<c:out value=' ${pageContext.request.contextPath}/ROOT/css/style.css'/>" rel="stylesheet" type="text/css"> 
    </head>
    <body>            
        <nav class="navbar navbar-light bg-light py-0">
                   <img  src="<c:out value=' ${pageContext.request.contextPath}/ROOT/images/logo.png'/>"  width="100" alt=.../ class="navbar-brand">
             <h1 class="navbar-brand"><a href="http://localhost:8080/TimeTable-DSS/Homepage">DAIICT-TimeTable System</a></h1>                          
                 <a href="http://localhost:8080/TimeTable-DSS/LogoutServlet" ><button class="btn btn-outline-success my-2 my-sm-0">Logout</button></a>  
           
</nav>
    </body>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>        
        <%@include file="message.jsp" %>
</html>
