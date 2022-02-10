<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<link href="../css/cartCSS.css" rel="stylesheet" type="text/css" />
		
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 
		
		<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
	
		<link href="../css/homeCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    	
    	<script language="javascript" src="../js/confirmedOrderJS.js"></script>
    	
    	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
	</head>	

	<body>
  		
  		<jsp:include page="navbar.jsp"></jsp:include>
  		
  		<div  class="main-container container" id="cart_prodotti">
  		
  		<a href="ordiniInConsegna" class="vm-link"> Ordini </a>
		
			<div class="order">
				<h3>Numero dell’ordine: ${ordine_visual.id} </h3>
				<div class="row"> 
					<div class="col-md-4">
						<b>Data dell’ordine</b> <br> ${ordine_visual.data}
					</div>
					<div class="col-md-4">
						<b>Totale</b> <br> ${ordine_visual.totale}  €
					</div>
					<div class="col-md-4">
						<b>Stato del ordine</b> <br>${ordine_visual.stato}
					</div>
				</div>
			</div>
											
			<table class="table table-hover space">
				<thead>
					<tr>
						<td>Nome</td>
						<td>Quantità</td>
						<td>Unità di Misura</td>
						<td>Prezzo</td>
					</tr>	
				</thead>
				
				<tbody>
					<c:forEach items="${ordine_visual.prodottiInOrder}" var="ord">
						<tr>
							<td> ${ord.key.nome}</td>	
							<td> ${ord.value} </td>
							<td> ${ord.key.unitaDiMisura}  </td>
							<td> ${ord.key.prezzo}  c/d  </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div class="vm-input-group">
				<form action="/chageState" method="get">
					<select id="nav-bar-cat" name="state" class="vm-text">
					    <option value="spedito" selected="selected"> Spedito </option>
					    <option value="consegnato"> Consegnato </option>
					    <option value="stornato"> Stornato </option>
				  	</select>
					<button type="submit" class="vm-btn-cart vm-btn-save"> Salva </button>
				</form> 
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