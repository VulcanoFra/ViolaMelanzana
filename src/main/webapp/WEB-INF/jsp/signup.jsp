<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="it"/>
<fmt:setBundle basename="messages/messages"/>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Signup</title>

		<link href="../css/authCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/utilCSS.css" rel="stylesheet" type="text/css">
		
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
		
		<script language="javascript" src="../js/signup.js"></script>
		
		<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
				
	</head>
	
	<body>
		<div class="container">
			<div class="row">
			<div class="form" class="col-lg-6 col-md-12 col-sm-12">
				<div class="logo">
					<div class="text-center">
						<h1>Registrati</h1>
					</div>
				</div>
				<c:choose>
			        <c:when test = "${message != null}">
			            <label class="text-danger">${message}</label>
			        </c:when>
			         
			        <c:when test = "${error != null}">
			           	<label class="text-danger">${error}</label>
			        </c:when>
		        </c:choose>

				<form id="formRegister" method="post" action="register">
					<div class="form-group">
						<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
						<label>Nome</label>
						<input type="text" id="nome" class="form-control" placeholder="Il tuo nome" name="nome">
						<label id="lblNome" style="color:red; font-size: 12"></label>
					</div>
					
					<div class="form-group">
						<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
						<label>Cognome</label>
						<input type="text" id="cognome" class="form-control" placeholder="Il tuo cognome" name="cognome">
						<label id="lblCognome" style="color:red; font-size: 12"></label>
					</div>
					
					<div class="form-group">
						<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
						<label>Email</label>
						<input type="email" id="mail" class="form-control" placeholder="Email" name="mail">
						<label id="lblMail" style="color:red; font-size: 12"></label>
					</div>
					
					<div class="form-group">
						<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
						<label>Nome Utente</label>
						<input type="text" id="username" class="form-control" placeholder="Il tuo nome utente" name="username">
						<label id="lblUsername" style="color:red; font-size: 12"></label>
					</div>
					
					<div class="form-group">
						<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
						<label>Password</label>
						<div class="form-row">
							<input type="password" id="password" class="form-control" placeholder="La tua password" name="password">
							<i class="far fa-eye" id="togglePassword" style="margin-left: -35px; margin-top: 10px; cursor: pointer;"></i>
						</div>
						<label id="lblPassword" style="color:red; font-size: 12"></label>
					</div>
					
					<div class="form-group">
						<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
						<label>Conferma Password</label>
						<div class="form-row">
							<input type="password" id="confirmPassword" class="form-control" placeholder="Conferma la tua password" name="confirmPassword">
							<i class="far fa-eye" id="toggleConfirmPassword" style="margin-left: -35px; margin-top: 10px; cursor: pointer;"></i>
						</div>
						<label id="lblConfirmPassword" style="color:red; font-size: 12"></label>
					</div>
					
					<div>
	                	<button type="submit" id="buttonRegister" class="btn btn-block mbtn tx-tfm vm-background-color">Registrati</button>
					</div>
					
					<div class="form-group spc">
						<p class="text-center"><a href="loginPage">Sei già registrato?</a></p>
					</div>
					
					<div class="form-group text-center">
						<a href="/">Pagina Principale</a>
					</div>
				</form>
			</div>
			
			
			
			
			
			
			
			
			
			
			
			<div class="col-lg-6 col-md-12 col-sm-12" style="margin-left: 40px;">
	
				<div style="padding-bottom: 64px; padding-top: 32px;">
					<h1 class="vm-color" style="text-align: center;"> Tutti i vantaggi </h1>
					<hr style="margin-top: -32px; width: 300px;">
					<hr style="margin-top: -20px; width: 300px;">
				</div>
				
				
				
				<div class="row" style="padding-bottom: 64px;">
					<div class="col-md-2 col-sm-2 col-2">
						<div class="fas fa-paper-plane vm-color s-icon"></div>
					</div>
					
					<div class="col-md-10 col-sm-10 col-10">
						<h2 class="vm-color">Pubblica le tue Ricette su Viola Melanzana</h2> 
						Iscrivendoti potrai condividere con tutta la nostra comunità le tue ricette favorite! 
					</div>
				</div>
				
				<div class="row" style="padding-bottom: 64px;">
					<div class="col-md-2 col-sm-2 col-2">
						<div class="fas fa-folder vm-color s-icon"></div>
					</div>
					
					<div class="col-md-10 col-sm-10 col-10">
						<h2 class="vm-color">I tuoi ricettari</h2> 
						Salva le tue ricette preferite organizzandole come vuoi.
					</div>
				</div>
				
				<div class="row" style="padding-bottom: 64px;">
					<div class="col-md-2 col-sm-2 col-2">
						<div class="fab fa-opencart vm-color s-icon"></div>
					</div>
					
					<div class="col-md-10 col-sm-10 col-10">
						<h2 class="vm-color">Lista della spesa</h2> 
						Aggiungi gli ingredienti nella tua lista della spesa direttamente dalle ricette e acquista i prodotti dai nostri magazzini.
					</div>
				</div>
				
				<div class="row" style="padding-bottom: 64px;">
					<div class="col-md-2 col-sm-2 col-2">
						<div class="fas fa-comment-alt vm-color s-icon"></div>
					</div>
					
					<div class="col-md-10 col-sm-10 col-10">
						<h2 class="vm-color">Commenta</h2> 
						Commenta le ricette di Viola Melanzana. Facci sapere cosa pensi!
					</div>
				</div>
				
			</div>
			
			
		</div>	
		</div>
	</body>
</html>