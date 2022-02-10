<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
  <head>
		<meta charset="utf-8">

		<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/cartCSS.css" rel="stylesheet" type="text/css" />
		<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		
		<script language="javascript" src="../js/payJS.js"></script>
		
		<script src="https://www.paypal.com/sdk/js?currency=EUR&client-id=AQOqe9CcBelAniKs5bmhJefXkjjNIln6rFY41LXNvSY8ePO-YcBSqFuJuC6Ot9wmvuAn51vjdAACjCCR"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
	</head>	
	<body>
	

		<div class="container pay-container space" id="card">
			<div class="vm-mod"><button class="bi-cart vm-btn-cart vm-btn-mod vm-color" onclick="location.href='\cart';"></button></div>
			<input class="vm-btn-cart vm-btn-deliver" id="btnPagamentoCosegna" type="submit" value="Pagamento alla consegna" />
            <div id="paypal-button-container"></div>
		</div>
	
	</body>
</html>