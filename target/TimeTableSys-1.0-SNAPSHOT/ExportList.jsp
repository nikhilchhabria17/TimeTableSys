
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
       
    </head>
    <body>
        <%@include file="header.jsp" %> 
    <center>
        
        <h1>List Of Tables</h1>
        <Table class="table table-striped">
            
            <c:forEach  var="TableName" items="${TableList}">
                <tr>
                    <td>
                        ${TableName}
                    </td>
                    <td>
                        <a href="${TableName}/Export" class="btn btn-outline-primary">Export</a>
                    </td>
                </tr>
            </c:forEach>
        </Table>
    </center>
    </body>
</html>
