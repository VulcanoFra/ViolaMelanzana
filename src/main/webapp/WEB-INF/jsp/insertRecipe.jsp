<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="it"/>
<fmt:setBundle basename="messages/messages"/>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Inserisci Ricetta</title>	
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
		 <link rel="icon" type="image/x-icon" href="/images/favicon.ico">

		<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/homeCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/insertCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/utilCSS.css" rel="stylesheet" type="text/css">
		
		<script  src="../js/insertRecipe.js"></script>
		<!-- <script src="../js/indexJS.js"></script> -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	</head>

	<body>
		<jsp:include page="navbar.jsp"></jsp:include>

		
		<div id="container-insert" class="container fluid">
			<h1>Inserisci la tua ricetta</h1>
			<form id="form-insert">
				<div class="form-group">
					<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
					<label>Titolo</label>
					<input class="form-control formInput" type="text" id="titolo" Placeholder="Titolo"/>
					<label id="lblTitolo" style="color:red; font-size: 12"></label>
				</div>
			
				<div class="form-group">
					<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
					<label>Descrizione</label>
					<textarea rows="6" cols="50" id="descrizione" class="form-control formInput" placeholder="Descrizione" maxlength="5000"></textarea>			
					<label class="label lbl-warning" for="descrizione">Max 5000 characters</label>
					<label id="lblDescrizione" style="color:red; font-size: 12"></label>
				</div>
				
				<div class="form-group">
					<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
					<label>Preparazione</label>
					<textarea rows="6" cols="50" id="preparazione" class="form-control formInput" placeholder="Preparazione" maxlength="10000"></textarea>			
					<label class="label lbl-warning" for="preparazione">Max 10000 characters</label>
					<label id="lblPreparazione" style="color:red; font-size: 12"></label>
				</div>
				
				<div class="form-row" id="ingrediente">
					<div class="form-group col-md-3">
				      	<input type="text" class="form-control" id="nomeIngrediente" placeholder="Nome Ingrediente">
						<label id="lblNomeIngrediente" style="color: red; font-size: 12;"></label>
				    </div>
				    
				    <div class="form-group col-md-2">
				      	<input type="number" class="form-control" id="quantita" placeholder="Quantità ">
				    </div>
				    
				    
				    <div class="form-group col-md-2">
				      	<input type="text" class="form-control" id="udmisura" placeholder="Unità  di misura">
				    </div>
				    
				    <div class="form-group col-md-2">
				   		<input class="btn" id="btnAggiungiIngrediente" type="button" value = "Aggiungi Ingrediente"/>
				    </div>
				    <div class="form-group col-md-3">
				    	<a href="/getListIngredients" target="_blank" rel="noopener noreferrer">Visualizza lista ingredienti</a>
					</div>
				</div> 
				
				
				<div class= "form-group">
					<table id="tableIngredienti" class="table">
						<thead>
							<tr>
								<th id="labelNome">Nome</th>
								<th>Quantità</th>
								<th>unità di misura</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				
			 	<div class="form-group">
			 		
					<label class="btn btn-default btn-file form-group col-md-3">
					   Carica immagine ricetta <input id="uploadImage" type="file" accept="image/png, image/jpeg" style="display: none;" required> 
					</label>
					<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
					<label>Immagine</label>
					 <img id="imageRicetta" width="300" height="300" src="" ></img> 
					 <label id="lblImmagine" style="color:red; font-size: 12"></label>
					 <button type="button" id="removeImage" class="btn" style="display: none;">Rimuovi</button>
				</div>
				
				<div class="form-group">
					<label>Link video youtube</label>
					<input type="text" class="form-control" id="video" placeholder="Link Youtube video preparazione">
				</div>   
				
				<div class="form-group">
					<label>Consiglio</label>
					<textarea rows="6" cols="50" id="consiglio" class="form-control formInput" placeholder="Consiglio" maxlength="1000"></textarea>			
					<label class="label lbl-warning" for="consiglio">Max 1000 characters</label>
				</div>
				
				<div class="form-group">
					<label>Curiosita</label>
					<textarea rows="6" cols="50" id="curiosita" class="form-control formInput" placeholder="Curiosità" maxlength="1000"></textarea>			
					<label class="label lbl-warning" for="curiosita">Max 1000 characters</label>
				</div>
				
				
				<div class="form-group">
					<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
					<label>Categoria</label>
					
					<div class="form-group">
							<input id="categoria" class="form-control" type="text" placeholder="Inserisci la categoria del piatto"  >
							<label id="lblCategoria" style="color:red; font-size: 12" for="categoria"></label>
							<button id="addCategory" class="btn" type="button">Aggiungi categoria</button>
							<a href="/getListCategories" target="_blank" rel="noopener noreferrer">Visualizza lista Categorie</a>
					</div>
				</div>
				
				<div id="div-categories" class="form-group">
					
				</div>
				
  
				<div class = "form-row">
					<div class="form-group col-md-2">
						<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
						<label>Difficolta</label>
						<input id="difficolta" class="form-control" type="number" min="1" max="5" placeholder="Difficoltà" >
						<label id="lblDifficolta" style="color:red; font-size: 12"></label>
					</div>
					<div class="form-group col-md-3">
						<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
						<label>Dosi</label>
						<input id="dosi" class="form-control" type="text" placeholder="Dosi">
						<label id="lblDosi" style="color:red; font-size: 12"></label>
					</div>
					<div class="form-group col-md-3">
						<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
						<label>Tempo Cottura</label>
						<input id="tempoC" class="form-control" type="number" placeholder="Tempo cottura in minuti">
						<label id="lblTempoC" style="color:red; font-size: 12"></label>
					</div>
					<div class="form-group col-md-3">
						<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
						<label>Tempo Preparazione</label>
						<input id="tempoP" class="form-control" type="number" placeholder="Tempo preparazione in minuti">	
						<label id="lblTempoP" style="color:red; font-size: 12"></label>
					</div>	
				</div>
				<div class="form-group" style="width: 300px;">
					<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px">Campi Obbligatori</i>
				</div>
				<div class="form-group">
					<button id="sendRecipe" type="submit" class="btn btn-block mbtn tx-tfm vm-background-color">Invia Ricetta</button>
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
