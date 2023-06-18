<%-- 
    Document   : TableList
    Created on : 15 Nov, 2022, 10:54:19 PM
    Author     : Paresh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .center
            {                
                margin-left: auto;
                margin-right: auto;                                
            }
        </style>
    </head>
    <body>
        
        <%@include file="header.jsp" %> 
    <center>
        
        <h1>List Of Tables</h1>
        <Table class="center table table-striped">
            
            <c:forEach  var="TableName" items="${TableList}">
                <tr>
                    <td>
                        ${TableName}
                    </td>
                    <td>
                        <a href="${TableName}/view" class="btn btn-outline-primary">View/Edit Table</a>
                    </td>
                </tr>
            </c:forEach>
        </Table>
    </center>
    </body>
</html>
