<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="ErrorPage.jsp"%>

<html>    
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TimeTable</title>
    </head>
    <body>
          <%@include file="header.jsp"%>           
    
        <div align="center">
            <form action="#" method="Post" class="tt-form">
         <table class="table">                        
                <tr> 
                 <th>Time</th>
                <th>MON</th>
                <th>TUE</th>                
                <th>WED</th>
                <th>THU</th>
                <th>FRI</th>
            </tr>            
            <c:set var="Days" value="0" scope="page" />
            <c:forEach var="rowData" items="${TimeTable}">                
             <c:set var="count" value="0" scope="page" />
        <tr>            
            <td><b>H<c:out value="${Days+1}"></b></c:out></td>
            <c:forEach var="cellData" varStatus="loop" items="${rowData}" >
                  <c:set var="count" value="${count + 1}" scope="page"/>          
                <td><input type="text" value='<c:out value="${cellData}"></c:out>' name="${Days}${count}"></td>                    
            </c:forEach>
                <c:set var="Days" value="${Days + 1}" scope="page"/>
        </tr>
    </c:forEach>
        </table>
                <button class="validate-btn btn btn-primary">Validate</button>
            <input type="submit" class="submit-btn btn btn-primary ">                        
            <a href="EditSlots" class="btn btn-primary" >Update Slots</a>               
            <a href="ValidateSlots" class="btn btn-primary ">Validate Slots</a>                           
            </form>            
    </div>   
            <%@include file="Details.jsp" %> 
    </body>
    <script>
        let submit_action = "/TimeTable-DSS/TimeTable/Edit";
        let validate_action = "/TimeTable-DSS/TimeTable/Validate";
        let form = document.querySelector(".tt-form");
        let submit_btn = document.querySelector(".submit-btn");
        let validate_btn = document.querySelector(".validate-btn");
        
        submit_btn.addEventListener("click",(e)=>{
            e.preventDefault();
            form.action = submit_action;
            form.submit();
        })
        
        validate_btn.addEventListener("click",(e)=>{
            e.preventDefault();
            form.action = validate_action;
            form.submit();
        })
    </script>
</html>
