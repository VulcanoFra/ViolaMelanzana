<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="it"/>
<fmt:setBundle basename="messages/messages"/>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Inserisci Chef</title>	
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
		 <link rel="icon" type="image/x-icon" href="/images/favicon.ico">

		<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/homeCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/insertCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/utilCSS.css" rel="stylesheet" type="text/css">
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<script  src="../js/insertChef.js"></script>
		
	</head>

	<body>
		<jsp:include page="navbar.jsp"></jsp:include>

		<div id="container-insert" class="container fluid">
			<h1>Inserisci lo chef</h1>
			<form id="formChef" action="/insertChef" method="post">
				<div class="form-group">
					<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
					<label>Nome</label>
					<input class="form-control formInput" type="text" id="nome" Placeholder="Nome" name="nome"/>
					<label id="lblNome" style="color:red; font-size: 12"></label>
				</div>
				
				<div class="form-group">
					<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
					<label>Cognome</label>
					<input class="form-control formInput" type="text" id="cognome" Placeholder="Cognome" name="cognome"/>
					<label id="lblCognome" style="color:red; font-size: 12"></label>
				</div>
			
				<div class="form-group">
					<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
					<label>Descrizione</label>
					<textarea rows="6" cols="50" id="descrizione" class="form-control formInput" placeholder="Descrizione" maxlength="500" name="descrizione"></textarea>			
					<label class="label lbl-warning" for="descrizione">Max 500 characters</label>
					<label id="lblDescrizione" style="color:red; font-size: 12"></label>
				</div>
				
				<div class="form-group">
					<label>Link immagine chef</label>
					<input type="text" class="form-control" id="linkImg" placeholder="Link Immagine dello chef" name="linkImg">
				</div>   
 
				<div class="form-group" style="width: 300px;">
					<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px">Campi Obbligatori</i>
				</div>
				<div class="form-group">
					<button id="insertChef" type="submit" class="btn btn-block mbtn tx-tfm vm-background-color">Aggiungi Chef</button>
				</div>
			</form>
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
