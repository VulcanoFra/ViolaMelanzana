<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="it"/>
<fmt:setBundle basename="messages/messages"/>

<html>

<head>
	<title>Insert title here</title>
	<meta charset="utf-8">

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<link href="../css/requestAdminViewCSS.css" rel="stylesheet" type="text/css">
	<script src="../js/requestAdminViewJS.js"></script>
	<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
	
</head>
		<body>
		
			 <jsp:include page="navbar.jsp"></jsp:include>
			
			 <c:if test="${admin == true }">	
				<div class="container space-nd" id="mainContainer">
					<div class="row">

						<div class="row">
								<h2 >Richieste utente in sospeso</h2>
						</div>

						<div class="row">

							<div class="col-12 col-lg-3" id="allUserReq">
								<div class="card shadow mb-3">

									<div class="card-header py-3">
										<p class="text-primary m-0 fw-bold">Utenti</p>
									</div>

									<div class="card-body shadow">

										<div class="table-responsive table mt-2" id="dataTableDiv"
											role="grid" aria-describedby="dataTable_info">
											<table class="table table-hover" id="dataTable">
											
												<thead>
													<tr>
														<th>Username</th>
														<th>Stato richieste</th>
													</tr>
												</thead>
												<tbody id="allUsersTableView">
													
												</tbody>
												<tfoot>
													<tr>
														<th><strong>Username</strong></th>
														<th><strong>Stato richieste</strong></th>
														
													</tr>
												</tfoot>												
											</table>
											
										</div>											
								     	<a href="" onclick="banList()">Segnalazioni</a>
									</div>
								
								</div>
									<div class="row legDiv">
											<p>*<span class="fa fa-check-square-o" aria-hidden="true" style="color:green" ></span>= non ci sono nuove richieste</p>
											<p>*<span class="fa fa-minus-square-o" aria-hidden="true" style="color:red"></span>= ci sono richieste in sospeso</p>
									</div>
							</div>

							<div class="col-12 col-lg-9" id="userReqTable">
								<div class="card shadow mb-3">

									<div class="card-header py-3" id="user">
										
									</div>

									<div class="card-body shadow">

									<div class="table-responsive table mt-1" id="dataTable1"role="grid" aria-describedby="dataTable_info">
											<table class="table table-hover" id="dataTable1">
												<thead>
													<tr>
														<th>Ambito richiesta</th>
														<th>Descrizione</th>
														<th>ID</th>
														<th>Altro</th>
														<th></th>
														<th></th>
													</tr>
												</thead>
												<tbody id="requestTableView">
												
												</tbody>
											
											</table>
										</div>

									</div>

								</div>
							</div>
							<div class="col-12 col-lg-9" id="banTable">
								<div class="card shadow mb-3">

									<div class="card-header py-3">
										<p class="text-primary m-0 fw-bold" id="user">SEGNALAZIONI</p>
									</div>

									<div class="card-body shadow">

									<div class="table-responsive table mt-1" id="dataTableBan"role="grid" aria-describedby="dataTable_info">
											<table class="table table-hover" id="dataTable">
												<thead>
													<tr>
														<th>Titolo</th>
														<th>ID</th>
														<th>Pubblicatore</th>
														<th>N° segnalazioni</th>
													</tr>
												</thead>
												<tbody id="banView">
													
												</tbody>
											
											</table>
										</div>

									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			<div class="modal" id="modalRecipe">
				 <div class="modal-content">

				</div>
			</div>
			</c:if>
			
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
