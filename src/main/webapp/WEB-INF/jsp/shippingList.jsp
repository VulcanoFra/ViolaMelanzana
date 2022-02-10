<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<meta charset="utf-8">
		
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 
		
		<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
		
		
		<link href="../css/cartCSS.css" rel="stylesheet" type="text/css" />
		<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/homeCSS.css" rel="stylesheet" type="text/css">
		
		<script language="javascript" src="../js/shippingListJS.js"></script>
		<script language="javascript" src="../js/indirizzoJS.js"></script>
		
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"> 

	</head>	
	<body>
	
		<div class="container space" id="card">
			<h3>Liste indirizzi per le consegne</h3>
			<hr/>
			
			<div class="prod-container" >
				<div style="display:none;" id="IndirizziAssenti">
					<label>Indirizzi assenti!</label>
				</div>
					
					<div id="indirizzi">
						<c:forEach items="${indirizzi}" var="ind">
							<div class="cart-container address-container" id="div_${ind.id}">
						    	<div id="card">
						        	<div class="row">
						        		<div class="col-md-6 col-sm-6 col-xs-6"> <h4>Via:</h4></div>
						            	<div class="col-md-6 col-sm-6 col-xs-6"> ${ind.indirizzo} </div>
						            </div>
						            
						            <div class="row">
						        		<div class="col-md-6 col-sm-6 col-xs-6"> <h4>Numero civico:</h4> </div>
						            	<div class="col-md-6 col-sm-6 col-xs-6"> ${ind.n_civico} </div>
						            </div>
						            
						            <div class="row">
						        		<div class="col-md-6 col-sm-6 col-xs-6"> <h4>CAP:</h4> </div>
						            	<div class="col-md-6 col-sm-6 col-xs-6"> ${ind.cap} </div>
						            </div>
						            
						            <div class="row">
						        		<div class="col-md-6 col-sm-6 col-xs-6"> <h4>Città:</h4> </div>
						            	<div class="col-md-6 col-sm-6 col-xs-6"> ${ind.citta} </div>
						            </div>
						            
						            <div class="row">
						        		<div class="col-md-6 col-sm-6 col-xs-6"> <h4>Provincia:</h4> </div>
						            	<div class="col-md-6 col-sm-6 col-xs-6"> ${ind.provincia} </div>
						            </div>
						            
						            <div class="row">
						        		<div class="col-md-6 col-sm-6 col-xs-6"> <h4>Telefono:</h4> </div>
						            	<div class="col-md-6 col-sm-6 col-xs-6"> ${ind.telefono} </div>
						            </div>
						            
						             <div>
						             	<input type="radio" name="indirizzi" id="${ind.id}">
						            	<label style="margin-top: 8px;"> Indirizzo di Consegna</label>
						           	</div>
						           
									<div class="vm-mod">
										<button class="btnElimina vm-btn-cart vm-btn-mod bi-trash vm-color" name="${ind.id}"></button>
						            	<button class="btnModifica vm-btn-cart vm-btn-mod bi-pencil-square vm-color" name="${ind.id}"></button>
									</div>
									
								</div>
							</div>
						</c:forEach>	
					</div>				
				</div>
				
				
				
				<div class="vm-mod">
					<button class="vm-btn-cart vm-btn-mod bi-plus-square vm-color" id="btnNuovo"></button>
				</div>
				
				<div class="vm-mod">
					<button class="bi-cart vm-btn-cart vm-btn-mod vm-color" onclick="location.href='\cart';"></button>
				</div>
				
				<div id="pulsantiSecondari" class="vm-mod">
					
					<input id="btnPaga" class="vm-btn-prod vm-btn-cart" type="button" value="Continua" />
				</div>
				
		</div>
	
	</body>
</html>