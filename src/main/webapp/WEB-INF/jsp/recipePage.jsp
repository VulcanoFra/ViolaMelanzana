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
	<meta name="viewport" content="width=device-width, user-scalable=no">
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 
	
	<link href="../css/homeCSS.css" rel="stylesheet" type="text/css">
	<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
	<link href="../css/recipePageCSS.css" rel="stylesheet" type="text/css">
	<link href="../css/utilCSS.css" rel="stylesheet" type="text/css">
	
	<script src="../js/recipePageJS.js"></script>
	
	<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
	
	
</head>
	
<body>

	<jsp:include page="navbar.jsp"></jsp:include>
		
	<div class="container space-nd" id="mainContainer">
		   
		   
		<input type="hidden" id="idRicetta" value="${ricetta.id}">
		<input type="hidden" id="mailUtente" value="${utente.mail}">
		<input type="hidden" id="usernameUtente" value="${utente.username}">
		
		<div class="likeUp">		   
			 <c:if test="${utente != null }">	
				 <c:if test="${admin == null }">
					 	<c:if test="${like == false }">	
							<button class="fa fa-heart-o fa-2x submitLike" aria-hidden="true" id="submitLike" name="submitLike" onclick="gestisciLike(this)"></button>
							<span class="likeUpNum">${ricetta.likes}</span>	
						</c:if>
						<c:if test="${like == true }">	
							<button class="fa fa-heart fa-2x submitLike" aria-hidden="true" id="submitLike" name="submitLike" onclick="gestisciLike(this)"></button>
							<span class="likeUpNum">${ricetta.likes}</span>	
						</c:if>
					</c:if>
			  </c:if>
			<c:if test="${utente != null }">	
				 <c:if test="${admin == true }">
				 	<button class="fa fa-heart-o fa-2x submitLike" aria-hidden="true"></button>
					<span class="likeUpNum">${ricetta.likes}</span>
				 </c:if>
			</c:if>
			<c:if test="${utente == null }">	
				<button  type="submit" class="fa fa-heart-o fa-2x submitLike" aria-hidden="true" id="submitLikeUp" onClick="notLoggedFunction()"></button>
				<span class="likeUpNum">${ricetta.likes}</span>					
			</c:if>
		</div>
	
		<h1 class="heading-1">${ricetta.titolo}</h1>
		
			<div class="shadow">
				<div class="media" >
					<div class="media-left col-md-8 ">
						<img id="imgRecipe" src="${ricetta.base64Image}" alt="recipe image">
						<c:if test="${ricetta.utentePubblicatore.mail != null}">
							<p id="by">by ${ricetta.utentePubblicatore.mail}</p>
						</c:if>
						<c:if test="${ricetta.utentePubblicatore.mail == null}">
							<p id="by">by chef n° ${ricetta.chefPubblicatore}</p>
						</c:if>
					</div>
				
					<div class="remove">
						<c:if test="${ricetta.utentePubblicatore == utente}">
							<c:if test="${ricetta.utentePubblicatore.master == true}">	
								<button id="remove" class="fa fa-trash-o fa-2x" aria-hidden="true" onClick="removeRecipeMaster()"></button>
							</c:if>
							
							
							<c:if test="${ricetta.utentePubblicatore.master == false}">	
								<button type= "submit" id="remove" class="fa fa-trash-o fa-2x" aria-hidden="true" onClick="modalFunction('#modalRemoveRecipe')"></button>
							</c:if>							
							
						</c:if>
					</div>
				
					<div class="media-right col-md-4 col-sm-8 col-xs-12" >
						<dl>
							<dt >
								<span class="fa fa-cutlery" aria-hidden="true" id="icon"></span>   Difficolta:
								<c:forEach var = "i" begin ="1" end = "${ricetta.difficolta}">
									<span class="fa fa-star" aria-hidden="true"></span>
								</c:forEach>
						 	</dt>
							
							<dt >
								<span class="fa fa-hourglass-o" aria-hidden="true" id="icon"></span> Tempo preparazione:
								${ricetta.tempoPreparazione} minuti	
							</dt>
							
							<dt >
								<span class="fa fa-clock-o" aria-hidden="true" id="icon"></span> Tempo Cottura:
								${ricetta.tempoCottura} minuti	
							</dt >
							
							<dt >
								<span class="	fa fa-balance-scale" aria-hidden="true" id="icon"></span> Dosi per:
								${ricetta.dosi}		
							</dt>
						</dl>
					</div>
				</div>
			</div>
	
	
	
	
	
	
	<div class="container1">
		<h2 class="titleSection">Presentazione</h2>
				
		<p>${ricetta.descrizione}</p>
	
		<h3 class="titleSection">Ingredienti</h3>
		<ul>
			<c:forEach items="${ingredienti}" var="ingrediente">
				<li class="ing">${ingrediente.ingrediente.nome} ${ingrediente.quantita}</li>
			</c:forEach>
		</ul>
	
		<div>
			<h2 class="titleSection">Preparazione</h2>
			<p>${ricetta.preparazione}<p>
		</div>
		
		<c:if test="${ricetta.video != null }">	
			<div id="video">
				   <h1 id="videoDesc">Cuciniamo insieme <span class="fa fa-smile-o" aria-hidden="true"></span></h1> 
				<iframe src="https://www.youtube.com/embed/${ricetta.video }"> </iframe>
			</div>
		</c:if>
			
		<div>
			<h2 class="titleSection">Consigli</h2>
			<p>${ricetta.consiglio}</p>	
		</div>
		
		<c:if test="${ricetta.curiosita != null }">	
			<div>
				<h2 class="titleSection">Curiosita</h2>
				<p>${ricetta.curiosita}</p>
			</div>
		</c:if>
	</div>
	
	
	
	<c:if test="${admin == null }">	
	
	
	<c:if test="${utente != null }">					 
		<div class="navbar navbar-light blue-grey lighten-5" id="buttonBar1">
		
		<div class="navbar-form navbar-left">	
			<button class="buttonType1 " id="buttonLike" onclick="gestisciLike('#submitLike')">			
				<span class="fa fa-heart-o" aria-hidden="true" id="imgBut"></span>Mi piace
			</button>
		</div>
			
		<div class="navbar-form navbar-left" >	
			<button class="buttonType1" id="buyButton" >
					<span class="fa fa-shopping-basket" aria-hidden="true" id="imgBut"></span>Acquista
			</button>
	 	</div>
	 	
	 	<div class="navbar-form navbar-right" >
	 		<button class="buttonType1" class="openmodal" onClick="modalFunction('#modalSave')">
					<span class="fa fa-folder" aria-hidden="true" id="imgBut"></span>  Salva
			</button>	
		</div>
		
	 	<div class="navbar-form navbar-right" id="banBar" >
		
		 	<c:if test="${ban == false }">	
					<button class="buttonType1" id="banButton" class="openmodal" onClick="modalFunction('#modalBan')">		
						<span class="fa fa-ban" aria-hidden="true" id="imgBut"></span>  Segnala
					</button>
			</c:if>
				
			<c:if test="${ban == true }">
					<button class="buttonType1" id="disabledButton" class="button disabled" title="Hai già segnalato questa ricetta" >		
						<span class="fa fa-ban" aria-hidden="true" id="imgBut"></span>  Segnala
					</button>
			</c:if>
		</div> 	
	</div>
</c:if>
		 	
		 	
		 	
		 	
		 	
	 	<div class="modal myModal" id="modalSave">
			  <div class="modal-content">
				    <div class="modal-header">
						<h2>Aggiungi a raccolta</h2>
						<span class="close" id="closeModal" data-dismiss="modal">&times;</span>
			
					</div>
						    
					<div class="modal-body" id="addToFolderBody">
						<c:if test="${folderList != null }">
								<div class="modalSel" id="modalSelBody">
														 						
									<c:forEach items="${folderList}" var="raccolta">
										<div class="form-check" id="formRaccolte">
											<input name="raccoltaSel" type="radio" id="${raccolta}" value="${raccolta}">
											<label>${raccolta}</label>
										</div>
									</c:forEach>		
									<button id="submitAddToFolder" class="btn btn-block mbtn tx-tfm vm-background-color" onClick="eventAddToFolder()" >Aggiungi</button>
								</div>
														     
						   <h1 id="opp">Oppure</h1>
						    <button type="submit" class="btn btn-block mbtn tx-tfm vm-background-color" onClick="modalFunction('#createNewFolder')">+ Nuova Raccolta</button>	
						   
						</c:if>
							
						<c:if test="${folderList == null }">	
							 <button id="myBtn" onClick="modalFunction('#createNewFolder')">+ Nuova Raccolta</button>	
						</c:if>
					</div> 
			</div>
		</div>
		
		<div class="modal myModal" id="modalBan">
				  <div class="modal-content" >
				    <div class="modal-header">
				    	<h2>Segnala ricetta</h2>
				      <span class="close" id="closeModal" data-dismiss="modal">&times;</span>
	
				    </div>
				    
				    <div class="modal-body">
								  <div class="form-check">
									    <input name="segnalazione" type="radio" id="motivazione" value="Il contenuto è inappropriato" checked="checked">
									    <label for="Il contenuto è inappropriato">Il contenuto è inappropriato</label>
								  </div>
								  
								   <div class="form-check">
									    <input name="segnalazione" type="radio" id="motivazione" value="E' spam" >
									    <label for="E' spam">E' spam</label>
								   </div>
								   <div class="form-check">
								  	  <input name="segnalazione" type="radio" id="motivazione" value="La descrizione non corrisponde al titolo" >
								   	 <label for="La descrizione non corrisponde al titolo">La descrizione non corrisponde al titolo</label>
								   </div>
								   
								   <div class="form-check"> 
								   	  <input name="segnalazione" type="radio" id="motivazione" value="altro" >							   
								   	  <label for="altro">altro</label>
								   </div>
			

						      	<button id="ban" class="btn btn-block mbtn tx-tfm vm-background-color">Invia</button>							
						</div> 
				    </div>
			</div>
			
			<div class="modal myModal" id="modalRemoveRecipe">
				  <div class="modal-content" >
				    <div class="modal-header">
				    	<h2>Richiesta di rimozione della ricetta</h2>
				      <span class="close" id="closeModal" data-dismiss="modal">&times;</span>
	
				    </div>
				    
				    <div class="modal-body">
								  <div class="form-check">
									    <input name="remove" type="radio" id="motivazioneRem" value="Voglio migliorare la ricetta" checked="checked">
									    <label for="r1">Voglio migliorare la ricetta</label>
								  </div>
								  
								   <div class="form-check">
									    <input name="remove" type="radio" id="motivazioneRem" value="Non è stata apprezzata dagli altri utenti" checked="checked">
									    <label for="r2">Non è stata apprezzata dagli altri utent</label>
								  </div>
								   <div class="form-check">
									    <input name="remove" type="radio" id="motivazioneRem" value="Non mi piace più" checked="checked">
									    <label for="r3">Non mi piace più</label>
								  </div>
								   
								   <div class="form-check">
									    <input name="remove" type="radio" id="motivazioneRem" value="Ci sono degli errori" checked="checked">
									    <label for="r4">Ci sono degli errori</label>
								  </div>
								     <div class="form-check">
									    <input name="remove" type="radio" id="motivazioneRem" value="altro" checked="checked">
									    <label for="r5">altro</label>
								  </div>
			

						      	<button id="removeR" class="btn btn-block mbtn tx-tfm vm-background-color">Invia</button>
						</div> 
				    </div>
			</div>
				
			<div class="modal" id="createNewFolder">
			  <!-- Modal content -->
			  <div class="modal-content">
			    <div class="modal-header">
			    	<h2>Crea una raccolta</h2>
			      <span class="close" id="closeModal" data-dismiss="modal">&times;</span>

			    </div>
			    
			    <div class="modal-body">
					<div class="form-group">
						<label>Nome</label>
						<input type="text" id="nomeRaccolta" class="form-control" placeholder="Assegna un nome alla tua raccolta..." name="nome">
						<label id="Nome"></label>
						<button id="saveFolder" class="btn btn-block mbtn tx-tfm vm-background-color">Crea</button>
						<button type="reset" data-dismiss="modal"class="btn btn-block mbtn tx-tfm vm-background-color">Annulla</button>
					</div>
			    </div>
			    
			  </div>			
		</div>
		
		<c:if test="${utente == null }">					 
		  <nav class="navbar navbar-light blue-grey lighten-5" id="buttonBar">
       		
				<button type="submit" class="bt" id="buttonType" onClick="notLoggedFunction()" >			
						<span class="fa fa-heart-o" aria-hidden="true" id="imgBut"></span>  Mi piace
				</button>				
	
				<button  type="submit" class="bt" id="buttonType" onClick="notLoggedFunction()" >
					<span class="fa fa-folder" aria-hidden="true" id="imgBut"></span>  Salva
				</button>

				
				<button type="submit" class="bt" id="buttonType" onClick="notLoggedFunction()" >
					<span class="fa fa-shopping-basket" aria-hidden="true" id="imgBut"></span>  Acquista
				</button>

							
				<button type="submit" class="bt" id="buttonType" onClick="notLoggedFunction()" >		
					<span class="fa fa-ban" aria-hidden="true" id="imgBut"></span>  Segnala
				</button>
				
							
				<button type="submit" class="bt" id="buttonType" onClick="notLoggedFunction()" >		
					<span class="fa fa-comments-o" aria-hidden="true" id="imgBut"></span>  Commenta
				</button>
			</nav>				
		</c:if>
		<c:if test="${utente != null }">
			<div class="text-center">
				<div class="form-group">
					<textarea id="contenuto" name="contenuto" rows="4" class="form-control" placeholder="Scrivi qui il tuo commento "></textarea>
					<button id="submitComment" class="fa fa-comment" aria-hidden="true">Commenta</button>
				</div>
			</div>
		</c:if>
	</c:if>
		
		<div id="divCommenti" class="text-center">
			<c:forEach items="${commentiRicetta}" var="commento" >
				<div class="card">
  					<div class="card-header">
						<h1>${commento.pubblicatore.username}:</h1>
						<img  src="/images/logo.png"></img>
					</div>
					 <div class="card-body">
					 	<p class="card-text ">${commento.contenuto}</p>
					 </div>
					  <div class="card-footer text-muted" id="footer">
					  		${commento.pubblicatore.mail}
					  </div>
				</div>
		   </c:forEach> 
		</div>
		
   </div>
			
	<div id="overlay">
		 <div class="notification with-icon success dismissable w-25" role="alert" aria-labelledby="not2dms-title" id="not2dms" class="animate__animated animate__slideInDown">
					<h2 id="not2dms-title">Attenzione</h2>
					<h5>Effettuare il login per utilizzare questa funzionalità</h5>
		
					<span class="close" aria-hidden="true">&times;</span>
		</div>
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
