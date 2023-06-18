
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>

.column {     
      justify-content: center;
  align-items: center;

 }

.row
{
margin: 0 auto;
width: 100%;              
}

.column 
{
display:block;
margin:20px auto;
text-align:center;
padding:14px 10px;
width:200px;
color:white;
}
.column:hover
{
border-color:#2ecc71;	
}


html, body {
  height: 100%;
  margin: 0;
  overflow:hidden;
}
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Homepage</title>
    </head>
    <body>        
<%@include file="header.jsp" %>
<div class="container">
                <div class="row">                    
                    <a href="TimeTable/Display"><div class="col-md-3 offset-md-5 btn btn-primary column">Edit TimeTable</div></a>
                </div>
                <div class="row">
                    <a  href="EditData/TableList"><div class="col-md-3 offset-md-5 btn btn-primary column">Edit Data</div></a>
                </div>
                <div class="row">   
                    <a  href="ManageUser/View"><div class="col-md-3 offset-md-5 column btn btn-primary"> Manage User</div></a>
                </div>
                <div class="row">
                    <a  href="Export/View"><div class="col-md-3 offset-md-5 column btn btn-primary"> Export Data</div></a>
                   </div>
                  <div class="row">
                    <a  href="Import/View"><div class="col-md-3 offset-md-5 column btn btn-primary"> Import Data</div></a>
                    </div>
                </div>
    </body>
</html>
