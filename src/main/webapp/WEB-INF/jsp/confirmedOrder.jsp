<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<link href="../css/cartCSS.css" rel="stylesheet" type="text/css" />
		
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		
		<script src="https://unpkg.com/pdf-lib@1.4.0"></script>
    	<script src="https://unpkg.com/downloadjs@1.4.7"></script>
    	<script language="javascript" src="../js/confirmedOrderJS.js"></script>
    	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
	</head>	

	<body>
		
  		
  		<div  class="main-container container" id="cart_prodotti">
  		  			
			<div class="order">
				<h3>Numero dell’ordine: <label id="id_ordine">${ordine.id}</label> </h3>
				<div class="row"> 
					<div class="col-md-4 col-sm-4 col-4">
						<b>Data dell’ordine</b> <br> <label id="data_ordine">${ordine.data}</label>
					</div>
					<div class="col-md-4 col-sm-4 col-4">
						<b>Totale</b> <br> <label id="totale_ordine"> ${ordine.totale}  € </label>
					</div>
					<div class="col-md-4 col-sm-4 col-4">
						<b>Stato del ordine</b> <br> <label id="stato_ordine">${ordine.stato}</label>
					</div>
				</div>
			</div>
											
			<div id="tabella_prodotti">
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
						<c:forEach items="${ordine.prodottiInOrder}" var="ord">
							<tr>
								<td id="${ord.key.nome}" class="nomeProdotti">${ord.key.nome}</td>	
								<td class="quantita"> ${ord.value} </td>
								<td> <label class="unitaDiMisura" >${ord.key.unitaDiMisura}</label>  </td>
								<td> <label class="prezzo">${ord.key.prezzo}</label> <label> c/d </label> </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			
		<div class="container">
	    	<div class="vm-mod">
	    		<button class="bi-file-earmark-pdf vm-btn-cart vm-btn-mod vm-color" onclick="createPdf()"></button>
	    	</div>
	    	<div class="vm-mod">
				<button class="vm-btn-cart vm-btn-prod vm-color" onclick="location.href='/';" >Continua a fare shopping</button>
			</div>
		</div>				
	</div>
	
		

	</body>
</html>