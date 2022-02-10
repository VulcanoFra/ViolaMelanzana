
<html>

	<head>
	
		<meta charset="UTF-8">
		<title>Reset Password</title>

		<link href="../css/authCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/util.css" rel="stylesheet" type="text/css">
		
		
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
		<script language="javascript" src="../js/resetPassword.js"></script>
		
	</head>
	
	<body>
		<div class="container lcont">
		
			<div id="containerReset" class="form lform">
				<div class="logo">
					<div class="text-center">
						<h1>Reset Your Password</h1>
					</div>
				</div>
				
				<form id="formReset" method="post" action="/doReset">
					<input type="hidden" name="token" value="${token}">
					<div class="form-group">
						<label>Password</label>
		               	<div class="form-row">
		                    <input id="password" type="password" class="form-control" placeholder="New password" name="password" required autofocus>
		                    <i class="far fa-eye" id="togglePassword" style="margin-left: -35px; margin-top: 10px; cursor: pointer;"></i>
							<label id="info" style="color:red "></label>
	                    </div>
	                </div>

	                <div class="form-group">
	                	
		               	<label>Confirm Password</label>
		               	<div class="form-row">
		                    <input id="confirmPassword" type="password" class="form-control" placeholder="Confirm new password" name="confirmPassword" required autofocus>
		                    <i class="far fa-eye" id="toggleConfirmPassword" style="margin-left: -35px; margin-top: 10px; cursor: pointer;"></i>
		                    <label id="infoConfirm" style="color:red "></label>
	                    </div>
	                </div>
	                
	                <div>
	                	<button id="btnChange" type="submit" class="btn btn-block mbtn tx-tfm vm-background-color">Cambia Password</button>
					</div>					
				</form>
			</div>
		</div>
	</body>
</html>