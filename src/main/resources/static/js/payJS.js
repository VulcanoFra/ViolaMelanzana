window.addEventListener("load", function(){
	eventiButtoni();
	controlloInMagazzino();
});

function eventiButtoni(){
	paypal.Buttons({
       style: {
         layout: 'horizontal'
       },
        createOrder: function(data, actions) {
           return actions.order.create({
             purchase_units: [{
               amount: {
                 value: localStorage.getItem("prezzo_totale")
               }
             }]
           });
         },
         onApprove: function(data, actions) {
           return actions.order.capture().then(function(details) {
	            $.ajax({
					type: "POST",
					url: "/confirmedOrderPayPal",
					success: function(risposta){
						console.log(risposta);
						if (risposta.status === "OK"){
							window.location.href = 'confirmedOrder';
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
         },

		onCancel: function(data, actions) {
			ripristinoDB();
		},
		
		onError: function(data, actions) {
			ripristinoDB();
		}
     }).render("#paypal-button-container");
		
	btnPagamentoCosegna.addEventListener("click",function(){
	$.ajax({
		type: "POST",
			url: "/confirmedOrderAllaConsegna",
			success: function(risposta){
			console.log(risposta);
				if (risposta.status === "OK"){
					window.location.href = 'confirmedOrder';
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


function controlloInMagazzino(){
	$.ajax({
		type: "POST",
		url: "/control",
		success: function(risposta){
		console.log(risposta);
			if (risposta.status === "OK"){
				if(risposta.messaggio === "false"){
					alert("magazzino in eccesso");
					window.location.href = '/';
				}
			}
		},
		error: function(xhr){
			console.log(xhr);
			var res = JSON.parse(xhr.responseText);
			alert(res.messaggio);
			window.location.href = '/';
		}
	});
}


function ripristinoDB(){
	$.ajax({
		type: "POST",
		url: "/restore",
		success: function(risposta){
		console.log(risposta);
			if (risposta.status === "OK"){
				console.log("ok");
			}
		},
		error: function(xhr){
			console.log(xhr);
			var res = JSON.parse(xhr.responseText);
			alert(res.messaggio);
			window.location.href = '/';
		}
	});
}

