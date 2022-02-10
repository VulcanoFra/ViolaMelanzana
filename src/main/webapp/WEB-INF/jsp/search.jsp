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
	
	<script src="../js/searchJS.js"></script>
	
</head>
	<body>
		
		<jsp:include page="navbar.jsp"></jsp:include>
					
		<div class="main-container">
			<div class="container space-s">
				
				<c:if test="${chef != null }">
				
					<c:if test="${admin == true }">
						<a href="/insertRecipeChefPage?key=${chef.id}">Aggiunge Ricetta</a>
					</c:if>	
					
					<div class="chef-card">	
						<h2> Le Ricette degli Chef </h2>
						<hr class="line">
						
						<div class="row chef-card-header">
							<img src="${chef.img_link}" class="img-circle chef-img" alt="chef avatar">
							<h3><b>${chef.nome} ${chef.cognome}</b></h3> 
						</div>
						
						<div class="row">
							<div class="card-body">
								<p>${chef.descrizione}</p>
								<p style="text-align: right;"> <b> Con noi dal </b> ${chef.data}</p>
							</div>	
						</div>	
					</div>
				</c:if>	
				
				<c:if test="${categoria != null }">
					<div class="chef-card">	
						<h2> Ricette ${categoria.nome} </h2>
						<hr class="line">
					</div>
				</c:if>	
				
				<c:if test="${message != null }">
					<div class="chef-card">	
						<h2> ${message}  </h2>
						<hr class="line">
					</div>
				</c:if>	
				
				<c:if test="${search != null }">
					<div class="chef-card">	
						<h2> Risultato Ricerca: <c:if test="${filter != null }">  ${filter.nome} </c:if> ${search} 	</h2>
						<hr class="line">
					</div>
				</c:if>	
		
		
		
		
				<c:forEach items="${list}" var="ricetta">
					<div class="card mb-3 vm-card">
						<div class="row no-gutters" id="recipe-${ricetta.id}">
							<div class="col-md-4">
								<a href="recipePage?ricetta_id=${ricetta.id}" >
									<img src="${ricetta.base64Image}" class="card-img" alt="...">
								</a>
							</div>
							<div class="col-md-8">
								<div class="card-body">
								
									<c:if test="${admin == true}">
										<c:if test="${chef != null}">
											<div class="icon-top-left">
												<button class="vm-link" onclick="remove(${ricetta.id}, ${chef.id})">Rimuovi Ricetta</button>
											</div> 
										</c:if>
									</c:if>	
									
									<div class="icon-top-right">
										<i class="glyphicon glyphicon-comment vm-color icon"></i>
										<span class="number vm-color">${ricetta.commenti.size()}</span>
										<i class="glyphicon glyphicon-heart vm-color icon"></i>
										<span class="number vm-color"> ${ricetta.likes} </span> 
									</div> 
									<a href="recipePage?ricetta_id=${ricetta.id}">
										<h2><b>${ricetta.titolo}</b></h2>   
									</a>
									<p class="decs">${ricetta.descrizione}</p>
								</div>	
								<div class="icon-botton-left">
									<i class="glyphicon glyphicon-cutlery vm-color icon"></i>
									<span class="number vm-color">${ricetta.difficolta}</span>
									<i class="glyphicon glyphicon-hourglass vm-color icon"></i>
									<span class="number vm-color">${ricetta.tempoPreparazione}</span>
								</div> 
							</div>
						</div>
					</div>
				</c:forEach>
			
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