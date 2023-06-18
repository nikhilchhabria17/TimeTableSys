<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update Slots</title>
  </head>
  <body>
      <%@include file="header.jsp" %>
        <c:set var="count" value="0" scope="page" />
        
            <div class="container">
     <div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Select Slots
  </button>
         <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
        <c:forEach var="cellData" varStatus="loop" items="${KeyList}">
          <a  class="dropdown-item" href="EditSlots?Val=${cellData}">${cellData}</a>
        </c:forEach>
      </div>
    </div>

                <br>
        <Table class="table">
            <form action="/TimeTable-DSS/TImeTable/EditSlots?Val=${param.Val}" method="POST">
            <th colspan="4">Slot Details for Slot No <c:out value="${param.Val}"/></th>
              <tr>
            <th>Slot No</th>
            <th>Course No</th>
            <th colspan=2>Actions</th>
              </tr>
        </thead>          
        <tbody>
          <c:forEach var="cellData" varStatus="loop" items="${SlotValue}">
            <tr>
              <td>${param.Val}</td>
              <td>
                  ${cellData}
                <c:set var="count" value="${count + 1}" scope="page"/>
              </td>
              <td><a class="btn btn-dark" href="/TimeTable-DSS/TimeTable/UpdateSlots?Val=${param.Val}&OldVal=${cellData}">Edit</a></td>
              <td><a class="btn btn-dark" href="">Delete</a></td>
            </tr>
          </c:forEach>
        </tbody>
        </Table>        
        <input type="hidden" name="length" value="${count}"/>
<!--        <center><input type="submit"></center>-->
        </form>
      </div>    
  </body>
</html>