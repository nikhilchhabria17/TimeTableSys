<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>    
    <style>
                                
 #MyInput
{
border:0;
display:block;
margin:20px auto;
text-align:center;
border:2px solid #3498db;
padding:14px 10px;
width:200px;
outline:none;
color:Black;
border-radius:24px;
transition:0.25s;
}
#MyInput input[type="text"]:focus
{
width:280px;
border-color:#2ecc71;	
}
    </style>
    <title> List</title>
</head>
<body>    
    <%@include file="header.jsp" %>
    <div align="center">
        <input type="text" name="" id="MyInput" placeholder="Search 🔎︎" onkeyup="SearchFunc()">
                      <c:forEach var="col" items="${MainMain}" begin="0" end="0">
                          <c:set var="column" value="${col.value[0]}" scope="page"></c:set>                    
                    </c:forEach>
        <a href="AddUser" class="btn btn-primary" style="float:left; margin:20;">Add User</a>
        <table class="Table table-striped" id="MyTable">
            <tbody>
            <c:forEach var="entry" items="${MainMain}">  
                <tr>                                    
                    <c:forEach var="col" items="${entry.value}">                      
                        <td><c:out value="${col}"></c:out></td>                    
                    </c:forEach>
                         <c:choose>
                            <c:when test="${entry.key!=0}">
                        <td>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="delete?columnname=${column}&${column}=<c:out value='${entry.value.get(0)}' />" class="btn btn-outline-primary">Delete</a>                     
                        </td>
                            </c:when>    
                            <c:otherwise>
                                <td colspan="2">Actions</td>
                            </c:otherwise>
                        </c:choose>
                        
                        
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>   
</body>
<script>
    const SearchFunc=()=>
    {   
        let filter=document.getElementById("MyInput").value.toUpperCase();
        let MyTable=document.getElementById("MyTable");
        let tr=MyTable.getElementsByTagName("tr");
        for(var i=1;i<tr.length;i++)
        {
            let td=tr[i].getElementsByTagName('td')[1];
            if(td)
            {
                let textvalue=td.textContent||td.innerHTML;
                if(textvalue.toUpperCase().indexOf(filter)>-1)
                {
                    tr[i].style.display="";
                }
                else
                {
                    tr[i].style.display="none";
                }
            }
        }
    }
    </script>
</html>