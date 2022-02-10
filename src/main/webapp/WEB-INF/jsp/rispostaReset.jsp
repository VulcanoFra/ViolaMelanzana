<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="it"/>
<fmt:setBundle basename="messages/messages"/>


<html>

	<head>
	
		<meta charset="UTF-8">
		<title>Risposta Reset</title>

		<link href="css/authCSS.css" rel="stylesheet" type="text/css">
		<link href="css/commonCSS.css" rel="stylesheet" type="text/css">
		
		<link rel="icon" type="image/x-icon" href="/images/favicon.ico">		
		
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>
	
	<body>
	
		<div class="container lcont">
			<div id="containerReset" class="form lform">
				<a href="/">
					<img src= "/images/logo.png" id="logo">
		    	</a>
	    		
				<form method="get" action="loginPage">
					<c:choose>
						<c:when test="${error != null }">
							<div class="logo">
								<div class="text-center">
									<h2>${error}</h2>
								</div>
							</div>
						</c:when>
						<c:when test="${message != null }">
							<div class="logo">
								<div class="text-center">
									<2>${message}</h2>
								</div>
							</div>
						</c:when>
					</c:choose>
	                <div class="form-group"> 
		                <div>
		                	<button class="btn btn-block mbtn tx-tfm vm-background-color">Vai alla pagina di login</button>
						</div>	
					</div>				
				</form>
			</div>
		</div>
	</body>
</html>