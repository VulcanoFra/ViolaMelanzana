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
	<link href="../css/folderLikeCSS.css" rel="stylesheet" type="text/css">
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<script src="../js/folderLikeJS.js"></script>
	
	<link rel="icon" type="image/x-icon" href="/images/favicon.ico"> 
	
	</head>
	
<body>
		
	<jsp:include page="navbar.jsp"></jsp:include>

	 <div id="mainContainer" class="row">
	 	<div class="container space-nd" id="mainContainer">
			<div class="col-sm-3 " id="left">
			    <h2 id="foldHead">Le mie raccolte</h2>			
			    <dl>			
				     <c:forEach items="${raccolteUtente}" var="raccolta">
				    		<dt>
						    	<a href="singleFolder?nome=${raccolta.nome}">
					   			  ${raccolta.nome}
						        </a> 
						     </dt>
				    </c:forEach>
				   
				   	<dt>
						<button id="myBtn" onClick="modalFolderFunction('#myModal')">+ Nuova Raccolta</button>	
					</dt>
				</dl>
			</div>
			
				<div class="col-sm-9 " id="likeRecipe">
					  	<h1 id="preferiti">I miei Preferiti</h1>
					   	<div id="likeRecipe">
					   	
					   	</div>			
					   		   	
				 </div>
				<button id="load" class="vm-btn-load"> Load more </button>				 
				 
		</div>
	</div>
	
		<div id="myModal" class="modal">
			  <!-- Modal content -->
			  <div class="modal-content">
			    <div class="modal-header">
			    	<h2>Crea una raccolta</h2>
			      <span class="close" data-dismiss="modal">&times;</span>

			    </div>
			    
			    <div class="modal-body">
			      <form action="newFolder"  method="post" >
					<div class="form-group">
						<label>Nome</label>
						<input type="text" id="nome" class="form-control" placeholder="Assegna un nome alla tua raccolta..." name="nome">
						<label id="Nome"></label>
					<button type="submit" id="saveFolder" name="submit" value="Submit" class="btn btn-block mbtn tx-tfm vm-background-color">Crea</button>
					<button type="reset" data-dismiss="modal"class="btn btn-block mbtn tx-tfm vm-background-color">Annulla</button>
					</div>
				</form>
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