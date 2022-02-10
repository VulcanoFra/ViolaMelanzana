<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="it"/>
<fmt:setBundle basename="messages/messages"/>
<html>

	<head>
	<meta charset="UTF-8">
	<title>Viola Melanzana</title>
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 
	
	<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
	
	
	</head>
	
	<body>
		<jsp:include page="navbar.jsp"></jsp:include>
		
			<div class="container" style="text-align: center; margin-top: 10%;">
				<img src="images/errorGif.gif" />
				<h2>Si è verificato un errore durante la navigazione</h2>
			</div>
			
			
			<footer style="background-color: WhiteSmoke; padding-bottom: 6px; padding-top: 6px; margin-top: 12px;">
				<div style="text-align: center;">
		     		<a class="" href="/">
						<img src= "/images/logo.png" id="logo">
					</a>
		   		</div>
		   		<div style="text-align: center; margin-top: 18px;">
		     		<h4>Corso di Web Computing</h4>
		     		<div>Laurea Triennale in Informatica</div>	
		     		<div>Università della Calabria</div>
		     		<div>Anno Accademico 2021-2022</div>
		   		</div>
			</footer>
	</body>
</html>	