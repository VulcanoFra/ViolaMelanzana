<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="it"/>
<fmt:setBundle basename="messages/messages"/>

<html>
	<head>
		<meta charset="utf-8">
		
		<link href="../css/cartCSS.css" rel="stylesheet" type="text/css" />
		<link href="../css/homeCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
 
	 	<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 
		
		<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
	
		<script language="javascript" src="../js/cartJS.js"></script>
		<script language="javascript" src="../js/ProdottoQuantita.js"></script>
		
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
 	
	</head>	
	
	
	<body>
		
		<jsp:include page="navbar.jsp"></jsp:include>
			
		<div class="container main-container">
			<div class="prod-container" style="display:none;" id="carrelloVuoto">
					<label>Carrello vuoto!</label>
			</div>
				
			<div  class="cart-container prod-container" id="tabella_prodotti">
			
				<table class="table table-hover">
					<thead>
						<tr>
							<td>Nome</td>
							<td>Quantità</td>
							<td>Unità di Misura</td>
							<td>Disponibilità</td>
							<td>Prezzo c/d </td>
							<td></td>
						</tr>	
					</thead>
					
					<tbody id="cart_prodotti">
						<c:forEach items="${ordine.prodottiInOrder}" var="ord">
							<tr id="tr_${ord.key.nome}">
								<td id="${ord.key.nome}" class="nomeProdotti">${ord.key.nome}</td>	
								<td> <input type="number" class="quantita" style="width:75px;" value="${ord.value}" onchange="inserimentoDatiPrezzo()"> </td>
								<td> ${ord.key.unitaDiMisura} </td>
								<td class="quantitaMax"> ${ord.key.quantitaDisponibile} </td>
								<td class="prezzo"> ${ord.key.prezzo} </td>
								<td>
									<button class="delete_btn vm-btn-cart vm-btn-mod bi-trash vm-color" name="${ord.key.nome}"></button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
		
	
			<div class="cart-container prod-container" id="card_paga">
		    	<div id="card">
		        	<div class="row cart-row">
		        		<div class="col-md-7 col-sm-7 col-xs-7"> Subtotale </div>
		            	<div class="col-md-5 col-sm-5 col-xs-5" id="subtotale" style="text-align: right;">  </div>
		            </div>
		            
		            <div class="row cart-row">
		        		<div class="col-md-7 col-sm-7 col-xs-7"> Spedizione* </div>
		            	<div class="col-md-5 col-sm-5 col-xs-5" id="spedizione" style="text-align: right;">  </div>
		            </div>
		            
		            <div class="row cart-row">
		        		<div class="col-md-7 col-sm-7 col-xs-7"> Totale </div>
		            	<div class="col-md-5 col-sm-5 col-xs-5" id="totale" style="text-align: right;">  </div>
		            </div>
		
					<input class="vm-btn-pay vm-btn-cart" id="btnIndirizzo" type="button" value="Paga" />
					<div class="cart-row">* Gratuita oltre i 29.99 euro</div>
				</div>
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
