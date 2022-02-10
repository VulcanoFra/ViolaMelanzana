<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
	<head>
		
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 
		
		<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
	
		<link href="../css/cartCSS.css" rel="stylesheet" type="text/css" />
		
		<link href="../css/homeCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/requestAdminViewCSS.css" rel="stylesheet" type="text/css">
		
    	<script language="javascript" src="../js/ordiniInConsegnaJS.js"></script>
    	
    	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
	</head>	

	<body>
  		
  			<jsp:include page="navbar.jsp"></jsp:include>
  			
	  		<div  class="main-container container" id="cart_prodotti">
  				<c:forEach items="${ordini}" var="ordine">
  				 <c:set var = "string1" value = "${ordine.stato}"/>
					<div class="div_${fn:replace(string1,' ', '_')} order" id="uno">  
						<button class="btnVisualizza vm-btn-view" name="${ordine.id}"> Visualizza </button>
						<h3>Numero dell’ordine: ${ordine.id} </h3> 
						
						<div class="row"> 
							<div class="col-md-4">
								<b>Data dell’ordine</b> <br> ${ordine.data} </div>
							<div class="col-md-4">
								<b>Totale</b> <br>  ${ordine.totale}  €  </div>
							<div class="col-md-4">
								<b>Stato del ordine</b> <br> ${ordine.stato} </div>
						</div>
					</div>
				</c:forEach>
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