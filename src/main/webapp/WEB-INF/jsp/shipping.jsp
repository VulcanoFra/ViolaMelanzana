<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<meta charset="utf-8">
		
		<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/cartCSS.css" rel="stylesheet" type="text/css" />
		<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<script language="javascript" src="../js/shippingJS.js"></script>
		<script language="javascript" src="../js/indirizzoJS.js"></script>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
	</head>	
	<body>
		
		<div class="container space" id="card">
			<h3>Indirizzo</h3>
			
			<hr/>
			
			<div class="vm-mod">
				<button class="bi-arrow-left-square vm-btn-cart vm-btn-mod vm-color" id="btnIndietro"></button>
			</div>
			
			<div class="cart-container mod-container">
				<form name="DatiSpedizione" action="pay" method="post">
					<label class="m-2">Via</label>
					<input type="text" class="vm-input" id="indirizzo">
					
					<label class="m-2">Numero civico</label>
					<input type="text" class="vm-input" id="n_civico"> <br>
					
					<label class="m-2">CAP</label>
					<input type="text" class="vm-input" id="cap"> <br>
					
					<label class="m-2">Città</label>
					<input type="text" class="vm-input" id="citta"> <br>
					
					<label class="m-2">Provincia</label>
					<input type="text" class="vm-input" id="provincia"> <br>
					
					<label class="m-2">Telefono</label>
					<input type = "text" class="vm-input" id="telefono"> <br>
					
					<div class="vm-mod">
						<input class="vm-btn-prod vm-btn-cart mt-4" id="btnSalva" type="button" value="Salva indirizzo" />
					</div>
				</form>
			</div>
		
		</div>
		
	</body>
</html>