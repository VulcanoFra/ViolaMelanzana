window.addEventListener("load", function(){
	aggiungiEventi();
});

function aggiungiEventi(){
	
	var btnVisualizza = document.querySelectorAll(".btnVisualizza");

	for(let i = 0; i < btnVisualizza.length; ++i){
		btnVisualizza[i].addEventListener("click",function(e){
			
	        	$.ajax({
					type: "POST",
					url: "/getOrdineDaVisualizzare",
					contentType: "application/json",
					data: JSON.stringify(e.target.name),
					success: function(risposta){
						console.log(risposta);
						if (risposta.status === "OK"){
							window.location.href = '/visualizzaOrdiniInConsegna';
						}
					},
					error: function(xhr){
						console.log(xhr);
						var res = JSON.parse(xhr.responseText);
						alert(res.messaggio);
						window.location.href = '/';
					}
			});
		});
	}
	var x = document.getElementsByClassName("div_spedito");
	for(let i = 0; i < x.length; ++i){
		x[i].className += " order-status";
		x[i].className += " shipped";
	}
	
	var y = document.getElementsByClassName("div_in_attesa_della_consegna,_pagamento_alla_consegna");
	for(let i = 0; i < y.length; ++i){
		y[i].className += " order-status";
		y[i].className += " pay-delivery";
	}
	
	var z = document.getElementsByClassName("div_in_attesa_della_consegna,_pagato_paypal");
	for(let i = 0; i < z.length; ++i){
		z[i].className += " order-status";
		z[i].className += " pay-paypal";
	}

}