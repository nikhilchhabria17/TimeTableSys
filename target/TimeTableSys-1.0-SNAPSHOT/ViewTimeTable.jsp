<%-- 
    Document   : check.jsp
    Created on : 18 Nov, 2022, 7:29:32 PM
    Author     : Paresh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
    </head>
    <body>
        <%@include file="header.jsp"%>        
        
                
                <div class="container">
                <table class="table table-striped">            
                <thead class="thead-dark"> 
                 <th>Time</th>
                <th>MON</th>
                <th>TUE</th>                
                <th>WED</th>
                <th>THU</th>
                <th>FRI</th>
            </tr>
            <c:set var="count" value="0" scope="page" />
            <c:forEach var="rowData" items="${TimeTable}">
        <tr>
            <c:set var="count" value="${count + 1}" scope="page"/>
            <td scope="row"><b>H<c:out value="${count}"></b></c:out></td>
            <c:forEach var="cellData" varStatus="loop" items="${rowData}" >
                <td scope="col"><c:out value="${cellData}"></c:out></td>                    
            </c:forEach>
        </tr>
    </c:forEach>        
        
        </table>     
            <br>
                    <form action="/TimeTable-DSS/TimeTable/Display" method="Post">                                        
            <button class="btn btn-primary btn-lg">Validate</button>                     
            
            <input type="submit" value="Edit" class="btn btn-primary btn-lg">                                   
            <a href="EditSlots" class="btn btn-primary btn-lg">Update Slots</a>                                 
            <a href="DownloadTimeTable" class="btn btn-primary btn-lg">Download TimeTable</a>                                 
            
            </form>

</form>
                </div>
    </body>
            <script>
                function exportTableToCSV($table, filename) {

    var $rows = $table.find('tr:has(td),tr:has(th)'),

        // Temporary delimiter characters unlikely to be typed by keyboard
        // This is to avoid accidentally splitting the actual contents
        tmpColDelim = String.fromCharCode(11), // vertical tab character
        tmpRowDelim = String.fromCharCode(0), // null character

        // actual delimiter characters for CSV format
        colDelim = '","',
        rowDelim = '"\r\n"',

        // Grab text from table into CSV formatted string
        csv = '"' + $rows.map(function (i, row) {
            var $row = $(row), $cols = $row.find('td,th');

            return $cols.map(function (j, col) {
                var $col = $(col), text = $col.text();

                return text.replace(/"/g, '""'); // escape double quotes

            }).get().join(tmpColDelim);

        }).get().join(tmpRowDelim)
            .split(tmpRowDelim).join(rowDelim)
            .split(tmpColDelim).join(colDelim) + '"',



        // Data URI
        csvData = 'data:application/csv;charset=utf-8,' + encodeURIComponent(csv);

        console.log(csv);

        if (window.navigator.msSaveBlob) { // IE 10+
            //alert('IE' + csv);
            window.navigator.msSaveOrOpenBlob(new Blob([csv], {type: "text/plain;charset=utf-8;"}), "csvname.csv")
        } 
        else {
            $(this).attr({ 'download': filename, 'href': csvData, 'target': '_blank' }); 
        }
}

// This must be a hyperlink
$("#xx").on('click', function (event) {

    exportTableToCSV.apply(this, [$('#example1'), 'export.csv']);

    // IF CSV, don't do event.preventDefault() or return false
    // We actually need this to be a typical hyperlink
});                
            </script>    
</html>
