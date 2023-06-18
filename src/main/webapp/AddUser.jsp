<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login Form</title>
</head>
<body>
    <%@include file="header.jsp" %>
    <table class="table">
    <form action="InsertUser" method="post">
        <tr>
            <td>
        <label for="username">Username:</label>
            </td>
            <td>
        <input id="username" name="username" style="width:30%;" required>
            </td>
        </tr>
        <tr>
            <td>
        <label for="password">Password:</label>
            </td>
            <td>
        <input type="password" id="password" name="password" style="width:30%"  required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,12}$"  oninvalid="setCustomValidity('Password should be of atleast 8 characters and contain 1 Lowercase , 1 Uppercase , 1 Symbol')" oninput="setCustomValidity('')">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="Submit" class="btn btn-success"></td>
        </tr>
    </table>        
    </form>
</body>
</html>