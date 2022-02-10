<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="it"/>
<fmt:setBundle basename="messages/messages"/>

<html>
<head>
	<meta charset="UTF-8">
		
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 

	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<script src="../js/navbarJS.js"></script>
	
	<link href="../css/navbarCSS.css" rel="stylesheet" type="text/css">
		
</head>

<body>
<header>

	<nav class="vm-navbar">
		<div class="container-fluid">
			
			<div class="navbar-header">
	     			<a class="" href="/">
					<img src= "/images/logo.png" id="logo">
				</a>
	   		</div>
	   		
	   		<div class="vm-input-group">
				<form action="/ricerca" method="get">
					<select id="nav-bar-cat" name="filter" class="vm-text">
					    <option value="all" selected="selected"> Tutte le categorie </option>
				  	</select>
					<input type="text" placeholder="Search" name="search">
					<button type="submit" class="fa fa-search vm-color vm-icon"></button>
				</form> 
			</div>
		
			<div class="vm-user nav navbar-nav">
			
				<c:if test="${admin == true }">
					<li class="vm-text" id="hello"> Admin ${utente.username}! </li>
					<li class="dropdown">
						<a class="fa fa-user-o vm-color vm-icon dropdown-toggle" data-toggle="dropdown" href="#"></a>
						
						<ul class="dropdown-menu" style="margin-left: -110px;">
						    <li><a href="requestAdminView">Richieste Utente</a></li>
						    <li><a href="ordiniInConsegna">Ordini</a></li>
						    <li><a href="insertChefPage">Nuovo Chef</a></li>
						    <li><a href="logOut">Logout</a></li>
					    </ul>
					</li>
				</c:if>
				
				
				
				<c:if test="${admin == null }">
					<c:if test="${utente != null }">
						<li class="vm-text" id="hello"> Benvenuto ${utente.username}! </li>
						<li class="dropdown">
							<a class="fa fa-user-o vm-color vm-icon dropdown-toggle" data-toggle="dropdown" href="#"></a>
							
							<ul class="dropdown-menu">
								    <li><a href="account">Il mio Profilo</a></li>
								    <li><a href="account">I miei Ordini</a></li>
								    <li><a href="insertRecipePage">Nuova Ricetta</a></li>
								    <li><a href="logOut">Logout</a></li>
						    </ul>
						</li>
						<li><a href="folderLike" class="fa fa-heart-o vm-color vm-icon"></a></li>
						<li><a href="cart" class="fa fa-opencart fa-lg vm-color vm-icon"></a></li>	
					</c:if>	
					
					
					
					<c:if test="${utente == null }">
						<li class="vm-text" id="hello"> Ciao, accedi! </li>
						<li class="dropdown">
							<a class="fa fa-user-o vm-color vm-icon dropdown-toggle" data-toggle="dropdown" href="#"></a>
							
							<ul class="dropdown-menu" style="margin-left: -109px;">
								    <li><a href="loginPage">Il mio Profilo</a></li>
								    <li><a href="loginPage">I miei Ordini</a></li>
								    <li><a href="loginPage">Nuova Ricetta</a></li>
								    <li><a href="loginPage">Login</a></li>
						    </ul>
						</li>
						<li><a href="loginPage" class="fa fa-heart-o vm-color vm-icon"></a></li>
						<li><a href="loginPage" class="fa fa-opencart fa-lg vm-color vm-icon"></a></li>	
					</c:if>
				</c:if>

			</div>
		 	
			
		</div>
	</nav>

</header>

</body>	
</html>