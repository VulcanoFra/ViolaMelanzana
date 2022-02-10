<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="it"/>
<fmt:setBundle basename="messages/messages"/>


<html>

	<head>
	
		<meta charset="UTF-8">
		<title>Password dimenticata</title>

		<link href="css/authCSS.css" rel="stylesheet" type="text/css">
		<link href="css/commonCSS.css" rel="stylesheet" type="text/css">
		
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
		<script src="../js/askMail.js"></script>
		
	</head>
	
	<body>
		<div class="container lcont">
		
			<div id="containerReset" class="form lform">
				<div class="logo">
					<div class="text-center">
						<h1>Password dimenticata</h1>
					</div>
				</div>
				
				
					<label>Invieremo link per resettare la password alla tua Email</label>					
					<label id="lblMessage" class="text-warning"></label>
					
				
					<div class="form-group">
		               	<div class="form-row">
		                    <input id="email" type="email" class="form-control" placeholder="Inserisci la tua email" name="email">
	                    </div>
	                </div>
	                <div class="form-group"> 
		                <div>
		                	<button id="btnSend" class="btn btn-block mbtn tx-tfm vm-background-color">Invia</button>
						</div>	
					</div>				
				
			</div>
		</div>
	</body>
</html>