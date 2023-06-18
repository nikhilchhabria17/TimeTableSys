
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <%@include file="header.jsp" %> 
    <center>
        
        <h1>Import Table</h1>
        <form action="../upload" method="post" enctype="multipart/form-data" id="tableform">        
            <table class="table">
                <tr>
                    <td>
            <label for="tablename">Choose Table to Import:</label>
                    </td>
                    <td>
            <select name="tablename" id="tablename" form="tableform">
            <c:forEach  var="TableName" items="${TableList}">                    
                        <option value="${TableName}">${TableName}</option>
            </c:forEach>
            </select>
            </td>
            <tr>
                <td>Choose Import File:</td>
                <td ><input type="file" name="file"  accept=".csv" required/></td>
            </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Import" class="btn btn-primary"></td>
            </tr>
        </form>
    </center>
    </body>
</html>
