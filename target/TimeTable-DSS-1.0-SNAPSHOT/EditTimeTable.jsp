<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="ErrorPage.jsp"%>
<!DOCTYPE html>
<html>
    
    <head>
                <style>
            
            a{
                text-decoration:none;  
  padding:10px 20px 10px 20px;
  border-radius:2px;
  color:White;
            }
button            
{
border:0;
background:#2ecc71;
display:block;
margin:20px auto;
text-align:center;
border:2px solid #2ecc71;
padding:10px 20px;
outline:none;
color:white;
border-radius:24px;
transition:0.25s;	
cursor:pointer;
}
        html,
body {
	height: 100%;
}

body {
	margin: 0;
	background: linear-gradient(45deg, #49a09d, #5f2c82);
	font-family: sans-serif;
	font-weight: 100;
}

.container {
	position: absolute;
	top: 30%;
	left: 50%;
	transform: translate(-50%, -50%);
}

table {
	width: 1000px;
	border-collapse: collapse;
	overflow: hidden;
	box-shadow: 0 0 20px rgba(0,0,0,0.1);                
}

th,
td {    
	padding: 10px;
	background-color: rgba(255,255,255,0.2);
	color: #fff;
}

th,td,tr {
	text-align: center;
}

thead {
	th {
		background-color: #55608f;
	}
}

tbody {
	tr {
		&:hover {
			background-color: rgba(255,255,255,0.3);
		}                
	}
	td {
                text-align:center;
		position: relative;
		&:hover {
			&:before {
				content: "";
				position: absolute;
				left: 0;
				right: 0;
				top: -9999px;
				bottom: -9999px;
				background-color: rgba(255,255,255,0.2);
				z-index: -1;
			}
		}
	}
}
    
            </style>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TimeTable</title>
    </head>
    <body>
          <%@include file="header.jsp" %> 
          <%@include file="Details.jsp" %> 
    
        <div align="center">
            <form action="#" method="Post" class="tt-form">
         <table>                        
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
            <div class="buttons">
                <button class="validate-btn">Validate</button>
            <input type="submit" class="submit-btn">            
            </form>            
            <a href="EditSlots"  >Update Slots</a>               
            <a href="ValidateSlots">Validate Slots</a>               
            </div>
            
    </div>   

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
