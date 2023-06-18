
<html>
<head>
		<title>Login Page</title>
	</head>
	<script>
			function validate()			
			{
				var username=document.getElementById("un").value;
				var password=document.getElementById("pw").value;
				var n=password.length;
				if(isNaN(username)&&username!==""&&password!==""&&(n>8))
				{
				document.getElementById("p1").innerHTML="Login Succesful";
				
				}
				else
				{
						document.getElementById("p1").innerHTML="Please Enter Correct Details";

				}				
			}
		</script>
	<style>
	body
{
margin:0px;
padding:0px;
background:#34495e;
font-family:sans-serif;
}
.box
{
	width:300px;
	padding:40px;
	position:absolute;
	top:50%;
	left:50%;
	transform:translate(-50%,-50%);
	background:none;
	text-align:center;
}
.box h1
{
text-transform:uppercase;
}
.box input[type="text"],.box input[type="password"]
{
border:0;
display:block;
margin:20px auto;
text-align:center;
border:2px solid #3498db;
padding:14px 10px;
width:200px;
outline:none;
color:black;
border-radius:24px;
transition:0.25s;
}
.box input[type="text"]:focus,.box input[type="password"]:focus
{
width:280px;
border-color:#2ecc71;	
}
.box input[type=submit]
{
border:0;
display:block;
margin:20px auto;
text-align:center;
border:2px solid #2ecc71;
padding:14px 40px;
outline:none;
color:black;
border-radius:24px;
transition:0.25s;	
cursor:pointer;
}
.box input[type=submit]:hover
{
background:#2ecc71;
	
}

                        .header1
            {
                overflow:auto;
            }
            .header1 h1{                
                float:left;
                width:40%;
                margin-top:3%;
            }            
            .header1 img{
                float:left;
                height:100px;
            }

            

	</style>
		<body>                    
                    <%@include file="header_1.jsp" %>                
			<table class="box">
                            <form action="Login" method="POST">
					<th><h1>Login</h1><th>
								<tr><td><input type= "text" name="username" placeholder="username" id="un" required></td></tr>
								<tr><td><input type="password" name="password" placeholder="password" id="pw" required></td></tr>
								<tr><td><input type="submit"></td></tr>
								<p id="p1">
									<p>
		</body>
</html>