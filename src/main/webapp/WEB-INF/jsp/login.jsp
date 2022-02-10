<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="it"/>
<fmt:setBundle basename="messages/messages"/>

<html>

	<head>
	
		<meta charset="UTF-8">
		<title>Accedi</title>

		<link href="../css/authCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/utilCSS.css" rel="stylesheet" type="text/css">
		
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
		<script src="https://apis.google.com/js/platform.js" async defer></script>
		<meta name="google-signin-client_id" content="765224026751-f8qujqku5n37rmv05ro5rs8n7ptaoc74.apps.googleusercontent.com">		
		<script language="javascript" src="../js/googleLogin.js"></script>
		<script language="javascript" src="../js/login.js"></script>
		
		<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
	</head>
	
	<body>	
		<div class="container lcont">
			<div id="containerLogin" class="form lform">
				<div class="logo">
					<div class="text-center">
						<h1>Accedi</h1>
					</div>
				</div>
				<c:if test="${error != null}">
					<label class="text-danger">${error}</label>
				</c:if>
				<form id="formLogin" method="post" action="doLogin">
					<div class="form-group">
	                    <label>Nome Utente</label>
	                    <input id="username" type="text" class="form-control" placeholder="Nome Utente" name="username">
						<label id="lblUsername" style="color:red; font-size: 12"></label>
                	</div>

	                <div class="form-group">
	                	
		               	<label>Password</label>
		               	<div class="form-row">
		                    <input id="password" type="password" class="form-control" placeholder="Password" name="password">
		                    <i class="far fa-eye" id="togglePassword" style="margin-left: -35px; margin-top: 10px; cursor: pointer;"></i>
		                    <label id="lblPassword" style="color:red; font-size: 12"></label>
	                    </div>
	                    <label for="password"><a href="askMailReset" target="_blank" rel="noopener noreferrer">Hai dimenticato la password?</a></label>
	                </div>
	                
	                <div>
	                	<button id="buttonLogin" type="submit" class="btn btn-block mbtn tx-tfm vm-background-color">Accedi</button>
					</div>
					
					<div>
						<div class="login-or">
							<hr class="hr-or">
							<span class="span-or">oppure</span>
						</div>
					</div>
					
					<div class="g-signin2 form-group" data-width="446" data-height="38" data-theme="dark" data-longtitle="true" data-onsuccess="onSignIn" data-onfailed="onFailed"></div>
			
					<div class="form-group">
						<p class="text-center">Non sei iscritto? <a href="signUpPage">Registrati subito</a></p>
					</div>	
					
					<div class="form-group text-center">
						<a href="/">Pagina Principale</a>
					</div>					
				</form>
			</div>
		</div>
	</body>
</html>