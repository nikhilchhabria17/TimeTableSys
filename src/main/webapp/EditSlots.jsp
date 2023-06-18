<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <style>
            .container
            {                
            margin-top: 10%;
            }
        </style>
    </head>
<body>
    <%@include file="header.jsp" %>     
    
            <div class="container  mx-md-n5">
                <h3>Update Slot</h3>
            <table class="table">
            <form action="UpdateSlot" method="post">            
            <tr>
                <td scope="row">Slot No: </td>
                <td>${SlotVal.getSlotno()}</td>
            </tr>
            <tr>
                <td scope="row">Course Code:</td>
                <td>                    
                    <input type="hidden" name="oldcoursename" size="45"
                            value="<c:out value='${SlotVal.getCourseno()}' />"                            
                        />
                    <input type="hidden" name="SlotNo" size="45"
                            value="${SlotVal.getSlotno()}"/>
                    <input type="text" name="newcoursename" size="45"
                            value="${SlotVal.getCourseno()}" />                        
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" class="btn btn-dark" />
                </td>
            </tr>
            </form>    
        </table>        
            </div>
</body>
</html>